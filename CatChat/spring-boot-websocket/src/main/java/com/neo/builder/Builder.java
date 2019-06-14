package com.neo.builder;

/**
 * Description: 建造者模式定义接口
 *
 * @author shuangling.mao
 * @date 2019/6/13 16:59
 */
public interface Builder <T>{
    /**
     * 构建
     *
     * @return 被构建的对象
     */
    T build();
}
