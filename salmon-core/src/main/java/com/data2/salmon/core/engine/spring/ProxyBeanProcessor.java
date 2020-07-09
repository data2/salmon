package com.data2.salmon.core.engine.spring;

import com.data2.salmon.core.common.util.ScanUtil;
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

import static com.data2.salmon.core.engine.enums.DataBase.JDBC;
import static com.data2.salmon.core.engine.enums.DataBase.PARTITION;

/**
 * @author leewow
 */
@Slf4j
@Component
public class ProxyBeanProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;
        String pack = ScanUtil.achieveScan();
        if (StringUtils.isNotEmpty(pack)) {
            for (Scanner.Inner inner : Scanner.loadCheckClassMethods(pack)) {
                if (PARTITION == inner.database || JDBC == inner.database) {
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
        } else {
            log.error("spring.salman.scan is null ,please configure in spring");

        }

    }

}
