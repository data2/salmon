package com.data2.salmon.core.engine.config;


/**
 * @author data2
 */
public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) {
        return t != null ? t : t2;
    }

}
