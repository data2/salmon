package com.muskteer.dico;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.InitializingBean;

public interface EngineConnection extends InitializingBean {
    DruidDataSource getSource(DataSourceLooker looker);
}
