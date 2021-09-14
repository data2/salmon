package com.data2.salmon.core.engine.spring;

import com.data2.salmon.core.common.util.ArrUtils;
import com.data2.salmon.core.engine.config.ScannerPackage;
import com.data2.salmon.core.engine.factory.SingleWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import static com.data2.salmon.core.engine.enums.DataBase.jdbc;
import static com.data2.salmon.core.engine.enums.DataBase.partition;

/**
 * @author data2
 */
@Slf4j
@Component
public class ProxyBeanProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;
        String pack = ScannerPackage.achieveScan();
        if (StringUtils.isEmpty(pack)) {
            log.warn("spring.salman.scan is null, noting todo");
            return;
        }
        for (Scanner.Inner inner : Scanner.loadCheckClassMethods(pack)) {
            if (!ArrUtils.inArray(inner.database, jdbc, partition)) {
                continue;
            }
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SingleWorker.class);
            beanDefinitionBuilder.addPropertyValue("name", inner.name);
            beanDefinitionBuilder.addPropertyValue("database", inner.database);
            beanDefinitionBuilder.addPropertyValue("file", inner.file);
            AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
            abstractBeanDefinition.addQualifier(new AutowireCandidateQualifier(inner.name));
            abstractBeanDefinition.setScope("prototype");
            defaultListableBeanFactory.registerBeanDefinition(inner.name, abstractBeanDefinition);
            log.info("register salmon success : {}", inner.toString());

        }

    }

}
