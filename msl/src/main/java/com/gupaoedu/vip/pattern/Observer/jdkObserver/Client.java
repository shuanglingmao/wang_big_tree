package com.gupaoedu.vip.pattern.Observer.jdkObserver;

public class Client {
    public static void main(String[] args) {
        //两个作者
        Writer wdd = new Writer("王呆呆");
        Writer msl = new Writer("毛双领");

        //四个读者
        Reader wqh = new Reader("王麒浩");
        Reader wyh = new Reader("王一涵");
        Reader wym = new Reader("王远盟");
        Reader zyf = new Reader("朱一凡");
        Reader mzl = new Reader("毛占领" );

        //王一涵和王麒浩关注了毛双领
        wyh.subscribe("王呆呆");
        wqh.subscribe("王呆呆");
        wym.subscribe("王呆呆");
        zyf.subscribe("王呆呆");


        //王远盟和朱一凡关注了王呆呆
        wym.subscribe("毛双领");
        zyf.subscribe("毛双领");
        mzl.subscribe("毛双领" );
        zyf.unSubscribe("毛双领");

        msl.publish("老母猪的喂养与繁殖");
        wdd.publish("Java从入门到放弃");



    }
}
