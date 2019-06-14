package com.neo.builder;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: 线程池构造者测试
 *
 * @author shuangling.mao
 * @date 2019/6/13 17:18
 */
public class ExecutorBuilderTest {
    @Test
    public void test1() throws InterruptedException {

        final ThreadPoolExecutor executor = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(5).setKeepAliveTime(60, TimeUnit.SECONDS).build();
        executor.execute(() -> {
            for (int i = 0; i < 200; i++) {
                System.out.println(Thread.currentThread().getName()+"--->"+i);
            }
        });


        TimeUnit.MINUTES.sleep(2);
    }

    @Test
    public void test2() {
        System.out.println(1);
    }
}