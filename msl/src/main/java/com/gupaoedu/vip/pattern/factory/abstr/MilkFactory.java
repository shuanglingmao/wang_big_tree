package com.gupaoedu.vip.pattern.factory.abstr;

import com.gupaoedu.vip.pattern.factory.Milk;
import com.gupaoedu.vip.pattern.factory.func.TelunsuFactory;
import com.gupaoedu.vip.pattern.factory.simple.SanLu;
import com.gupaoedu.vip.pattern.factory.simple.SixHeTao;
import com.gupaoedu.vip.pattern.factory.simple.YiLi;

public class MilkFactory extends AbstractFactory {
    public Milk getTulunsu() {
        //可以用工厂 也可以直接New   spring 使用的工厂
        //此处是组合
        return new TelunsuFactory().getMilk();
    }

    public Milk getYiLi() {
        return new YiLi();
    }

    public Milk getSixHeTao() {
        return new SixHeTao();
    }

    public Milk getSanLu() {
        return new SanLu();
    }
}
