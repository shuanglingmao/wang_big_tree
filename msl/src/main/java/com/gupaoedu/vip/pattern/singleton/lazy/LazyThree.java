package com.gupaoedu.vip.pattern.singleton.lazy;

public class LazyThree {
    //也是懒汉式单例  默认使用LazyThree的时候  会初始化内部类            在外部类被调用的时候内部类才会被加载
    //如果没有使用的话 内部类是不加载的           既不会造成 饿汉式内存浪费  也没有synchroized性能问题
    //最好的 最牛逼的  单例模式实现方式
    private volatile boolean initialized = false;
    private LazyThree() {
        //防止反射侵入
        synchronized (LazyThree.class) {
            if (!initialized) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯!");
            }
        }
    }

    //内部类在 getInstance 方法调用之前初始化          巧妙的避免了线程安全问题
    public static final LazyThree getInstance() {
        return LazyHolder.LAZY;
    }

    /**
     * 默认不加载
     */
    private static class LazyHolder {
        private static final LazyThree LAZY = new LazyThree();
    }
}
