package com.msl.guava.collection;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shuangling.mao
 * @date 2019-03-26 22:00
 */
public class TestCollections {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        for (Integer value : list) {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(value+"===================="+Thread.currentThread().getName());
//        }

        list.parallelStream().forEach(value ->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(value+"===================="+Thread.currentThread().getName());
        });
    }
}
