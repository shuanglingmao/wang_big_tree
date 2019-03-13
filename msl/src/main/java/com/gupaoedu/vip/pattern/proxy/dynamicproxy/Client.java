package com.gupaoedu.vip.pattern.proxy.dynamicproxy;

import com.gupaoedu.vip.pattern.proxy.staticproxy.XXXService;
import com.gupaoedu.vip.pattern.proxy.staticproxy.XXXServiceImpl;

public class Client {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        XXXService xxxService = (XXXService) dynamicProxy.newInstance(new XXXServiceImpl());

        xxxService.addUser();
        xxxService.deleteUser(777);


        System.out.println("------------");




    }
}
