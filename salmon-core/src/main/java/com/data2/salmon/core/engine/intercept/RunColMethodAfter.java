package com.data2.salmon.core.engine.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author leewow
 */
@Slf4j
public class RunColMethodAfter implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) {
        log.info("after sql run.");
    }

}
