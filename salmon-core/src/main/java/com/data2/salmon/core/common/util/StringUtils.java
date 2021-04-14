package com.data2.salmon.core.common.util;

/**
 * @author leewow
 */
public class StringUtils {

    public static boolean containAllcharIgnoreCase(String tmp, char[] chars) {
        String str;
        for (char c : chars) {
            str = String.valueOf(c);
            if (!tmp.contains(str) && !tmp.toUpperCase().contains(str)) {
                return false;
            }
        }
        return true;
    }

}
