package provotor.petprojects.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RestCallsAspect {
    private static Logger logger = LoggerFactory.getLogger(RestCallsAspect.class);

    @Pointcut("within(provotor.petprojects.MainRestController)")
    public void controller(){};

    @Pointcut("controller() && execution(* *..get*(..))")
    public void onlyGet(){};

    @Pointcut("controller() && !onlyGet()")
    public void exceptGet(){};

    @Before("onlyGet()")
    public void beforeGet(JoinPoint point) {
        logger.info("Request: " + point.getSignature().getName());
    }

    @Before("exceptGet()")
    public void before(JoinPoint point) {
        logger.info("Request: " + point.getSignature().getName() + " " + Arrays.toString(point.getArgs()));
    }

    @AfterReturning(value = "onlyGet()")
    public void afterReturnGet(JoinPoint point) {
        logger.info("Response: " + point.getSignature().getName());
    }

    @AfterReturning(value = "exceptGet()", returning = "returnValue")
    public void afterReturn(JoinPoint point, Object returnValue) {
        logger.info("Response: " + point.getSignature().getName() + " " + returnValue.toString());
    }
}
