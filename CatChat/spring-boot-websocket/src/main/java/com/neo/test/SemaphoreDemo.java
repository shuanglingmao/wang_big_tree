package com.neo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/2 0002
 * @Author 毛双领 <shuangling.mao>
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //阻塞队列  五个车位
        final LinkedBlockingQueue<String> parks = new LinkedBlockingQueue<>(5);

        parks.offer("车位一");
        parks.offer("车位二");
        parks.offer("车位三");
        parks.offer("车位四");
        parks.offer("车位五");

        final ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore smmaphore = new Semaphore(5);

        for (int i = 1; i <= 100 ; i++) {
            final int temp = i;

            executorService.submit(() -> {
                try {
                    //判断内部数字（许可证）是否大于0，如果大于0，获取许可，值减一，线程继续执行
                    //值为0，没有获得许可，线程阻塞，直到其他线程调用release()释放掉许可，值+1，当前线程离开阻塞
                    smmaphore.acquire();

                    String park = parks.take();

                    System.out.println("车辆:"+temp+"\t抢到车位:"+park);
                    Thread.sleep((long) Math.random() * 6000);
                    //值+1
                    smmaphore.release();
                    parks.offer(park);
                    System.out.println("车辆:"+temp+"\t离开车位:"+park);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}


