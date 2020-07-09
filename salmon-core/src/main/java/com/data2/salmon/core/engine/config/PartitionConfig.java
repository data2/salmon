package com.data2.salmon.core.engine.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static com.data2.salmon.core.engine.enums.DataBase.JDBC;

/**
 * @author leewow
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.salmon.database.partition")
public class PartitionConfig implements Config {
    private String url;
    private String username;
    private String password;
    private String mapping;
    private String table;

    @Override
    public DruidDataSource builder(String dbId) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(mapping) || StringUtils.isEmpty(table)) {
            log.info("partition config not right, url or username or passwd or mapping or table is null!");
            return null;
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(doURL(dbId));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(url.contains(JDBC.getCode()) ? "com.mysql.cj.jdbc.Driver" : "oracle.jdbc.driver.OracleDriver");
        dataSource.setInitialSize(5);
        return dataSource;
    }

    private String doURL(String dbId) {
        String[] tmp;
        for (String sub : mapping.split(",")) {
            tmp = sub.split(">");
            if (tmp.length == 2) {
                if (tmp[0].equals("db" + dbId)) {
                    url = url.replace("#", tmp[1]);
                    break;
                }
            }
        }
        return url;

    }

}
