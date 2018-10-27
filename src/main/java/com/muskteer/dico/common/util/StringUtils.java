package com.muskteer.dico.common.util;

public class StringUtils {

    public static boolean containAllcharIgnoreCase(String tmp, char[] chars) {
        for (char c : chars) {
            if (tmp.contains(c + "") || tmp.toUpperCase().contains(c + "")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
