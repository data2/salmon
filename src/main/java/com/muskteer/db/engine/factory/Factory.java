package com.muskteer.db.engine.factory;

import java.lang.reflect.Proxy;

import com.muskteer.db.engine.proxy.RunColInvocationHandler;
import com.muskteer.db.engine.inner.co.Co;

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
