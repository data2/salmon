package com.muskteer.dico1.engine.config;

import java.util.Properties;

import com.muskteer.dico1.common.util.DicoClassLoader;

public class DicoConfig implements CoConfig {

    private Properties properties;

    public DicoConfig(Properties properties) {
        this.properties = properties;
    }

    public DicoConfig(String file) {
        this(initProps(file));
    }

    @Override
    public String getValue(String key) {
        return (String) properties.get(key);
    }

    private static Properties initProps(String file) {
        Properties properties = new Properties();
        try {
            properties.load(DicoClassLoader.loadFile(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
