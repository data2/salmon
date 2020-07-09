package com.data2.salmon.core.engine.config;

import com.data2.salmon.core.engine.cache.impl.SqlConfigCache;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Component
@Slf4j
public class ParseConfig {

    @Autowired
    private SqlConfigCache sqlConfigCache;

    public String parse(String file, ExecuteSql executeSql) {
        String mapperFile = "mapper/".concat(file).concat(".salmon");
        return (String) sqlConfigCache.getSource(mapperFile, executeSql.getSqlId());
    }

}
