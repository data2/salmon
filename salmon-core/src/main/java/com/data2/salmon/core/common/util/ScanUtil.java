package com.data2.salmon.core.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

/**
 * @author leewow
 */
@Slf4j
public class ScanUtil {

    static LinkedHashMap cfg = (LinkedHashMap) PropertiesUtil.yaml("application.yml");

    public static String achieveScan() {
        return (String) getMap(getMap(cfg, "spring"), "salmon").get("scan");
    }

    private static LinkedHashMap getMap(LinkedHashMap map, String key) {
        return (LinkedHashMap) map.get(key);
    }

    public static void main(String[] args) {
        System.out.println(achieveScan());
    }

}
