package com.data2.salmon.core.util;

import java.util.Map;

/**
 * @author leewow
 */
public class ScanUtil {

    public static String achieveScan() {
        return (String) ((Map) ((Map) YamlUtil.yaml("src/main/resources/application.yml").get("spring")).get("salmon")).get("scan");
    }

}
