package com.data2.salmon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParseConfig {

    @Autowired
    private DicoSqlConfigCache dicoSqlConfigCache;

    public String parse(String file, ExecuteSql dicoSql) throws SalmonException {
        String mapperFile = "mapper/".concat(file).concat(".co");
        return (String) dicoSqlConfigCache.getSource(mapperFile, dicoSql.getSqlId());
    }

}
