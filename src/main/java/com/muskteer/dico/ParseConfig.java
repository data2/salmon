package com.muskteer.dico;

import com.muskteer.dico.DicoSqlConfigCache;
import com.muskteer.dico.DicoException;
import com.muskteer.dico.DicoExecuteSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParseConfig {

    @Autowired
    private DicoSqlConfigCache dicoSqlConfigCache;

    public String parse(String file, DicoExecuteSql dicoSql) throws DicoException {
        String mapperFile = "mapper/".concat(file).concat(".co");
        return (String) dicoSqlConfigCache.getSource(mapperFile, dicoSql.getSqlId());
    }

}
