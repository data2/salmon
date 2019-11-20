package com.data2.salmon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParseConfig {

    @Autowired
    private SqlConfigCache dicoSqlConfigCache;

    public String parse(String file, ExecuteSql dicoSql)  {
        String mapperFile = "mapper/".concat(file).concat(".salmon");
        return (String) dicoSqlConfigCache.getSource(mapperFile, dicoSql.getSqlId());
    }

}
