package com.msl.interview.deallock;

import java.util.concurrent.TimeUnit;

/**
 * Description: 手撸代码实现死锁
 *
 * @author shuangling.mao
 * @date 2019/3/18 18:20
 */
public class DealLock {
    private Object o1 = new Object();
    private Object o2 = new Object();

    public static void main(String[] args) {
        new DealLock().test();
    }

    public void test() {
        /**
         * 线程A持有obj1锁，线程B持有obj2锁，
         * 线程A往下执行程序需要获取obj2锁，但是obj2锁这时被线程B持有，
         * 线程B要想释放obj2锁，必须执行完程序，往下执行，
         * 但线程B执行程序又需要获取obj1的锁，但obj1的锁却被线程A所持有。
         * 所以，造成了线程A等待线程B释放obj2锁，线程B却又在等待线程A释放obj1锁，
         * 所以两不相让，就造成了死锁。
         */
        new Thread("A") {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                        System.out.println("线程:"+Thread.currentThread().getName()+"执行完毕");
                    }
                }

            }
        }.start();

        new Thread("B") {
            @Override
            public void run() {
                synchronized (o2) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println("线程:"+Thread.currentThread().getName()+"执行完毕");
                    }
                }
            }
        }.start();
    }



}
