package com.muskteer.dico.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dico.rule")
public class DicoRuleConfig {

    private String table;
//
//    public DicoConfig() {
//    }


//    public DicoConfig(Properties properties) {
//        this.properties = properties;
//    }
//
//    public DicoConfig(String file) {
//        this(initProps(file));
//    }
//
//    private Properties initProps(String file) {
//        Properties properties = new Properties();
//        try {
//            properties.load(DicoClassLoader.loadFile(file));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return properties;
//    }

}
