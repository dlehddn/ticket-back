package ticketing.ticket.common.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("@annotation(ticketing.ticket.common.aop.annotation.TimeTrace)")
    public Object doTimeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("[{}] : {} ms", joinPoint.getSignature(), endTime - startTime);
        return proceed;
    }
}
