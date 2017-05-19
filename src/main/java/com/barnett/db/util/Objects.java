package com.barnett.db.util;

import org.junit.Test;

import com.barnett.db.base.exception.InnerException;

public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) throws InnerException {
        return (T) (t != null ? t : t2);
    }
    
    @Test
    public void testName() throws Exception {
        ClassLoader b = Objects.priorityUseFirst(
                Thread.currentThread().getContextClassLoader(),
                DicoClassLoader.class.getClassLoader());
        System.out.println(b.getResourceAsStream("conf/db-partion-engine.properties"));
    }
}
