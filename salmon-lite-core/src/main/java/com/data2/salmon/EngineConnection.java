package com.data2.salmon;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author leewow
 */
public interface EngineConnection extends InitializingBean {
    DruidDataSource getSource(DataSourceLooker looker);
}