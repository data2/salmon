package com.barnett.db.engine.cache;

public interface SqlSource extends Source{
    
    Object getSource(String obj1, String obj2);
    
}
