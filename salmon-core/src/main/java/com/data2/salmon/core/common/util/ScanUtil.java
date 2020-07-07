package com.data2.salmon.core.common.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author leewow
 */
@Slf4j
public class ScanUtil {

    static LinkedHashMap cfg = (LinkedHashMap) yaml("application.yml");

    public static String achieveScan() {
        return (String) getMap(getMap(cfg, "spring"), "salmon").get("scan");
    }

    private static LinkedHashMap getMap(LinkedHashMap map, String key) {
        return (LinkedHashMap) map.get(key);
    }

    public static void main(String[] args) {
        System.out.println(achieveScan());
    }

    public static Map<String, String> yaml(String file) {
        Map<String, String> maps = Maps.newHashMap();
        try {
            Yaml yaml = new Yaml();
            URL url = ScanUtil.class.getClassLoader().getResource(file);
            if (url != null) {
                maps = (Map) yaml.load(new FileInputStream(url.getFile()));
                System.out.println(maps);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("read properties exception,{}", e.getMessage());
        }
        return maps;
    }
}
