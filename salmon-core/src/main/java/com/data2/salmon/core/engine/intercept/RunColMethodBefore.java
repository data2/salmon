package com.data2.salmon.core.engine.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author leewow
 */
@Slf4j
public class RunColMethodBefore implements MethodBeforeAdvice {

    @Override
    public void before(Method arg0, Object[] arg1, Object arg2) {
        log.info("before sql run . ");
    }

}
