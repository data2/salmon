package com.data2.salmon.common.util;

import java.util.Map;

public class ScanUtil {

    public static String achieveScan() {
        return (String) ((Map) ((Map) YamlUtil.yaml("application.yml").get("spring")).get("salmon")).get("scan");
    }
}
