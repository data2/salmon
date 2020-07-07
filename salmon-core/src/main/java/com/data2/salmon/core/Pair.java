package com.data2.salmon.core;

import lombok.Data;

/**
 * @author leewow
 */
@Data
public class Pair {
    private String key;
    private String value;

    public Pair() {
    }

    public Pair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

}
