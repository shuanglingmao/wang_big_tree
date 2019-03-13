package com.gupaoedu.vip.pattern.factory.abstr;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        MilkFactory factory = new MilkFactory();
        //对用户而言，更加简单   用户只有选择的权利
        System.out.println(factory.getTulunsu());


        System.out.println(factory.getSanLu());
    }
}
