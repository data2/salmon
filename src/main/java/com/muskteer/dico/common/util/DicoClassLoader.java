package com.muskteer.dico.common.util;

import java.io.InputStream;

import com.muskteer.dico.common.exception.DicoException;

public class DicoClassLoader {

    public static InputStream loadFile(String file) throws DicoException {
        return getClassLoader().getResourceAsStream(file);
    }

    public static ClassLoader getClassLoader() throws DicoException {
        return Objects.priorityUseFirst(Thread.currentThread().getContextClassLoader(),
                DicoClassLoader.class.getClassLoader());
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
