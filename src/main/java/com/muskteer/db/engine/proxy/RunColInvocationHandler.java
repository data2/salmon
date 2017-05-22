package com.muskteer.db.engine.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.muskteer.db.engine.proxy.cutpoint.impl.AfterInterceptor;
import com.muskteer.db.engine.proxy.cutpoint.impl.BeforeInterceptor;

public class RunColInvocationHandler implements InvocationHandler {

    private Object impl;

    public RunColInvocationHandler(Object impl) {
        this.impl = impl;
        before = new BeforeInterceptor();
        after = new AfterInterceptor();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before.execute(impl);
        Object obj = method.invoke(impl, args);
        after.execute(impl);
        return obj;
    }

    private BeforeInterceptor before;

    private AfterInterceptor after;

}
