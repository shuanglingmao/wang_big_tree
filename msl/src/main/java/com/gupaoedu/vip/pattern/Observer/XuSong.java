package com.gupaoedu.vip.pattern.Observer;

/**
 * 观察者许嵩
 */
public class XuSong implements Observer {
    public void update(Observable o) {
        System.out.println("我是许嵩，我收到王呆呆发微博的通知了");
        System.out.println("我是许嵩，我要去点赞~求翻牌");
    }
}
