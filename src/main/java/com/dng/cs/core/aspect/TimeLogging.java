package com.dng.cs.core.aspect;

import com.dng.cs.core.log.LogFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class TimeLogging {

    @Pointcut("execution(* com.dng.cs.core.repository.base.*.*(..))")
    private void repositoryFunction() {}

    @Pointcut("execution(* com.dng.cs.core.controller.*.*(..))")
    private void controllerFunction() {}

    // Logging time
    @Around("repositoryFunction() || controllerFunction()")
    public Object logAroundEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        Object value = joinPoint.proceed();
        Instant end = Instant.now();
        String executeTime = String.format("[%s] execute took [%d] ms ",
                            joinPoint.getSignature().getName(),
                            Duration.between(start, end).toMillis());
        log.info(executeTime);
        LogFactory.infoLog().info(executeTime);
        return value;
    }

}
