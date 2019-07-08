package com.neo.test;

import java.util.concurrent.CyclicBarrier;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-23 23:22
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
    }

    CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
    });
}