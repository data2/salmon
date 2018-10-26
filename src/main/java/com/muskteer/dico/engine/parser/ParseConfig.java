package com.muskteer.dico.engine.parser;

import java.io.IOException;

import com.muskteer.dico.base.exception.InnerException;
import com.muskteer.dico.engine.cache.DicoSqlSource;
import com.muskteer.dico.engine.inner.sql.DicoSql;

/**
 * parse co file.
 * 
 * @author wanglei
 * @since 2016.8.3
 * @modify 2016.8.5
 */
public class ParseConfig {

    public static String parse(String classloaderfile, DicoSql dicoSql) throws InnerException, IOException {
        return (String) new DicoSqlSource().getSource(classloaderfile, dicoSql.getSqlId());
    }

}
