package com.gupaoedu.vip.pattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    //真实对象的引用
    private Object target;

    public Object newInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

    }

    /**
     *
     * @param proxy  代理对象
     * @param method 当前调度方法
     * @param args  调度方法阐述
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        befor();
        method.invoke(target,args);
        after();
        return null;
    }


    private void befor() {
        System.out.println("在目标方法之前执行~");
    }

    private void after() {
        System.out.println("在目标方法之后执行~");
    }


}
