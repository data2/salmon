package com.data2.salmon.core.engine.domain;

import com.data2.salmon.core.engine.enums.DataBase;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author data2
 * @description
 * @date 2020/7/9 上午10:12
 */
@Data
@AllArgsConstructor
public class Looker {
    private DataBase dbase;
    private String index;

    @Override
    public String toString() {
        if (index == null) {
            return dbase.name();
        } else {
            return dbase.name().concat(index);
        }
    }
}
