package com.msl.aop;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author msl on 2020/5/29.
 */
public class MethodProxy implements InvocationHandler {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果传进来是一个已实现的具体类（本次演示略过此逻辑)
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t)
            //如果传进来的是一个接口（核心)
        } else {
            return run(method, args);
        }
        return null;
    }

    /**
     * 实现接口的核心方法
     * @param method
     * @param args
     * @return
     */
    public Object run(Method method,Object[] args){
        //TODO
        //如远程http调用
        //如远程方法调用（rmi)
        //....
        System.out.println("你的实现已经被换掉了");
        return Lists.newArrayList(1,2,3,4,5);
    }
}
