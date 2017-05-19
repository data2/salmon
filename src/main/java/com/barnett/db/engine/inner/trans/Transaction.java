package com.barnett.db.engine.inner.trans;

public interface Transaction {
    
    void start();
    
    void commit();
}
