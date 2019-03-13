package com.gupaoedu.vip.pattern.Observer;

public class Client {
    public static void main(String[] args) {
        //创建微博大V 王呆呆
        Observable 王呆呆 = new Observable();

        //许嵩关注了王呆呆
        王呆呆.addObserver(new XuSong());
        //王呆呆老公关注了王呆呆
        王呆呆.addObserver(new MaoShuangLing());

        王呆呆.updateWeibo();
    }
}
