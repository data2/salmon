package com.data2.salmon.common.service;

import com.data2.salmon.SalmonException;

import java.io.InputStream;

public class ConfigurationLoader {

    public static InputStream loadFile(String file) throws SalmonException {
        return getClassLoader().getResourceAsStream(file);
    }

    public static ClassLoader getClassLoader() throws SalmonException {
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
