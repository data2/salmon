package com.data2.salmon.core;

import java.io.InputStream;

/**
 * @author leewow
 */
public class ConfigurationLoader {

    public static InputStream loadFile(String file) {
        return getClassLoader().getResourceAsStream(file);
    }

    public static ClassLoader getClassLoader()  {
        return Objects.priorityUseFirst(Thread.currentThread().getContextClassLoader(),
                ConfigurationLoader.class.getClassLoader());
    }

    public static ClassLoader getContextLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static String getCurrClassName() {
        String result = null;
        StackTraceElement[] o = Thread.currentThread().getStackTrace();
        for (int i = o.length - 1; i >= 0; i--) {
            if (!o[i].getClassName().contains("Thread")) {
                result = o[i].getClassName();
                break;
            }
        }
        return result;
    }

}
