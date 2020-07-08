package com.data2.salmon.core.engine.enums;

/**
 * @author leewow
 */

public enum DataBase {
    JDBC("jdbc", 1), ORACLE("oracle", 2), PARTITION("partition", 3);

    String code;
    int index;

    DataBase(String code, int index) {
        this.code = code;
        this.index = index;
    }

    public String getCode() {
        return code;
    }

}
