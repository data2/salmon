package com.data2.salmon.core.engine.config;

import com.data2.salmon.core.common.util.PropertiesUtil;

import java.util.LinkedHashMap;

/**
 * @author data2
 */
public class ScannerPackage {

    public static String achieveScan() {
        return (String) getMap(getMap((LinkedHashMap) PropertiesUtil.yaml("application.yml"), "spring"), "salmon").get("scan");
    }

    private static LinkedHashMap getMap(LinkedHashMap map, String key) {
        return (LinkedHashMap) map.get(key);
    }

}
