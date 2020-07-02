package com.data2.salmon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Component
public class ParseConfig {

    @Autowired
    private SqlConfigCache sqlConfigCache;

    public String parse(String file, ExecuteSql executeSql) {
        String mapperFile = "mapper/".concat(file).concat(".salmon");
        return (String) sqlConfigCache.getSource(mapperFile, executeSql.getSqlId());
    }

}
