package com.rms.kalah.crossConcerns.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLogger {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    @Pointcut( value = "execution(* com.rms.kalah..*.*(..))")
    public void methodsExecutions() {}

    @Around("methodsExecutions() ")
    public Object createProcessorSpan(final ProceedingJoinPoint combined) throws Throwable {
        Object result;
        try {
            logger.info("Execute: {} || Parameters: {}", combined.getSignature(), combined.getArgs());
            result = combined.proceed();
            logger.info("Executed:  {} || Result: {}", combined.getTarget(), result);
        }catch(Exception e){
            logger.info("Executed:  {} || Exception: {}", combined.getTarget(), e);
            throw e;
        }
        return result;
    }

}