package com.gupaoedu.vip.thread.first.producterandconsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<PCData> blockingQueue;
    private static final int SLEEP_TIME = 1000;

    public Consumer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费,线程ID:"+Thread.currentThread().getId());
        Random r = new Random();
        while (true) {
            try {
                PCData pcdata = blockingQueue.take();
                if (pcdata != null) {
                    int re = pcdata.getData() * pcdata.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",pcdata.getData(),pcdata.getData(),re));
                    Thread.sleep(r.nextInt(SLEEP_TIME));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
