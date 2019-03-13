package com.wdd;

public class FirstDemo {
    public static void main(String[] args) {
        //jvm试图使用的最大内存量  最大
        long maxMemory = Runtime.getRuntime().maxMemory();
        //jvm中的内存总量         默认
        long totalMemory = Runtime.getRuntime().totalMemory();
        //空闲容量
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("FREE_MEMORY = " + freeMemory + "（字节）、" + (freeMemory / (double)1024 / 1024) + "MB");
    }
    public static void ctrlP(String name, Integer age) {
        System.out.println(name+"");



    }
}
