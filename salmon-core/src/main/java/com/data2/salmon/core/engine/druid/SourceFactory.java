package com.data2.salmon.core.engine.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ExecutionException;

/**
 * @author leewow
 */
public interface SourceFactory extends InitializingBean {
    DruidDataSource getSource(DataSourceLooker looker) throws ExecutionException;
}
