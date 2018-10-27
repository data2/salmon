package com.muskteer.dico.engine.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DicoPair {
    private String key;
    private String value;

    public DicoPair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public DicoPair() {
        super();
    }

}
