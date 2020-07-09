package com.data2.salmon.core.engine.intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author leewow
 */
public class RunColInvocationHandlerProxy implements InvocationHandler {

    private Object obj;
    private ParamsInterceptor before;
    private ResultOutputInterceptor after;

    public RunColInvocationHandlerProxy(Object obj) {
        this.obj = obj;
        before = new ParamsInterceptor();
        after = new ResultOutputInterceptor();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before.execute(obj);
        Object result = method.invoke(obj, args);
        after.execute(obj);
        return obj;
    }

}
