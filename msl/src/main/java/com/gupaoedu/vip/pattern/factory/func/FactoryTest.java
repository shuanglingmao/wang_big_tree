package com.gupaoedu.vip.pattern.factory.func;

public class FactoryTest {
    public static void main(String[] args) {

        //需要用户 选择工厂  工厂负责生产细节  配方   增加用户复杂度
        System.out.println(new TelunsuFactory().getMilk());
        System.out.println(new YiLiFactory().getMilk());
    }
}
