package com.data2.salmon;

import java.lang.reflect.Proxy;

/**
 * impl of co add proxy.
 * 
 */
public class Factory {

    public static Salmon getSource(Object impl) {
        return (Salmon) Proxy.newProxyInstance(Salmon.class.getClassLoader(), new Class<?>[] { Salmon.class },
                new RunColInvocationHandler(impl));
    }

}
