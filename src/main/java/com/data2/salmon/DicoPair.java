package com.data2.salmon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DicoPair {
    private String key;
    private String value;
    public DicoPair(){}
    public DicoPair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

}
