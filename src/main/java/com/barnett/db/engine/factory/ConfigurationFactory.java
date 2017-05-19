package com.barnett.db.engine.factory;

import java.util.Properties;

import com.barnett.db.base.exception.InnerException;
import com.barnett.db.config.KeysConstant;
import com.barnett.db.util.DicoClassLoader;
import com.barnett.db.util.PropertiesUtil;

public class ConfigurationFactory {

    public static Properties properties = new Properties();;

    public static String getProperty(String key) {
        return (String) properties.get(key);
    }

    static {
        try {
            PropertiesUtil.loader(properties,
            DicoClassLoader.loadFile(KeysConstant.getSysName()));
        } catch (InnerException e) {
            e.printStackTrace();
        }
    }
    
}
