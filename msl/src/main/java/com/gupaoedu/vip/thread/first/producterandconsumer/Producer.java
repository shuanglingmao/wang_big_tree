package com.gupaoedu.vip.thread.first.producterandconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
public class Producer implements Runnable {
    private volatile  boolean isRunning = true;
    /**内存缓冲区*/
    private BlockingQueue<PCData> blockingQueue;
    /**总数  原子操作*/
    private static AtomicInteger count = new AtomicInteger();
    private static final  int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        PCData pcData = null;
        Random r = new Random();
        System.out.println("开始生产，线程ID:"+Thread.currentThread().getId());
        while (isRunning) {
            try {
                Thread.sleep(r.nextInt(SLEEP_TIME));
                pcData = new PCData(count.incrementAndGet());
                System.out.println(pcData+"加入队列");
                if (!blockingQueue.offer(pcData,2, TimeUnit.SECONDS)) {
                    System.out.println("加入队列失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
