package com.data2.salmon.core.engine.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.engine.domain.Looker;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ExecutionException;

/**
 * @author leewow
 */
public interface SourceFactory extends InitializingBean, DisposableBean {
    DruidDataSource getSource(Looker looker) throws ExecutionException;
}
