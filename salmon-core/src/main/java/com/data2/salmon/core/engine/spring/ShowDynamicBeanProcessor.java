package com.data2.salmon.core.engine.spring;

import com.data2.salmon.core.engine.inter.Salmon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author data2
 */
@Slf4j
@Component
public class ShowDynamicBeanProcessor implements BeanPostProcessor {

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
