package com.data2.salmon;

import org.springframework.util.StringUtils;

public class DataSourceLooker {
    DataBase dbase;
    String dbid;
    public DataSourceLooker(DataBase dbase,String dbid){
        this.dbase = dbase;
        this.dbid = dbid;
    }

    @Override
    public String toString() {
        return dbase.name().concat(StringUtils.isEmpty(dbid)?"":dbid);
    }
}