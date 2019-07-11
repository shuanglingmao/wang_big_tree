package com.neo.test;

import com.neo.thread.RejectPolicy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-23 17:11
 */

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 2,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), RejectPolicy.CALLER_RUNS.getValue());
        for (int i = 1; i <= 100; i++) {
            threadPoolExecutor.execute(()->{
                //让线程睡一会儿
                System.out.println(Thread.currentThread().getName()+"\t执行任务");
            });
        }
    }
}
