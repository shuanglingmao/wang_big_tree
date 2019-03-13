package com.gupaoedu.vip.thread.first;

public class ImplementsRunnable implements Runnable {
    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName()+":"+sum);
    }


    public static void main(String[] args) {
        Runnable runnable1 = new ImplementsRunnable();
        Runnable runnable2 = new ImplementsRunnable();

        Thread t1 = new Thread(runnable1,"线程1");
        Thread t2 = new Thread(runnable2,"线程2");

        t1.start();
        t2.start();
    }
}
