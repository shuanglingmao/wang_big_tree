package com.gupaoedu.vip.pattern.Observer;

public class Client {
    public static void main(String[] args) {
        Observable ob = new Observable();

        ob.addObserver(new XuSong());
        ob.addObserver(new MaoShuangLing());

        ob.updateWeibo();
    }
}
