package com.data2.salmon.core.engine.trans;

/**
 * @author leewow
 */
public interface Transaction {

    void start();

    void commit();
}
