package com.data2.salmon.core.engine.druid;

import com.data2.salmon.core.engine.enums.DataBase;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author leewow
 */
@Data
public class DataSourceLooker {
    public DataBase dbase;
    public String dbid;

    public DataSourceLooker(DataBase dbase, String dbid) {
        this.dbase = dbase;
        this.dbid = dbid;
    }

    @Override
    public String toString() {
        return dbase.getCode().concat(StringUtils.isEmpty(dbid) ? "" : dbid);
    }
}