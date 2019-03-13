package com.gupaoedu.vip.pattern.factory.func;

import com.gupaoedu.vip.pattern.factory.Milk;
import com.gupaoedu.vip.pattern.factory.simple.SixHeTao;

/**
 * 事情变得专业
 */
public class SixHeTaoFactory implements Factory {
    public Milk getMilk() {
        //可以添加配方 细节
        return new SixHeTao();
    }
}
