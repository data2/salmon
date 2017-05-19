package com.barnett.db.engine.parser;

import java.io.IOException;

import com.barnett.db.base.exception.InnerException;
import com.barnett.db.engine.cache.DicoSqlSource;
import com.barnett.db.engine.inner.sql.DicoSql;
/**
 * parse co file.
 * @author wanglei
 * @since 2016.8.3
 * @modify 2016.8.5
 */
public class ParseConfig {

    public static String parse(String classloaderfile, DicoSql dicoSql) 
            throws InnerException, IOException {
        return  (String) new DicoSqlSource().getSource(classloaderfile, dicoSql.getSqlId());
    }
    
}
