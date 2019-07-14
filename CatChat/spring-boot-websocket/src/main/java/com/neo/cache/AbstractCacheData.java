package com.neo.cache;

/**
 * Description: 缓存数据获取钩子  jdk1.8应该使用 jdk提供的函数式接口  Supplier 无需自己定义
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:34
 */
@FunctionalInterface
public interface AbstractCacheData<T> {
    T getDataFromDB();
}
