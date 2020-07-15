package com.msl.aop;



import java.lang.reflect.Proxy;

/**
 * @author msl on 2020/5/29.
 */
public class Invoker {

    public Object getInstance(Class<?> cls){
        MethodProxy invocationHandler = new MethodProxy();
        Object newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls },
                invocationHandler);
        return (Object)newProxyInstance;
    }


}
