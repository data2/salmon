package com.muskteer.dico;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.dico.database.partition")
public class DicoPartitionConfig implements DicoConfig {
    private String url;
    private String username;
    private String password;
    private String mapping;
    private String table;

}
