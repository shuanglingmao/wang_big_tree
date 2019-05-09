package com.msl.redis;

import org.junit.Before;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/7 18:07
 */

public class Test {
    private RedisClient redisClient;
    public static void main(String[] args) {
////        Jedis jedis = new Jedis("152.136.133.147",6379);
//        JedisPool jedisPool = new JedisPool("152.136.133.147",6379);
//        final Jedis jedis = jedisPool.getResource();
////        jedis.set("msl","毛双领");
//        System.out.println(jedis.get("msl"));
//        System.out.println(jedis);
        RedisClient redisClient = new RedisClient();
        redisClient.set("msl","毛双领",3600);
        System.out.println(redisClient.get("msl"));
    }

    @Before
    public void befor() {
        redisClient = new RedisClient();
    }



    @org.junit.Test
    public void setnx() {
        System.out.println(redisClient.setnx("lock", "true", 60));
    }

    /**初始化发强器*/
    private static CountDownLatch cdl = new CountDownLatch(20);
    private static Integer num = 10;

    @org.junit.Test
    public void killTest() {
        kill();
    }

    public void kill()   {
        System.out.println("秒杀开始~");
        for (int i = 1; i <= 20; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Request("线程"+i)).start();
            cdl.countDown();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Request implements Runnable {
        private String name;
        public Request(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invoke(name);
        }
    }

    private  void invoke(String name) {
        System.out.println(1);
        if (redisClient.setnx("lock","lock",2)) {
            if (num > 0) {
                System.out.println(name+"抢到优惠券"+num+",优惠券剩余"+(num-1));
                num--;
            } else {
                System.out.println(name+"优惠券已被抢光");
            }
        } else {
            invoke(name);
        }
    }




}
