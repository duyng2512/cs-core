package com.dng.cs.core.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AddressControllerAspect {

    @Before("execution(* com.dng.cs.core.controller.AddressController.*(..))")         //point-cut expression
    public void logBeforeV1(JoinPoint joinPoint) {
        log.info("Before AddressController.logBeforeV1() : " + joinPoint.getSignature().getName());
    }
    
}
