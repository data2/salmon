package com.data2.salmon.core.engine.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.engine.druid.Config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.salmon.database.oracle")
public class OracleConfig implements Config {
    private String url;
    private String username;
    private String password;

    @Override
    public DruidDataSource builder(String dbid) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // TODO
        dataSource.setDriverClassName(null);
        return null;
    }
}
