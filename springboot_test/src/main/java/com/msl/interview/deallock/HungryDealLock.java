package com.msl.interview.deallock;

import java.util.concurrent.*;

/**
 * Description: 解饿死锁
 *
 * @author shuangling.mao
 * @date 2019/3/19 10:07
 */
public class HungryDealLock {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        final Future<String> stringFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Future<String> stringFuture = service.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return null;
                    }
                });
                return stringFuture.get();
            }
        });


        System.out.println(stringFuture.get());
        service.shutdown();


        /**
         * 单线程线程池在执行任务的时候，我们任务中却又执行了单线程线程池的另一个任务，
         * 但是单线程线程池一次只能执行一个任务，所以两个任务在互相等待另一个任务的完成，造成最终两个任务都无法完成，就造成了饥饿死锁的现象。
         * 其实所谓的饥饿死锁也就是死锁。不明白《java并发编程实战》这书为什么要称呼这种情况叫“饥饿死锁”。
         */
    }
}
