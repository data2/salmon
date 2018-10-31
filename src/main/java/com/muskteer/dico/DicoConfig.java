package com.muskteer.dico;

import com.alibaba.druid.pool.DruidDataSource;

public interface DicoConfig {

    DruidDataSource builder(String dbid);

}
