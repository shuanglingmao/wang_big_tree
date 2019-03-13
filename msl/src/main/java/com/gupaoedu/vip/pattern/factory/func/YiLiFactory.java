package com.gupaoedu.vip.pattern.factory.func;

import com.gupaoedu.vip.pattern.factory.Milk;
import com.gupaoedu.vip.pattern.factory.simple.YiLi;

/**
 * 事情变得专业
 */
public class YiLiFactory implements Factory {
    public Milk getMilk() {
        //可以添加配方 细节
        return new YiLi();
    }
}
