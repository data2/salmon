package com.muskteer.dico;


public class Objects {

    public static <T> T priorityUseFirst(T t, T t2) throws DicoException {
        return (T) (t != null ? t : t2);
    }

    public void testName() throws Exception {
        ClassLoader b = Objects.priorityUseFirst(Thread.currentThread().getContextClassLoader(),
                DicoClassLoader.class.getClassLoader());
        System.out.println(b.getResourceAsStream("dico.properties"));
    }
}
