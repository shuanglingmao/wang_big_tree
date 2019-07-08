package com.neo.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    //请注意此时Number是加了volatile关键字修饰的
    public void addPulsPuls() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1. 验证volatile的可见性
 *      1.1假如int number = 0; number变量之根本没有添加volatile关键字修饰，没有可见性
 *      1.2添加了volatile,可以解决可见性问题
 * 2. 验证volatile不保证原子性
 *      2.1原子性指得是什么？
 *      不可分割，完整性，也即某个线程正在做某个业务的时候中间不可以被加塞或者被分割，需要整体完整
 *      要么同时成功，要么同时失败。  保证数据完整一致性
 *      2.2 volatile不保证原子性的案例演示
 *      2.3 why?
 *      2.4 如何解决原习性
 *          * 加sync  杀鸡用牛刀
 *          * 正解 juc.AtomicInteger  不会出现写覆盖
 */
public class Volatile {

    public static void main(String[] args) throws InterruptedException {
        okAtomic();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {

            }
        }).start();
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        Thread thread = new Thread();

    }
    public void test() {
        System.out.println(1);
    }
    //验证及解决原子性问题
    private static void okAtomic() {
        MyData myData = new MyData();
        //20个线程点1000次  20个线程加完之后应该是20000
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addPulsPuls();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        //等待20个线程执行完毕 再用main线程取得最终的结果值看是多少？
//        TimeUnit.SECONDS.sleep(5);
        while (Thread.activeCount() > 2)  {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value:" + myData.atomicInteger.get());
    }


    //volatile 可以保证可见性，及时通知其他线程，主物理内存的值已被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number value: "+myData.number);
        },"AAA").start();


        //        加volatile 线程修改后 其他线程可以迅速获取最新值
        while (myData.number == 0) {
            //等于0 死循环
        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value:" + myData.number);
    }
}

