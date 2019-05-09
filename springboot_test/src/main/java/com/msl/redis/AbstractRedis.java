package com.msl.redis;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019/5/8 9:44
 */
public abstract class AbstractRedis {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；*/
    private static int MAX_WAIT = 15 * 1000;
    /**超时时间*/
    private static int TIMEOUT = 10 * 1000;

    private static JedisPool jedisPool = null;

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    protected Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }

        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            log.error("同步获取Jedis实例失败" + e.getMessage(), e);
            returnBrokenResource(jedis);
        }

        return jedis;
    }


    private void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }

    private void returnBrokenResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }

    private synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }
    private void initialPool() {
        //Redis服务器IP
        String HOST = "152.136.133.147";
        //Redis的端口号
        int PORT = 6379;
        //访问密码
        String AUTH = "";

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数，如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(100);
            //最大空闲数，控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
            config.setMaxIdle(8);
            //最小空闲数
            config.setMinIdle(10);
            //是否在从池中取出连接前进行检验，如果检验失败，则从池中去除连接并尝试取出另一个
            config.setTestOnBorrow(true);
            //在return给pool时，是否提前进行validate操作
            config.setTestOnReturn(true);
            //在空闲时检查有效性，默认false
            config.setTestWhileIdle(true);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
            //这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(30000);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(60000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(1000);
            //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(MAX_WAIT);

            if (!Strings.isNullOrEmpty(AUTH)) {
                jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, AUTH);
            } else {
                jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
            }
        } catch (Exception e) {
            if(jedisPool != null){
                jedisPool.close();
            }
            log.error("初始化Redis连接池失败", e);
        }

    }
    protected abstract Long increment(String key, long delta);
    protected abstract Long increment(String key);
    protected abstract void set(String key, String value,int timout);
    protected abstract String get(String key);
    protected abstract Boolean exists(String key);
    protected abstract void delete(String ...key);
    protected abstract Boolean setnx(String key,String value,int timout);
}
