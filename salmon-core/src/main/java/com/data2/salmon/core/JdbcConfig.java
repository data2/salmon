package com.data2.salmon.core;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.salmon.database.jdbc")
public class JdbcConfig implements Config {
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
        dataSource.setDriverClassName(url.contains("jdbc") ? "com.mysql.cj.jdbc.Driver" : "oracle.jdbc.driver.OracleDriver");
        return dataSource;
    }
}
