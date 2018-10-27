package com.muskteer.dico.parser;

import com.muskteer.dico.cache.DicoSqlSource;
import com.muskteer.dico.inner.DicoSql;

/**
 * parse co file
 * @author wanglei
 * @since 2016.8.3
 * @modify 2016.8.5
 */
public class ParseConfig {

    public static String parse(String classloaderfile, DicoSql dicoSql) {
        return (String) new DicoSqlSource().getSource(classloaderfile, dicoSql.getSqlId());
    }

}
