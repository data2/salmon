package com.muskteer.dico;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.dico.database.oracle")
public class DicoOracleConfig implements DicoConfig {
    private String url;
    private String username;
    private String password;

}
