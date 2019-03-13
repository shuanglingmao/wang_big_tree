package com.gupaoedu.vip.pattern.factory.simple;

import com.gupaoedu.vip.pattern.factory.Milk;

public class SimpleFactory {

    public Milk getMilk(String name) {
        if ("特仑苏".equals(name)) {
            return new Telunsu();
        } if ("伊利".equals(name)) {
            return new YiLi();
        } if ("六个核桃".equals(name)) {
            return new SixHeTao();
        } else {
            System.out.println("我们不能失生产所需的产品");
            return null;
        }

        //添加三鹿 要加三鹿

    }

}
