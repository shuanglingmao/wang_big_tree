package com.msl.guava.future;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author msl on 2020/5/8.
 */

public class ListenableFutureTest {


    @Test
    public void creat() throws InterruptedException {

        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        Collection<Callable<Integer>> threads = Lists.newArrayList(() -> 111,() -> 222,() -> 333);
        List<Future<Integer>> futures = executorService.invokeAll(threads);
        System.out.println(futures);
    }


    @Test
    public void create1() {

    }



}
