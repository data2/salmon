package com.muskteer.dico.engine.parser;

import com.muskteer.dico.engine.cache.impl.DicoSqlSource;
import com.muskteer.dico.engine.inner.DicoSql;

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
