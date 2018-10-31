package com.muskteer.dico;

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

@Component
public class ProxyBeanProcessor implements BeanFactoryPostProcessor {

    public static final Logger log = LoggerFactory.getLogger(ProxyBeanProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;
        if (StringUtils.isEmpty(DicoScanUtil.achieveScan())) {
            throw new ApplicationContextException("spring.dico.scan is null ,please configure in spring");
        }
        for (Scanner.Inner inner : Scanner.loadCheckClassMethods(DicoScanUtil.achieveScan())) {
            if (DicoEnum.PARTITION.toString().equalsIgnoreCase(inner.database)) {
                AbstractBeanDefinition abstractBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(QuickDicoService.class).getBeanDefinition();
                abstractBeanDefinition.addQualifier(new AutowireCandidateQualifier(inner.name));
                abstractBeanDefinition.setScope("prototype");
                defaultListableBeanFactory.registerBeanDefinition(inner.name, abstractBeanDefinition);
                log.info("regist Dico success :" + inner.toString());
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
//            if (DicoEnum.JDBC.toString().equals(annotation.database())
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
