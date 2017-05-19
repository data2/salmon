package com.barnett.db.config;

import java.io.IOException;
import java.util.Properties;

import com.barnett.db.base.exception.InnerException;
import com.barnett.db.util.DicoClassLoader;

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InnerException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
