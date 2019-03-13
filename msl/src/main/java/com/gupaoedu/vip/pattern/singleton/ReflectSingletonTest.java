package com.gupaoedu.vip.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectSingletonTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        try{
            //很无聊的情况下，进行破坏
            Class<?> clazz = LazyDoubleCheckSingleton.class;

            //通过反射拿到私有的构造方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问，强吻，不愿意也要吻
            c.setAccessible(true);
            Object instance1 = c.newInstance();
            System.out.println(instance1);
            LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
            System.out.println(instance);



//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Object instance = c.newInstance();
//                        System.out.println(instance);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            Thread thread1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Object instance = c.newInstance();
//                        System.out.println(instance);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
//            thread1.start();
//            thread.join();
//            thread1.join();
//            System.out.println("End");
//            //暴力初始化
//            Object o1 = c.newInstance();
//
//            //调用了两次构造方法，相当于new了两次
//            //犯了原则性问题，
//            Object o2 = c.newInstance();
//
//            System.out.println(o1 == o2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
