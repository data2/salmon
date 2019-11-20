package com.data2.salmon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {
    private String key;
    private String value;
    public Pair(){}
    public Pair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

}
