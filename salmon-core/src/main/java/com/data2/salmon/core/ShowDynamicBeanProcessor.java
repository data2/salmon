package com.data2.salmon.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Component
public class ShowDynamicBeanProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(ShowDynamicBeanProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Salmon) {
            log.info(beanName);
        }
        return bean;
    }
}
