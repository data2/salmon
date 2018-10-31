package com.muskteer.dico;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

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

    @Override
    public DruidDataSource builder(String dbId) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(doURL(dbId));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(url.contains("jdbc") ? "com.mysql.jdbc.Driver" : "oracle.jdbc.driver.OracleDriver");
        dataSource.setInitialSize(5);
        return dataSource;
    }

    private String doURL(String dbId) {
        String[] tmp = null;
        for (String sub : mapping.split(",")) {
            tmp = sub.split("\\|");
            if (tmp.length == 2) {
                if (tmp[0].equals("db" + dbId)) {
                    url = url.replace("XXX", tmp[1]);
                    break;
                }
            }
        }
        return url;

    }

}
