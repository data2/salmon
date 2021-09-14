package com.data2.salmon.core.engine.config;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author data2
 */
public interface Config {

    /**
     * @param index
     * @return
     */
    DruidDataSource builder(String index);

}
