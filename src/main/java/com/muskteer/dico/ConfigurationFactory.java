package com.muskteer.dico;

import java.util.Properties;

public class ConfigurationFactory {

    public static Properties properties = new Properties();;

    public static String getProperty(String key) {
        return (String) properties.get(key);
    }

    static {
        try {
            PropertiesUtil.loader(properties,
                DicoClassLoader.loadFile(DBaseKeys.DEFAULT_DICO_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
