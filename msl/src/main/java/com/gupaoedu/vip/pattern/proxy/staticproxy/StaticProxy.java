package com.gupaoedu.vip.pattern.proxy.staticproxy;

public class StaticProxy implements XXXService {
    private XXXService xxxService;

    public StaticProxy(XXXService xxxService) {
        this.xxxService = xxxService;
    }

    /**
     * 真正的业务逻辑 还是目标类 xxxService去实现
     * 代理类只是在目标类的前后  增加一个方法
     */
    @Override
    public void addUser() {
        befor();
        xxxService.addUser();
        after();
    }

    @Override
    public void deleteUser(Integer userId) {
        befor();
        xxxService.deleteUser(userId);
        after();
    }

    private void befor() {
        System.out.println("方法执行前~现在是北京时间:2019-03-11");
    }
    private void after() {
        System.out.println("方法执行后~现在是北京时间:2019-03-11");
    }

}
