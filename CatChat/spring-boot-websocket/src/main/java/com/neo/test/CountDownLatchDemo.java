package com.neo.test;

import java.util.concurrent.CountDownLatch;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-23 22:16
 */

public class CountDownLatchDemo {

    public static final int NUM = 5;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= NUM; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "号运动员\t准备完毕！");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "/t 所有运动员准备就绪，裁判员开枪。 比赛开始！");







        /*CountDownLatch countDownLatch = new CountDownLatch(NUM);

        for (int i = 1; i <= NUM; i++)
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号运动员\t准备完毕！");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "/t 所有运动员准备就绪，裁判员开枪。 比赛开始！");*/
    }
}