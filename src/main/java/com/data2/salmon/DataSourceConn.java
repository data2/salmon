package com.data2.salmon;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
public class DataSourceConn implements EngineConnection {


    private Cache<DataSourceLooker, DruidDataSource> dataSourceCache = CacheBuilder.newBuilder().build();

    @Autowired
    public DicoPartitionConfig dicoPartitionConfig;

    @Autowired
    public DicoJdbcConfig dicoJdbcConfig;

    @Autowired
    public DicoOracleConfig dicoOracleConfig;

    @Override
    public DruidDataSource getSource(final DataSourceLooker dataSourceLooker) {
        try {
            return dataSourceCache.get(dataSourceLooker, new Callable<DruidDataSource>() {
                @Override
                public DruidDataSource call() throws Exception {
                    DicoConfig config = null;
                    switch (dataSourceLooker.dbase) {
                        case "PARTITION":
                            config = dicoPartitionConfig;
                            break;
                        case "JDBC":
                            config = dicoJdbcConfig;
                            break;
                        case "ORACLE":
                            config = dicoOracleConfig;
                            break;
                    }
                    return config.builder(dataSourceLooker.dbid);
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }


        // int myInitialSize = parseIntParameter(initialSize, database);
        // if (myInitialSize > 0)
        // dataSource.setInitialSize(myInitialSize);
        //
        // int myMinIdle = parseIntParameter(minIdle, database);
        // if (myMinIdle > 0) dataSource.setMinIdle(myMinIdle);
        //
        // int myMaxActive = parseIntParameter(maxActive, database);
        // if (myMaxActive > 0) dataSource.setMaxActive(myMaxActive);
        //
        // int myMaxWait = parseIntParameter(maxWait, database);
        // if (myMaxWait > 0) dataSource.setMaxWait(myMaxWait);
        //
        // int myTimeBetweenEvictionRunsMillis =
        // parseIntParameter(timeBetweenEvictionRunsMillis, database);
        // if (myTimeBetweenEvictionRunsMillis > 0)
        // dataSource.setTimeBetweenEvictionRunsMillis(myTimeBetweenEvictionRunsMillis);
        //
        // int myMinEvictableIdleTimeMillis =
        // parseIntParameter(minEvictableIdleTimeMillis, database);
        // if (myMinEvictableIdleTimeMillis > 0)
        // dataSource.setMinEvictableIdleTimeMillis(myMinEvictableIdleTimeMillis);
        //
        // String myValidationQuery = parseParameter(validationQuery,
        // database);
        // if (myValidationQuery.length() > 0)
        // dataSource.setValidationQuery(myValidationQuery);
        //
        // String myTestWhileIdle = parseParameter(testWhileIdle,
        // database);
        // if (myTestWhileIdle.length() > 0) {
        // dataSource.setTestWhileIdle(Boolean.parseBoolean(myTestWhileIdle));
        // }
        //
        // String myTestOnBorrow = parseParameter(testOnBorrow,
        // database);
        // if (myTestOnBorrow.length() > 0) {
        // dataSource.setTestOnBorrow(Boolean.parseBoolean(myTestOnBorrow));
        // }
        //
        // String myTestOnReturn = parseParameter(testOnReturn,
        // database);
        // if (myTestOnReturn.length() > 0) {
        // dataSource.setTestOnReturn(Boolean.parseBoolean(myTestOnReturn));
        // }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
