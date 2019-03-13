package com.gupaoedu.vip.pattern.template;

public class Test {
    public static void main(String[] args) {
        //咖啡
        Beverage coffee = new Coffee();
        coffee.prepareBeverage();
        System.out.println("----------------------------------");
        //豆奶粉
        Beverage milkFen = new MilkFen();
        milkFen.prepareBeverage();
    }
}
