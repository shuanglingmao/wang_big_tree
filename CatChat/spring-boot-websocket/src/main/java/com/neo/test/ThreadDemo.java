package com.neo.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-25 01:05
 */
class Tick implements Runnable {
    private int TICK_NUM = 100;
    private AtomicInteger NUM = new AtomicInteger(100);
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (TICK_NUM > 0) {
                    //让线程睡一会儿 模拟处理逻辑
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t出售车票，tick号为：" + TICK_NUM--);
                } else {
                    break;
                }
            }
        }
    }


}

public class ThreadDemo {
    public static void main(String[] args) {
        Tick tick = new Tick();
        Thread t1 = new Thread(tick);
        Thread t2 = new Thread(tick);
        Thread t3 = new Thread(tick);
        t1.setName("1号窗口");
        t2.setName("2号窗口");
        t3.setName("3号窗口");
        t1.start();
        t2.start();
        t3.start();
    }





    

}
