package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.engine.inter.Salmon;
import com.data2.salmon.core.engine.intercept.RunColInvocationHandlerProxy;

import java.lang.reflect.Proxy;

/**
 * @author data2
 */
public class Factory {

    public static Salmon getSource(Object obj) {
        return (Salmon) Proxy.newProxyInstance(Salmon.class.getClassLoader(), new Class<?>[]{Salmon.class},
                new RunColInvocationHandlerProxy(obj));
    }

}
