package com.barnett.db.engine.route;

import java.util.Arrays;

public class TableRulerBean {
    private String name;
    private String column;
    private String val;
    private String partionMethod;
    private String[] params;
    
    public TableRulerBean(){}
    public String getPartionMethod() {
        return partionMethod;
    }
    public void setPartionMethod(String partionMethod) {
        this.partionMethod = partionMethod;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
    public String[] getParams() {
        return params;
    }
    public void setParams(String[] params) {
        this.params = params;
    }
    public TableRulerBean(String name, String column, String val,
            String partionMethod, String[] params) {
        super();
        this.name = name;
        this.column = column;
        this.val = val;
        this.partionMethod = partionMethod;
        this.params = params;
    }
    @Override
    public String toString() {
        return "TableRulerBean [name=" + name + ", column=" + column + ", val="
                + val + ", partionMethod=" + partionMethod + ", params="
                + Arrays.toString(params) + "]";
    }
    
    
    
    
}
