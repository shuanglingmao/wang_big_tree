package com.gupaoedu.vip.thread.first;

public class ExtendsThread extends Thread {
    public ExtendsThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {

            sum += i;
        }
        System.out.println(getName()+"执行完毕"+sum);
    }

    public static void main(String[] args) {
        Thread thread1 = new ExtendsThread("线程1");
        Thread thread2 = new ExtendsThread("线程2");
        thread1.start();
        thread2.start();
    }
}
