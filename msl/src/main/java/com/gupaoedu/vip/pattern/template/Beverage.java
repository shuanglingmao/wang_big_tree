package com.gupaoedu.vip.pattern.template;

/**
 * 饮料  抽象基类，为所有的子类提供一个模版
 *
 *  OO设计原则之一就是分离可变和不变的部分并把可变的部分封装起来，
 *  哪些步骤的实现是一样的，哪些是可变的。
 * 我们把不变的部分提取出来并放到超类中让所有子类共享其行为，
 * 同时我们把可变部分的具体实现延迟到子类中，让子类来自行决定如何实现。
 * 模板方法模式在一个方法中定义一个算法的骨架，
 * 而将一些步骤的实现延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中某些步骤的具体实现。
 */
public abstract class Beverage {

    /**
     * 只做饮料
     * 具体的模版方法，要用final关键字进行修饰,避免被子类覆盖
     */
    public final void prepareBeverage() {
        //1.烧开水
        boilWater();
        //2.把  原料  放入杯中
        putYuanliao();
        //3.杯中加水
        pourWater();
        //4.搅拌均匀
        stir();
        //5.喝
        drink();

    }

    private void boilWater() {
        System.out.println("烧开水");
    }

    protected abstract void putYuanliao();

    private void pourWater() {
        System.out.println("把热水倒入杯中");
    }

    private void stir(){
        System.out.println("搅拌均匀");
    }

    private void drink(){
        System.out.println("开喝");
    }










}
