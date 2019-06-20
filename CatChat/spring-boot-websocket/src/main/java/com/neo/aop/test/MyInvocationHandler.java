package com.neo.aop.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/6/18 11:20
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你已经成功被我代理");
        return method.invoke(target,args);
    }
}
