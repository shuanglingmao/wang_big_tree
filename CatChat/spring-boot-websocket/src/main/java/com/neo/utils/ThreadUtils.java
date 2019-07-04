package com.neo.utils;

import com.neo.rpc.threadpool.CommonThreadPool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
public class ThreadUtils {

    private static AtomicInteger num = new AtomicInteger();
    public static void execute(Runnable r, Integer number) {
        CommonThreadPool.execute(r,number);
    }


    public static void main(String[] args) {
        execute(()->{
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        },10);
        System.out.println(num);
    }
}
