package com.data2.salmon;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RunColInvocationHandler implements InvocationHandler {

    private Object impl;
    private ParamsInterceptor before;
    private ResultOutputInterceptor after;

    public RunColInvocationHandler(Object impl) {
        this.impl = impl;
        before = new ParamsInterceptor();
        after = new ResultOutputInterceptor();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before.execute(impl);
        Object obj = method.invoke(impl, args);
        after.execute(impl);
        return obj;
    }

}
