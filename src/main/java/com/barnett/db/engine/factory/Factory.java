/**
 * copyright@com.barnett
 */
package com.barnett.db.engine.factory;

import java.lang.reflect.Proxy;

import com.barnett.db.engine.inner.co.Co;
import com.barnett.db.engine.proxy.RunColInvocationHandler;

/**
 * impl of co add proxy.
 * @author wanglei
 * @since 2016.8.3
 *
 */
public class Factory  {

    public static Co getSource(Object impl){
        return (Co) Proxy.newProxyInstance(Co.class
                .getClassLoader(), new Class<?>[] { Co.class},
                new RunColInvocationHandler(impl));
    }
    
    
}
