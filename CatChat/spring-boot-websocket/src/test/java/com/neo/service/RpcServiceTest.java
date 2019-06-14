package com.neo.service;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Description: 验证重复提交切面
 *
 * @author shuangling.mao
 * @date 2019/6/14 11:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RpcServiceTest {
    @Autowired
    private RpcService rpcService;
    @Autowired
    private RedisService redisService;
    @Test
    public void getFlag() throws Exception {
        new Thread(()->{
            final Result<Boolean> result = rpcService.getFlag("!");
            System.out.println(JSONObject.toJSONString(result)+result.getClass());
        },"thread1").start();
        new Thread(()->{
            final Result<Boolean> result = rpcService.getFlag("!");
            System.out.println(JSONObject.toJSONString(result)+result.getClass());
        },"thread2").start();

        TimeUnit.SECONDS.sleep(10);

    }

    @Test
    public void test1() {
        final boolean set = redisService.set("111", "-", 100L, TimeUnit.SECONDS);
        final Object o = redisService.get("111");
        System.out.println(o);
    }

}