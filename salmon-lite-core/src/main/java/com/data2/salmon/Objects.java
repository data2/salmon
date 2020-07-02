package com.data2.salmon;


/**
 * @author leewow
 */
public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) {
        return (T) (t != null ? t : t2);
    }

    public void testName() throws Exception {
        ClassLoader b = Objects.priorityUseFirst(Thread.currentThread().getContextClassLoader(),
                ConfigurationLoader.class.getClassLoader());
        System.out.println(b.getResourceAsStream("salman.properties"));
    }
}
