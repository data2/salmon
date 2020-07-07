package com.data2.salmon.core.common.util;

/**
 * @author leewow
 */
public class ArrUtils {

    public static boolean inArray(String obj, String... objs) {
        for (String o : objs) {
            if (obj.equals(o)) {
                return true;
            }
        }
        return false;
    }

}
