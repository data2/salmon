package com.data2.salmon.core;


/**
 * @author leewow
 */
public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) {
        return (T) (t != null ? t : t2);
    }

}
