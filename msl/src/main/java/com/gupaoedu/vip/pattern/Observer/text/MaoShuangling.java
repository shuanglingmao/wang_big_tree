package com.gupaoedu.vip.pattern.Observer.text;

/**
 * @author msl on 2020/4/10.
 */
public class MaoShuangling implements Observer {
    /**
     * description: 执行回调方法<br>
     *
     * @author: msl
     * @date: 2020/4/10 22:13
     */
    @Override
    public void execute() {
        int a = 5/0;
        System.out.println("我叫毛双领， 我接受到了开播通知");
    }
}
