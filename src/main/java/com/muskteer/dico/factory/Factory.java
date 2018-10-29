package com.muskteer.dico.factory;

import java.lang.reflect.Proxy;

import com.muskteer.dico.proxy.RunColInvocationHandler;
import com.muskteer.dico.inner.Dico;

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
