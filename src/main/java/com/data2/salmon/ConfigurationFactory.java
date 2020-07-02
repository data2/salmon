package com.data2.salmon;

import com.data2.salmon.common.util.PropertiesUtil;

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
