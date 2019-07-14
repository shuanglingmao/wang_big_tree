package com.neo.test;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/27 0027
 * @Author 毛双领 <shuangling.mao>
 */
public class Volatile1 {
    public static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("出狱倒计时");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            isStop = true;
            System.out.println("批准出狱!");
        }).start();

        while (!isStop) {

        }



    }
}
