package com.gupaoedu.vip.pattern.proxy.staticproxy;

/**
 * 目标类
 * 需求：在执行方法前后打印日志
 * 如果在直接在执行逻辑前写   不利于维护     XXXServiceImpl  不能专心做自己擅长的事
 * 因此吧要增加的 功能 写到动态代理类当中
 */
public class XXXServiceImpl implements XXXService {

    @Override
    public void addUser() {
        System.out.println("添加一个用户");
    }

    @Override
    public void deleteUser(Integer userId) {
        System.out.println("删除用户ID为:"+userId+"的用户");
    }
}
