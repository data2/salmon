package com.data2.salmon;

import java.lang.reflect.Proxy;

/**
 * impl of co add proxy.
 * 
 */
public class Factory {

    public static Dico getSource(Object impl) {
        return (Dico) Proxy.newProxyInstance(Dico.class.getClassLoader(), new Class<?>[] { Dico.class },
                new RunColInvocationHandler(impl));
    }

}
