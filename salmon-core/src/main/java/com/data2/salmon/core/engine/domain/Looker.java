package com.data2.salmon.core.engine.domain;

import com.data2.salmon.core.engine.enums.DataBase;
import lombok.Data;

/**
 * @author leewow
 * @description
 * @date 2020/7/9 上午10:12
 */
@Data
public class Looker {
    private DataBase dbase;
    private String index;

    public Looker(DataBase dbase, String index) {
        this.dbase = dbase;
        this.index = index;
    }

    @Override
    public String toString() {
        if (index == null) {
            return dbase.getCode();
        } else {
            return dbase.getCode().concat(index);
        }
    }
}
