package com.platform.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value = "execution(* com.platform.controller.*.*(..))")
    public void logingPoint() {
    }

    @Around("logingPoint()")
    public Object myLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] param = proceedingJoinPoint.getArgs();
        logger.info("调用方法：" + className + "." + methodName);
        StringBuilder str = new StringBuilder();
        for (Object o : param) {
            str.append(o.getClass().getName()).append(o.toString()).append("    ");
        }

        logger.info("传入参数:" + str.toString());
        Object object = proceedingJoinPoint.proceed();

        logger.info("调用结束");
        return object;
    }
}
