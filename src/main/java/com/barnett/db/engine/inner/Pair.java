package com.barnett.db.engine.inner;

public class Pair {
    private String key;
    private String value;
    public Pair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }
    public Pair() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
