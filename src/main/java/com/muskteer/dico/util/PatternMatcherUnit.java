package com.muskteer.dico.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherUnit {

    public static List<String> find(String source, String regex) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);

        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    public static String[] find2(String source, String regex) {
        List<String> list = find(source, regex);
        String[] res = new String[list.size()];
        int i = 0;
        for (String tmp : list) {
            res[i++] = tmp;
        }
        return res;
    }

    public static String[] split(String source, String regex) {
        Pattern p = Pattern.compile(regex);
        return p.split(source);
    }

}
