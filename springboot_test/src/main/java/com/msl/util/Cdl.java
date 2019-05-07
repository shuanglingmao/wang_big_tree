package com.msl.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Description: 线程池操作
 *
 * @author shuangling.mao
 * @date 2019/5/5 15:53
 */
public class Cdl {


    public void setName() {


//
//        ExecutorService executorService = Executors.newFixedThreadPool(20);

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
//                Runtime.getRuntime().availableProcessors(),1, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
//            @Override
//            public Thread newThread(@NotNull Runnable r) {
//                return new Thread(r, "线程" + Thread.currentThread().getName());
//            }
//        });
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 20, 200L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),namedThreadFactory);
//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        ExecutorService executorService = Executors.newFixedThreadPool(30);
        final int num = 6000000;
        final CountDownLatch cdl = new CountDownLatch(num);
        long havaThreadPollTime=0;
        long nohavaThreadPollTime=0;

        try {
            final long start = System.currentTimeMillis();
            for (int i = 0; i < num; i++) {
                executorService.execute(new TestUrl(cdl));
            }
            cdl.await();
            final long end = System.currentTimeMillis();
            executorService.shutdown();
            havaThreadPollTime=end-start;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService = null;
        }
        final long start = System.currentTimeMillis();
        for (int i = 0; i <= num; i++) {
            new TestUrl().test();
        }
        final long end = System.currentTimeMillis();
        nohavaThreadPollTime=end- start;
        System.out.println("有线程池~执行完毕,执行时间:"+havaThreadPollTime);
        System.out.println("无线程池~执行完毕,执行时间:"+nohavaThreadPollTime);
    }

    public static void main(String[] args) {
        new Cdl().setName();
    }




}
