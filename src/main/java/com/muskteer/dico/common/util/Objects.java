package com.muskteer.dico.common.util;


import com.muskteer.dico.common.exception.InnerException;

public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) throws InnerException {
        return (T) (t != null ? t : t2);
    }

    public void testName() throws Exception {
        ClassLoader b = Objects.priorityUseFirst(Thread.currentThread().getContextClassLoader(),
                DicoClassLoader.class.getClassLoader());
        System.out.println(b.getResourceAsStream("dico.properties"));
    }
}
