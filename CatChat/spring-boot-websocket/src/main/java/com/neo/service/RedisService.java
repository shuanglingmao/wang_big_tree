package com.neo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Description:  redis
 * @author shuangling.mao
 * @date 2019/6/10 11:40
 * @param
 * @return
 */
@Service("redisService")
public class RedisService {
    private Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean isVip(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * set value
     * @param key
     * @param value
     * @return
     */
    public <K,V> boolean set(final K key, V value) {
        boolean result = false;
        try {
            ValueOperations<K, V> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, 1L, TimeUnit.DAYS);
            result = true;
        } catch (Exception e) {
            logger.error("set error: key {}, value {}",key,value,e);
        }
        return result;
    }

    /**
     * set value with expireTime
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public <K,V> boolean set(final K key, V value, Long expireTime,TimeUnit unit) {
        boolean result = false;
        try {
            ValueOperations<K, V> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, unit);
            result = true;
        } catch (Exception e) {
            logger.error("set error: key {}, value {},expireTime {}",key,value,expireTime,e);
        }
        return result;
    }

    public<K, V> boolean setIfAbsent(final K key,V value,Long expireTime,TimeUnit unit) {
        boolean result = false;
        try {
            ValueOperations<K, V> operations = redisTemplate.opsForValue();
            //原子操作
            result = operations.setIfAbsent(key,value,expireTime,unit);
        } catch (Exception e) {
            logger.error("setIfAbsent error: key {}, value {},expireTime {}",key,value,expireTime,e);
        }
        return result;
    }

    /**
     * @param key
     * @return
     */
    public <K> boolean exists(final K key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @param key
     * @return
     */
    public <K,V> V get(final K key) {
        V result = null;
        ValueOperations<K, V> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * remove single key
     * @param key
     */
    public <K> void remove(final K key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * batch delete
     * @param keys
     */
    public <K> void remove(final K... keys) {
        for (K key : keys) {
            remove(key);
        }
    }

    /**
     * batch delete with pattern
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * hash set
     * @param key
     * @param hashKey
     * @param value
     */
    public void hashSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * hash get
     * @param key
     * @param hashKey
     * @return
     */
    public Object hashGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     *  list push
     * @param k
     * @param v
     */
    public <K,V> void push(K k,V v){
        ListOperations<K, V> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     *  list range
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public <K,V> List<V> range(K k, long l, long l1){
        ListOperations<K, V> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     *  set add
     * @param key
     * @param value
     */
    public <K,V> void setAdd(K key,V value){
        SetOperations<K, V> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * set get
     * @param key
     * @return
     */
    public <K,V> Set<V> setMembers(K key){
        SetOperations<K, V> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * ordered set add
     * @param key
     * @param value
     * @param scoure
     */
    public <K,V> void zAdd(K key,V value,double scoure){
        ZSetOperations<K, V> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * rangeByScore
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public <K,V> Set<V> rangeByScore(K key,double scoure,double scoure1){
        ZSetOperations<K, V> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}