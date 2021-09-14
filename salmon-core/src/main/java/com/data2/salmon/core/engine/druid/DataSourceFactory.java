package com.data2.salmon.core.engine.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.engine.config.PartitionConfig;
import com.data2.salmon.core.engine.domain.Looker;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author data2
 */
@Component
@Slf4j
public class DataSourceFactory implements SourceFactory {

    @Autowired(required = false)
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
            if (Objects.nonNull(jdbcConfig) || Objects.nonNull(partitionConfig)) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            if (Objects.nonNull(oracleConfig)) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
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
                case partition:
                    return partitionConfig.builder(looker.getIndex());
                case jdbc:
                    return jdbcConfig;
                case oracle:
                    return oracleConfig;
                default:
                    return null;
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        try {
            close();
        } catch (Exception e) {
        }
    }

    private void close(Object... objs) {
        dataSourceCache.asMap().values().forEach(new Consumer<DruidDataSource>() {
            @Override
            public void accept(DruidDataSource druidDataSource) {
                try {
                    druidDataSource.close();
                } catch (Exception e) {
                }
            }
        });
    }
}
