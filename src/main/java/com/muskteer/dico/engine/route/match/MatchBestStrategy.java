package com.muskteer.dico.engine.route.match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.muskteer.dico.util.StringUtils;

public class MatchBestStrategy {

    public static String exec(@SuppressWarnings("rawtypes") Map map, String key) {
        @SuppressWarnings("unchecked")
        Iterator<String> it = (Iterator<String>) map.keySet().iterator();
        char[] chars = excludeNonWord(key.toCharArray());
        String tmp = null;
        while (it.hasNext()) {
            tmp = it.next();
            if (!org.springframework.util.StringUtils.isEmpty(tmp) && StringUtils.containAllcharIgnoreCase(tmp, chars)) {
                return tmp;
            }
        }
        return null;

    }

    private static char[] excludeNonWord(char[] chars) {
        List<Character> list = new ArrayList<Character>();
        String tmp = null;
        for (char sub : chars) {
            tmp = ("" + sub).trim();
            if (!org.springframework.util.StringUtils.isEmpty(tmp) && Character.isLetter(sub)) {
                list.add(sub);
            }
        }
        chars = new char[list.size()];
        int i = 0;
        for (Character c : list) {
            chars[i++] = c;
        }
        return chars;
    }

}
