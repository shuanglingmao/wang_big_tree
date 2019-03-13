package com.gupaoedu.vip.pattern.singleton.Hungry;

/**
 * 饿汉式
 */
public class Hungry {
    private Hungry() {}

    // 类加载 先静态 后动态   先属性  后方法  先上后下

    private static final Hungry hugry = new Hungry();

    public static Hungry getInstance() {
        return hugry;
    }
}
