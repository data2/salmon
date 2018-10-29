package com.muskteer.dico.inner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class BindPropertiesBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(BindPropertiesBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        try {
            if (bean.getClass().isAnnotationPresent(BootDico.class)
                    && bean instanceof DicoService) {
                log.info(beanName);
                BootDico annotation = bean.getClass().getAnnotation(BootDico.class);
                Field field = bean.getClass().getField("dico");
                Mapper mapper = field.getAnnotation(Mapper.class);
                JdbcQuickDicoService dico = new JdbcQuickDicoService();
                dico.type = annotation.type();
                dico.file = mapper.file();
                dico.database = mapper.database();
//                field.set(bean,dico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
