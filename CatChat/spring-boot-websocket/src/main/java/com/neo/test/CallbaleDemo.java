package com.neo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019-06-23 16:14
 */
class MyCallbale implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("*********come in callbale");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallbaleDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallbale());
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyCallbale());
        //本来new Thread() 里要传Runnable 但是 Callbale不是runnable   但是runnbale的父接口RunnableFuture实现了Runnable
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();
        //多个线程启动一个futureTask  只会执行一次
        Thread t2 = new Thread(futureTask1,"t2");
        t2.start();


        while (!futureTask.isDone()) {

        }
//        //futureTask.get() 会阻塞 直到  futureTask得到返回值 返回        futureTask.get()建议放在最后
//        System.out.println(futureTask.get()+futureTask1.get());
        System.out.println(Runtime.getRuntime().availableProcessors());



    }
}
