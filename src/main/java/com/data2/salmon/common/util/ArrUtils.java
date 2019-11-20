package com.data2.salmon.common.util;

public abstract class ArrUtils {

    @SafeVarargs
    public static boolean inArray(String obj, String... objs) {
        for (String o : objs) {
            if (obj.equals(o)) {
                return true;
            }
        }

        return false;
    }

    public static <T> boolean isEmpty(T[] objs) {
        return objs == null || objs.length == 0;
    }
}
