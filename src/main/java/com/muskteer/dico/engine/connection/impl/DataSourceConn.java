package com.muskteer.dico.engine.connection.impl;

import java.sql.Connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.muskteer.dico.config.DBaseKeys;
import com.muskteer.dico.engine.connection.EngineConnection;
import com.muskteer.dico.engine.factory.ConfigurationFactory;

public class DataSourceConn implements EngineConnection {

    LoadingCache<String, DruidDataSource> dataSourceCache;
    static ThreadLocal<String> childrenDB = new ThreadLocal<String>();

    @Override
    public void iniliazation() {
        dataSourceCache = CacheBuilder.newBuilder().build(new CacheLoader<String, DruidDataSource>() {

            @Override
            public DruidDataSource load(String key) throws Exception {
                DruidDataSource source = new DruidDataSource();
                // source.setUrl(parseParameter(url, database));
                source.setUsername(ConfigurationFactory.getProperty(DBaseKeys.username));
                source.setPassword(ConfigurationFactory.getProperty(DBaseKeys.password));

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

                return source;
            }
        });
    }

    @Override
    public Connection getConnection() {
        return null;
    }

}
