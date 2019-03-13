package com.gupaoedu.vip.pattern.factory.func;

import com.gupaoedu.vip.pattern.factory.Milk;
import com.gupaoedu.vip.pattern.factory.simple.Telunsu;

/**
 * 事情变得专业  特仑苏工厂 只生产特仑苏
 */
public class TelunsuFactory implements Factory {
    public Milk getMilk() {
        //可以添加配方 细节
        return new Telunsu();
    }
}
