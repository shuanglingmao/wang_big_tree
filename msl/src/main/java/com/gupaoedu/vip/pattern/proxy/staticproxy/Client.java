package com.gupaoedu.vip.pattern.proxy.staticproxy;

public class Client {

    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy(new XXXServiceImpl());
        staticProxy.addUser();
        staticProxy.deleteUser(1);


        System.out.println("-----------------------");


        /***
         * 此处这个匿名内部类 就是目标类  代理类为目标类增加的额外功能
         */
        StaticProxy staticProxy1 = new StaticProxy(new XXXService() {
            @Override
            public void addUser() {
                System.out.println("添加个锤子");
            }

            @Override
            public void deleteUser(Integer userId) {
                System.out.println("删除个锤子");
            }
        });
        staticProxy1.addUser();
        staticProxy1.deleteUser(123);
    }
}
