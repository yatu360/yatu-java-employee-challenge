package com.reliaquest.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.reliaquest.api.service..*(..)) || execution(* com.reliaquest.api.controller..*(..))")
    public void loggableMethods() {
        // Pointcut for all methods in service & controller packages
    }

    @Before("loggableMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info(
                "➡️ Entering: {}.{}() with arguments = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info(
                "⬅️ Exiting: {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    @AfterThrowing(pointcut = "loggableMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error(
                "❌ Exception in: {}.{}() - {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage(),
                ex);
    }
}
