package com.data2.salmon.core.engine.domain;

import com.data2.salmon.core.engine.enums.DataBase;
import lombok.Data;
import org.springframework.util.StringUtils;

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
        return dbase.getCode().concat(StringUtils.isEmpty(index) ? "" : index);
    }
}
