package com.data2.salmon.core.engine.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.data2.salmon.core.engine.enums.DataBase.jdbc;

/**
 * @author leewow
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.salmon.database.jdbc")
@Configuration
public class JdbcConfig {
    private String url;
    private String username;
    private String password;

    @Bean(name = "jdbc")
    public DruidDataSource builder() {
        if (StringUtils.isAllBlank(url, username, password)) {
            return null;
        }
        if (StringUtils.isAnyEmpty(url, username, password)) {
            log.info("jdbc config not right, url or username or passwd is null!");
            return null;
        }
        if (!url.contains(jdbc.name())) {
            log.info("jdbc url config not contain jdbc str!");
            return null;
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
