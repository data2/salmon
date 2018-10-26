package com.muskteer.dico.engine.factory;

import java.util.Properties;

import com.muskteer.dico.base.exception.InnerException;
import com.muskteer.dico.config.KeysConstant;
import com.muskteer.dico.util.DicoClassLoader;
import com.muskteer.dico.util.PropertiesUtil;

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
