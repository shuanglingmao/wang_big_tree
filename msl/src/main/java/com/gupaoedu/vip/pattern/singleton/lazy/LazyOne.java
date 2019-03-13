package com.gupaoedu.vip.pattern.singleton.lazy;

public class LazyOne {
    private LazyOne(){}

    private static LazyOne lazy = null;

    public static LazyOne getInstance() {
        if (lazy == null) {
            lazy = new LazyOne();
        }
        return lazy;
    }
}
