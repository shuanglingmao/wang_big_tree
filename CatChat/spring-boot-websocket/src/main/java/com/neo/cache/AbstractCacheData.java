package com.neo.cache;

/**
 * Description: 缓存数据获取钩子
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:34
 */
public abstract class AbstractCacheData<T> {
    public abstract T getDataFromDB();
}
