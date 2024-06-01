package ticketing.ticket.common.aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ticketing.ticket.common.aop.annotation.SlackNotification;
import ticketing.ticket.common.slack.MessageGenerator;
import ticketing.ticket.common.slack.MessageSender;
import ticketing.ticket.common.slack.RequestStorage;
import ticketing.ticket.common.slack.enums.MessageFormat;
import ticketing.ticket.common.slack.enums.SlackNotificationLevel;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SlackAspect {

    private final RequestStorage requestStorage;
    private final MessageGenerator messageGenerator;
    private final MessageSender messageSender;

    @Before("@annotation(ticketing.ticket.common.aop.annotation.SlackNotification)")
    public void sendExceptionMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (!validateHasOneArgument(args)) {
            return;
        }

        if (!validateIsException(args)) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SlackNotificationLevel level = signature.getMethod()
                .getAnnotation(SlackNotification.class)
                .level();

        String message = messageGenerator.generate(requestStorage.get(), (Exception) args[0], level);
        messageSender.send(message);
    }

    private boolean validateIsException(Object[] args) {
        if (!(args[0] instanceof Exception)) {
            log.warn("[SlackAlarm] argument is not Exception");
            return false;
        }
        return true;
    }

    private boolean validateHasOneArgument(Object[] args) {
        if (args.length != 1) {
            log.warn(String
                .format(MessageFormat.SLACK_ALARM_FORMAT.getMessage(), "ambiguous exceptions! require just only one Exception"));
            return false;
        }
        return true;
    }
}
