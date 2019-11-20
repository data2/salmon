package com.data2.salmon;

import com.data2.salmon.common.util.StringUtils;
import com.google.common.base.Splitter;

import java.util.HashMap;
import java.util.Map;

public class TextFunction {
    private static String blank = " ";

    public static String excute(String contents, String key) {
        Iterable<String> lines = Splitter.on('\n').trimResults().split(contents);
        StringBuffer res = new StringBuffer();
        boolean tag = false;
        for (String line : lines) {
            line = line.trim();
            if (line.length() == 0)
                continue;
            if (line.startsWith("--") && line.contains(addDecorate(key))) {
                tag = true;
                continue;
            }
            if (line.startsWith("--")) {
                tag = false;
            }
            if (tag) {
                res.append(blank + line.trim());
            }
        }

        return res.toString();
    }

    private static CharSequence addDecorate(String key) {
        return "<" + key + ">";
    }

    /**
     * contain something.
     *
     * @param contents
     * @param key
     * @return
     */
    public static Map<String, Object> contain(String contents, String key) {
        Iterable<String> lines = Splitter.on(';').trimResults().split(contents);
        Map<String, Object> map = new HashMap<String, Object>();
        String[] ruler = null;
        for (String line : lines) {
            line = line.trim();
            ruler = line.substring(line.indexOf("(") + 1, line.length() - 1).split(",");
            if (line.length() == 0)
                continue;
            if (line.contains(key) || line.contains(key.toUpperCase())
                    || StringUtils.containAllcharIgnoreCase(key, ruler[0].toCharArray())) {
                clearBlank(ruler);
                map.put("Ruler", ruler);
                map.put("Method", line.substring(0, line.indexOf("(")).trim());
                return map;
            }
        }
        return null;
    }

    private static void clearBlank(String[] ruler) {
        for (int i = 0; i < ruler.length; i++) {
            ruler[i] = ruler[i].trim();
        }

    }

}
