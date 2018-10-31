package com.muskteer.dico;

import org.springframework.stereotype.Component;

import java.util.Map;

public class DicoScanUtil {

    public static String achieveScan() {
        return (String) ((Map) ((Map) YamlUtil.yaml("application.yml").get("spring")).get("dico")).get("scan");
    }
}
