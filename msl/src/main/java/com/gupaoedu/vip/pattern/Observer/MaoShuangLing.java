package com.gupaoedu.vip.pattern.Observer;

/**
 * 观察者毛爷爷
 */
public class MaoShuangLing implements Observer {
    public void update(Observable o) {
        System.out.println("我是王呆呆老公，我收到"+o.getClass().getSimpleName()+"发微博的通知了");
        System.out.println("我是王呆呆老公，我要去点赞~求翻牌");
    }
}
