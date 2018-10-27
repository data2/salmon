package com.muskteer.dico.config;

import java.util.Properties;

import com.muskteer.dico.common.util.DicoClassLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dico.database")
public class DicoDatabseConfig {

    private Properties properties;
    private String url;
    private String username;
    private String password;
    private String mapping;

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
