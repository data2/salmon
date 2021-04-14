package com.data2.salmon.core.common.util;

import java.util.Objects;

/**
 * @author leewow
 */
public class ArrUtils {

    public static <T> boolean inArray(T obj, T... objs) {
        if (obj instanceof String) {
            for (T object : objs) {
                if (obj.equals(object)) {
                    return true;
                }
            }
        } else {
            for (T object : objs) {
                if (Objects.equals(obj, object)) {
                    return true;
                }
            }
        }
        return false;
    }

}
