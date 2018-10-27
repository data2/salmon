package com.muskteer.dico.parser;

import com.muskteer.dico.cache.DicoSqlConfigCache;
import com.muskteer.dico.inner.DicoExecuteSql;

public class ParseConfig {

    public static String parse(String classloaderFile, DicoExecuteSql dicoSql) {
        return (String) new DicoSqlConfigCache().getSource(classloaderFile, dicoSql.getSqlId());
    }

}
