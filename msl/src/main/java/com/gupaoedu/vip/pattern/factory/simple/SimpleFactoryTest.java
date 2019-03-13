package com.gupaoedu.vip.pattern.factory.simple;

public class SimpleFactoryTest {

    public static void main(String[] args) {

        //new的过程实际上是一个比较复杂的过程
        // 有了rmb  就不需要自己new
//        System.out.println(new Telunsu().getName());

        //小作坊生产模式
        //用户本身不再关心生产的过程，而只需要关心生产的结果
        //假如： 特仑苏、伊利、六个核桃   成分配比都是不一样的
        SimpleFactory factory = new SimpleFactory();
        //把用户的需求告诉工厂
        //创建产品的过程（new Object()）隐藏，对用户而言 不清楚是怎么产生
        System.out.println(factory.getMilk("六个核桃"));

        //可能会出错
        System.out.println("aaa");
    }

}
