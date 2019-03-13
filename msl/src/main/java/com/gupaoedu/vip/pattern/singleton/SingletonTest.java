package com.gupaoedu.vip.pattern.singleton;

import com.gupaoedu.vip.pattern.singleton.lazy.LazyThree;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyTwo;
import com.gupaoedu.vip.pattern.singleton.register.RegisterMap;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

public class SingletonTest {
    @Test
    public void TestHungry() {

    }

    public static void main(String[] args) throws InterruptedException {
        int count = 100;
        final CountDownLatch cdl = new CountDownLatch(count);

        long start = System.currentTimeMillis();
//        final Set<Hungry> synSet = Collections.synchronizedSet(new HashSet<Hungry>());
        for (int i = 0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
//                    synSet.add(Hungry.getInstance());
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    Hungry instance = Hungry.getInstance();
//                    LazyOne instance = LazyOne.getInstance();
//                    LazyTwo instance = LazyTwo.getInstance();
//                    LazyThree instance = LazyThree.getInstance();
                    RegisterMap instance = RegisterMap.getInstance("registerMap");
                    System.out.println(System.currentTimeMillis()+":"+instance);
                }
            }.start();
            cdl.countDown();
        }

        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));
        Thread.sleep(1000L);

    }

    @Test
    public void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
//              LazyOne.getInstance();
            LazyTwo.getInstance();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));
    }

    @Test
    public void test2() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<LazyThree> clazz = LazyThree.class;
        //通过反射拿到私有的构造方法
        Constructor<LazyThree> c = clazz.getDeclaredConstructor();
        //强制访问
        c.setAccessible(true);

        //暴力初始化
        LazyThree instance = c.newInstance();
        LazyThree instance1 = c.newInstance();
        System.out.println(instance);
        System.out.println(instance1);
        System.out.println(instance == instance1);



    }
}
