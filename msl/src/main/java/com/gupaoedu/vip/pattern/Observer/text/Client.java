package com.gupaoedu.vip.pattern.Observer.text;

/**
 * @author msl on 2020/4/10.
 */
public class Client {
    public static void main(String[] args) {
        Observable observer = new Observable();
        observer.add(new MaoShuangling());
        observer.add(new MaoZhanling());

        observer.notifyFuns();
    }
}
