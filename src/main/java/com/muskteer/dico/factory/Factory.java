package com.muskteer.dico.factory;

import java.lang.reflect.Proxy;

import com.muskteer.dico.proxy.RunColInvocationHandler;
import com.muskteer.dico.inner.DatabaseOps;

/**
 * impl of co add proxy.
 * 
 */
public class Factory {

    public static DatabaseOps getSource(Object impl) {
        return (DatabaseOps) Proxy.newProxyInstance(DatabaseOps.class.getClassLoader(), new Class<?>[] { DatabaseOps.class },
                new RunColInvocationHandler(impl));
    }

}
