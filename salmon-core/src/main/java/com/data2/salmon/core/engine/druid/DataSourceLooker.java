package com.data2.salmon.core.engine.druid;

import com.data2.salmon.core.engine.enums.DataBase;
import org.springframework.util.StringUtils;

/**
 * @author leewow
 */
public class DataSourceLooker {
    public DataBase dbase;
    public String dbid;

    public DataSourceLooker(DataBase dbase, String dbid) {
        this.dbase = dbase;
        this.dbid = dbid;
    }

    @Override
    public String toString() {
        return dbase.name().concat(StringUtils.isEmpty(dbid) ? "" : dbid);
    }
}