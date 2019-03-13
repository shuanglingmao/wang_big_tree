package com.gupaoedu.vip.pattern.factory.abstr;

import com.gupaoedu.vip.pattern.factory.Milk;

/**
 * 抽象工厂   是用户的主入口   spring
 */
public abstract class AbstractFactory {
    //公共的逻辑
    //方便统一管理
    //方便扩展
    //如果添加一个三鹿  只许增加一个api  用户不需要管  只需要选择即可（对用户而言没有修改）    简单工厂需要增加if else  用户还要知道名字    工厂方法需要增加工厂方法类
    /**
     * 获得特仑苏品牌的牛奶
     * @return
     */
    public abstract Milk getTulunsu();
    /**
     * 获得伊利品牌的牛奶
     * @return
     */
    public abstract Milk getYiLi();
    /**
     * 获得六个核桃品牌的牛奶
     * @return
     */
    public abstract Milk getSixHeTao();

    public abstract Milk getSanLu();
}
