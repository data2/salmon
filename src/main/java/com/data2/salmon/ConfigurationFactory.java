package com.data2.salmon;

import com.data2.salmon.common.util.PropertiesUtil;

import java.util.Properties;

public class ConfigurationFactory {

    public static Properties properties = new Properties();;

    public static String getProperty(String key) {
        return (String) properties.get(key);
    }

    static {
        try {
            PropertiesUtil.loader(properties,
                ConfigurationLoader.loadFile(DBaseKeys.DEFAULT_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
