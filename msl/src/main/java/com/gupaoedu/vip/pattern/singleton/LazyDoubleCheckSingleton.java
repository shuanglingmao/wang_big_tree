package com.gupaoedu.vip.pattern.singleton;

public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton lazy = null;

    private volatile static boolean initialized = false;

    private LazyDoubleCheckSingleton() {
        synchronized (LazyDoubleCheckSingleton.class) {
            if (!initialized) {
                initialized = !initialized;
            }else {
                throw new RuntimeException("不允许创建多个实例");
            }
        }
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (lazy == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazy == null) {
                    lazy = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazy;
    }
}
