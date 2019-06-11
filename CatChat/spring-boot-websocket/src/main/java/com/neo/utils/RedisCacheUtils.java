package com.neo.utils;

import com.neo.cache.AbstractCacheData;
import com.neo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Description: redis工具类
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:43
 */
@Slf4j
@Component
public final class RedisCacheUtils{

    @Autowired
    private RedisService redisService;



    /**
     * Description: 获取valueCommand数据
     *
     * @param <T> the type parameter
     * @param key the key
     * @return T t
     */
    public <T> T get(String key) {
        return (T) redisService.get(key);
    }


    /**
     * 获取数据,如果没有，调用钩子后存入valueCommands缓存
     *
     * @param <T>       the type parameter
     * @param key       键
     * @param cacheData 缓存数据
     * @return the t
     */
    public <T> T get(String key, AbstractCacheData<T> cacheData) {
        T result = get(key);
        if (result != null) {
            return result;
        } else {
            result = cacheData.getDataFromDB();
            if (result != null) {
                redisService.set(key,result);
            }
            return result;
        }
    }

}
