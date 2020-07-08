package com.data2.salmon.core.engine.config;

import com.data2.salmon.core.engine.cache.impl.SqlConfigCache;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author leewow
 */
@Component
public class ParseConfig {

    @Autowired
    private SqlConfigCache sqlConfigCache;

    public String parse(String file, ExecuteSql executeSql) {
        String mapperFile = "mapper/".concat(file).concat(".salmon");
        System.out.println(new File(mapperFile).exists());
        return (String) sqlConfigCache.getSource(mapperFile, executeSql.getSqlId());
    }

}
