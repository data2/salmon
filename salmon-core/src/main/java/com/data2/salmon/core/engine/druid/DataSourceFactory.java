package com.data2.salmon.core.engine.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.engine.config.ConfigurationLoader;
import com.data2.salmon.core.engine.config.PartitionConfig;
import com.data2.salmon.core.engine.domain.Looker;
import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.data2.salmon.core.engine.enums.DataBase.JDBC;

/**
 * @author leewow
 */
@Component
@Slf4j
public class DataSourceFactory implements SourceFactory {

    @Autowired
    public PartitionConfig partitionConfig;
    @Autowired(required = false)
    @Qualifier("jdbc")
    public DruidDataSource jdbcConfig;
    @Autowired(required = false)
    @Qualifier("oracle")
    public DruidDataSource oracleConfig;

    private Cache<String, DruidDataSource> dataSourceCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(20)
            .build();

    @Override
    public void afterPropertiesSet() {
        try {
            String config = Resources.toString(ConfigurationLoader.getClassLoader().getResource("application.yml"),
                    Charsets.UTF_8);
            if (config.contains(JDBC.name().toLowerCase())) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
//            if (config.contains(ORACLE.name().toLowerCase())) {
//                Class.forName("oracle.jdbc.driver.OracleDriver");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("loading class exception, exception: {}", e.getMessage());
        }
    }

    /**
     * when use databse, then build it, except easy config,eg jdbc or oracle
     *
     * @param looker
     * @return
     * @throws ExecutionException
     */
    @Override
    public DruidDataSource getSource(final Looker looker) throws ExecutionException {
        return dataSourceCache.get(looker.toString(), () -> {
            switch (looker.getDbase()) {
                case PARTITION:
                    return partitionConfig.builder(looker.getIndex());
                case JDBC:
                    return jdbcConfig;
                case ORACLE:
                    return oracleConfig;
                default:
                    return null;
            }
        });
    }

}
