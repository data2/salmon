package com.data2.salmon.core;

import com.data2.salmon.core.common.util.ScanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

import static com.data2.salmon.core.engine.enums.DataBase.JDBC;
import static com.data2.salmon.core.engine.enums.DataBase.PARTITION;

/**
 * @author leewow
 */
@Component
public class ProxyBeanProcessor implements BeanFactoryPostProcessor {

    public static final Logger log = LoggerFactory.getLogger(ProxyBeanProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;
        String pack = ScanUtil.achieveScan();
        if (StringUtils.isEmpty(pack)) {
            throw new ApplicationContextException("spring.salman.scan is null ,please configure in spring");
        }
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
                log.info("register salmon success :" + inner.toString());
            }

        }


//        Map<String, Object> beans = defaultListableBeanFactory.getBeansWithAnnotation(BootDico.class);
//        Iterator<Map.Entry<String, Object>> it = beans.entrySet().iterator();
//        List<String> list = Lists.newArrayList();
//        BootDico annotation = null;
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = it.next();
//            list.add(entry.getKey());
//
//            annotation = defaultListableBeanFactory.findAnnotationOnBean(entry.getKey(), BootDico.class);
//            log.info("regist bootdico component : " + entry.getKey()
//                    + " , name : " + annotation.name()
//                    + " , database : " + annotation.database());
//            if (DataBaseEnum.JDBC.toString().equals(annotation.database())
//                    && true) {
//                BeanDefinitionBuilder beanDefine = BeanDefinitionBuilder.genericBeanDefinition(DatabaseQuickDicoService.class);
//                AbstractBeanDefinition bf = beanDefine.getBeanDefinition();
//                bf.addQualifier(new AutowireCandidateQualifier(annotation.name()));
//                bf.setScope("prototype");
//                beanDefine.addPropertyValue("file", annotation.file());
//                beanDefine.addPropertyValue("name", annotation.name());
//                beanDefine.addPropertyValue("database", annotation.database());
//                defaultListableBeanFactory.registerBeanDefinition(annotation.name(), bf);
//                log.info("regist bean : " + annotation.name() + " , info : " + beanDefine.toString());
//            }
//        }

    }

}
