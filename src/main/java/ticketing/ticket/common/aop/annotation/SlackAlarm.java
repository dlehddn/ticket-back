package ticketing.ticket.common.aop.annotation;

import ticketing.ticket.common.slack.enums.SlackAlarmLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SlackAlarm {
    SlackAlarmLevel level() default SlackAlarmLevel.WARN;
}
