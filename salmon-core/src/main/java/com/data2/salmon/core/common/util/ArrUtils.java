package com.data2.salmon.core.common.util;

/**
 * @author leewow
 */
public class ArrUtils {

    public static boolean inArray(String obj, String... objs) {
        for (String object : objs) {
            if (obj.equals(object)) {
                return true;
            }
        }
        return false;
    }

}
