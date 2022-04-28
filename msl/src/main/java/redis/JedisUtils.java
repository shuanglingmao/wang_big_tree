package redis;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class JedisUtils {
    private static JedisPoolConfig config;

    private static JedisPool jedisPool;

    private static String password = "jin1tian8";

    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, "101.34.121.25", 6379);
    }

    /**
     * 获取jedis
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(password);
        return jedis;
    }

    /**
     * jedis放回连接池
     *
     * @param jedis
     */
    public static void close(Jedis jedis) {
        //从源码可以分析得到，如果是使用连接池的形式，这个并非真正的close,而是把连接放回连接池中
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    public static <T> T get(String key, Class<T> clazz) {
        String objJsonStr = get(key);
        if (StringUtils.isBlank(objJsonStr)) {
            return null;
        }
        T result = JSONObject.parseObject(objJsonStr, clazz);
        return result;
    }

    public static <T> T get4Cache(String key, Class<T> clazz, Long second, GetData4DB<T> supplier) {
        T t = get(key, clazz);
        if (Objects.isNull(t)) {
            //从数据查
            t = supplier.getDate4DB();
            set(key, t, second);
        }
        return t;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    public static <T> void set(String key, T obj, long seconds) {
        String objJsonStr = JSONObject.toJSONString(obj);
        set(key, objJsonStr, seconds);
    }



    /**
     * set with expire milliseconds
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static void set(String key, String value, long seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            //* @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
            //     *                     *          if it already exist.
            //     *                     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
            jedis.set(key, value, "NX", "EX", seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }


    public static Long incr(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void hset(String key,String field,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key,field,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String hget(String key,String field){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key,field);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }

    public static Map<String,String> hgetAll(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hgetAll(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String blpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String blpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(0, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void lpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String brpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String brpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(0, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void rpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     * 获取key过期时间 -1表示永久 -2表示该key不存在
     * @param key
     * @return
     */
    public static long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }
}
