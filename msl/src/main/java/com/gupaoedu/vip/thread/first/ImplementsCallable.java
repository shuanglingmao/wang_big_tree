package com.gupaoedu.vip.thread.first;

import java.util.concurrent.*;

public class ImplementsCallable implements Callable<Double> {
    @Override
    public Double call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        Double result = new Double(sum);
        System.out.println(Thread.currentThread().getName()+":"+ result);
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable callable = new ImplementsCallable();
        Future<Double> future = executorService.submit(callable);

        System.out.println(future.get());
        //future.get() 会等到阻塞结束后 才会执行  "能立刻执行我吗？"
        System.out.println("能立刻执行我吗？");
        executorService.shutdown();


    }
}
