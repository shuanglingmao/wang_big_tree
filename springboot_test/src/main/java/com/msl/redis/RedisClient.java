package com.msl.redis;

import redis.clients.jedis.Jedis;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/8 13:07
 */
public class RedisClient extends AbstractRedis {


    @Override
    public Long increment(String key, long delta) {
        return getJedis().incrBy(key,delta);
    }

    @Override
    public Long increment(String key) {
        return getJedis().incr(key);
    }

    @Override
    public void set(String key, String value, int timout) {
        final Jedis jedis = getJedis();
        jedis.set(key, value);
        jedis.expire(key,timout);
    }

    @Override
    public String get(String key) {
        return getJedis().get(key);
    }

    @Override
    public Boolean exists(String key) {
        return getJedis().exists(key);
    }

    @Override
    public void delete(String... key) {
        getJedis().del(key);
    }

    @Override
    public Boolean setnx(String key, String value, int timout) {
        final Jedis jedis = getJedis();
        final boolean result = jedis.setnx(key, value) == 1;
        if (result) {
            jedis.expire(key,timout);
        }
        return result;

    }
}
