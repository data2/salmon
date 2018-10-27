package com.muskteer.dico1.engine.factory;

import java.lang.reflect.Proxy;

import com.muskteer.dico1.engine.proxy.RunColInvocationHandler;
import com.muskteer.dico1.engine.inner.Co;

/**
 * impl of co add proxy.
 * 
 */
public class Factory {

    public static Co getSource(Object impl) {
        return (Co) Proxy.newProxyInstance(Co.class.getClassLoader(), new Class<?>[] { Co.class },
                new RunColInvocationHandler(impl));
    }

}
