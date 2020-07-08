package com.data2.salmon.core.engine.druid;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author leewow
 */
public interface Config {

    /**
     * @param dbid
     * @return
     */
    DruidDataSource builder(String dbid);

}
