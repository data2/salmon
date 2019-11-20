package com.data2.salmon;

import com.alibaba.druid.pool.DruidDataSource;

public interface DicoConfig {

    DruidDataSource builder(String dbid);

}
