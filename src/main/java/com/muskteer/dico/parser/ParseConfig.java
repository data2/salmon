package com.muskteer.dico.parser;

import com.muskteer.dico.cache.DicoSqlConfigCache;
import com.muskteer.dico.common.exception.DicoException;
import com.muskteer.dico.inner.DicoExecuteSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ParseConfig {

    @Autowired
    private DicoSqlConfigCache dicoSqlConfigCache;

    public String parse(String file, DicoExecuteSql dicoSql) throws DicoException {
        String mapperFile = "mapper/".concat(file).concat(".co");
        if (!new File(mapperFile).exists()){
            throw new DicoException("mapper file : " + file + " not found.");
        }
        return (String) dicoSqlConfigCache.getSource(mapperFile, dicoSql.getSqlId());
    }

}
