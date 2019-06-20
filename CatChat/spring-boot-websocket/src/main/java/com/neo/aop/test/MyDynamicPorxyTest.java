package com.neo.aop.test;

import java.lang.reflect.Proxy;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/6/18 11:19
 */
public class MyDynamicPorxyTest {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(helloService);
        HelloService proxyHelloService = (HelloService)Proxy.newProxyInstance(HelloServiceImpl.class.getClassLoader(), HelloServiceImpl.class.getInterfaces(), myInvocationHandler);
        //调用代理方法
        proxyHelloService.sayHello("赵二狗");
    }
}
