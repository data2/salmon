package com.data2.salmon.core.common.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * @author leewow
 */
@Slf4j
public class PropertiesUtil {

    public static void loader(Properties properties, InputStream in) {
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("load properties exception, occur with reason {}", e.getMessage());
        }
    }

    public static Map<String, String> yaml(String file) {
        Map<String, String> maps = Maps.newHashMap();
        try {
            Yaml yaml = new Yaml();
            URL url = PropertiesUtil.class.getClassLoader().getResource(file);
            if (url != null) {
                maps = (Map) yaml.load(new FileInputStream(url.getFile()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("read properties exception,{}", e.getMessage());
        }
        return maps;
    }
}
