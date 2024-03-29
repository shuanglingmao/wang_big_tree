package jdk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/20 0020
 * @Author 毛双领 <shuangling.mao>
 */
public class Atomic {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static Integer num = 0;

    public static void main(String[] args) {
//        atomicIncrement();
        UnAtomicIncrement();
    }

    private static void atomicIncrement() {
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 200; j++) {
                    atomicInteger.getAndIncrement();
                }
            }, "线程"+i).start();
        }

        while (Thread.activeCount() > 2 ) {
            Thread.yield();
        }

        System.out.println("mission is over,value:\t" + atomicInteger);
    }

    private static void UnAtomicIncrement() {
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 200; j++) {
                    num++;
                    System.out.println("线程名：" + Thread.currentThread().getName() + ", num:" + num);
                }
            }, "线程"+i).start();
        }

        while (Thread.activeCount() > 2 ) {
            Thread.yield();
        }

        System.out.println("mission is over,value:\t" + num);
    }


}
