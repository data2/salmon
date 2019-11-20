package com.data2.salmon;

import com.alibaba.druid.pool.DruidDataSource;

public interface Config {

    DruidDataSource builder(String dbid);

}
