package com.muskteer.dico.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dico.database")
public class DicoDatabseConfig {

    private String url;
    private String username;
    private String password;
    private String mapping;
    private String table;

}
