package com.data2.salmon.core;

import com.data2.salmon.core.util.PropertiesUtil;

import java.util.Properties;

/**
 * @author leewow
 */
public class ConfigurationFactory {

    public static Properties properties = new Properties();

    static {
        try {
            PropertiesUtil.loader(properties,
                    ConfigurationLoader.loadFile(DBaseKeys.DEFAULT_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
