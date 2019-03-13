package com.gupaoedu.vip.thread.first.producterandconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> blockingQueue = new LinkedBlockingDeque<>(10);
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3= new Producer(blockingQueue);

        Consumer consumer1 = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);
        Consumer consumer3 = new Consumer(blockingQueue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        Thread.sleep(10*1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000);
        service.shutdown();

    }
}
