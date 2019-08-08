package com.neo.rpc.threadpool;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/28 0028
 * @Author 毛双领 <shuangling.mao>
 */
public class MyThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 实际开发中可以 自定义子类 实现 IAsynchronousHandler接口 作为参数参入
        // 例如： ServiceRunnable  异步服务类
        //整个项目 统一使用CommonThreadPool name  拒绝策略
        Future<Object> execute = CommonThreadPool.execute(new IAsynchronousHandler() {
            @Override
            public void executeAfter(Throwable t) {
                System.out.println("为什么我不执行");
            }

            @Override
            public void executeBefore(Thread t) {
                System.out.println("************开始干活************");
            }

            @Override
            public Object call() throws Exception {
                int sum = 0;
//                for (int i = 0; i < 10; i++) {
//                    try { TimeUnit.SECONDS.sleep(1/5); } catch (InterruptedException e) { e.printStackTrace(); }
//                    sum ++;
//                }
                int i = 0/5;
                return sum;
            }
        });

        System.out.println(execute.get());

        System.out.println("大功告成~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void test1 () throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Future<Integer> submit = executorService.submit(() -> {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            return 3;
        });
        System.out.println(submit.get());

        System.out.println("大功告成");
    }


    private class ServiceRunnable implements IAsynchronousHandler {

        private String remoteUrl;
        private Map<String, String> paramMap;
        private int connectTimeout;
        private int readTimeout;

        public ServiceRunnable(String remoteUrl, Map<String, String> paramMap, int connectTimeout, int readTimeout) {
            this.remoteUrl = remoteUrl;
            this.paramMap = paramMap;
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
        }

        @Override
        public Object call() throws Exception {
//            Object result = DefaultHttpClient.doPost(remoteUrl.toString(), paramMap, connectTimeout, readTimeout);
            return null;
        }

        @Override
        public void executeAfter(Throwable t) {

        }

        @Override
        public void executeBefore(Thread t) {

        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 666);

        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }

    @Test
    public void test3() {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_00_01"));
        },"线程1").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_12_88"));
        },"线程2").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_06_99"));
        },"线程3").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_01_99"));
        },"线程4").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_56_34"));
        },"线程5").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_00_01"));
        System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_12_88"));
        System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_01_99"));
        System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_06_99"));
        System.out.println(Thread.currentThread().getName()+"\t"+addVersionNumber("v_56_34"));

    }

    private String addVersionNumber(String versionNow){
        StringBuilder stringBuilder = new StringBuilder("");
        int i = versionNow.lastIndexOf("_");
        String subNumber = versionNow.substring(i+1,versionNow.length());
        Integer subNumberInteger = Integer.valueOf(subNumber)+1;
        String faNumber = versionNow.substring(2,i);
        Integer faNumberInteger = Integer.valueOf(faNumber);
        if (subNumberInteger > 20){
            stringBuilder.append("v_").append((faNumberInteger+1)).append("_0");
        }else{
            stringBuilder.append("v_").append(faNumberInteger).append("_").append(subNumberInteger);
        }
        return stringBuilder.toString();
    }


    @Test
    public void test5() {
        for (int i = 0; i < 3; i++) {
            final int tepm = i;
            CommonThreadPool.execute(new IAsynchronousHandler() {
                @Override
                public void executeAfter(Throwable t) {
                    System.out.println(t);
                }

                @Override
                public void executeBefore(Thread t) {

                }

                @Override
                public Object call() throws Exception {
                    if (tepm == 2) {
                        throw new RuntimeException();
                    }
                    System.out.println(tepm);
                    return null;
                }
            });
        }

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
