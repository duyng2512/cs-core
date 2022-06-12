package com.dng.cs.core.aspect;

import com.dng.cs.core.log.LogFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionLogging {

    @AfterThrowing(pointcut = "execution(* com.dng.cs.core.controller.*.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable  {
        LogFactory.errorLog().error(ex.getMessage(), ex);
    }

}
