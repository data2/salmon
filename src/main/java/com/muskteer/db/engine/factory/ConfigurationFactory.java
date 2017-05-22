package com.muskteer.db.engine.factory;

import java.util.Properties;

import com.muskteer.db.base.exception.InnerException;
import com.muskteer.db.config.KeysConstant;
import com.muskteer.db.util.DicoClassLoader;
import com.muskteer.db.util.PropertiesUtil;

public class ConfigurationFactory {

    public static Properties properties = new Properties();;

    public static String getProperty(String key) {
        return (String) properties.get(key);
    }

    static {
        try {
            PropertiesUtil.loader(properties, 
                DicoClassLoader.loadFile(KeysConstant.getDefaultDbaseInitFileName()));
        } catch (InnerException e) {
            e.printStackTrace();
        }
    }

}
