package com.neo.rpc.threadpool;

import java.util.concurrent.Callable;

public interface IAsynchronousHandler<V> extends Callable<V> {

    void executeAfter(Throwable t);

    void executeBefore(Thread t);
}
