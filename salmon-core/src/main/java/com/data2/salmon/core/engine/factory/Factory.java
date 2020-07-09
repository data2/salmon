package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.Salmon;
import com.data2.salmon.core.engine.intercept.RunColInvocationHandlerProxy;

import java.lang.reflect.Proxy;

public class Factory {

    public static Salmon getSource(Object obj) {
        return (Salmon) Proxy.newProxyInstance(Salmon.class.getClassLoader(), new Class<?>[]{Salmon.class},
                new RunColInvocationHandlerProxy(obj));
    }

}
