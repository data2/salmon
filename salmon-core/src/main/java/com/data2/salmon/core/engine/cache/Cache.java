package com.data2.salmon.core.engine.cache;

/**
 * @author leewow
 */
public interface Cache {

    /**
     * @param obj
     */
    void remove(String obj);

    /**
     *
     */
    void removeAll();
}
