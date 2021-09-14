package com.data2.salmon.core.engine.cache;

import org.springframework.beans.factory.DisposableBean;

/**
 * @author data2
 */
public interface Cache extends DisposableBean {

    /**
     * @param obj
     */
    void remove(String obj);

    /**
     *
     */
    void removeAll();
}
