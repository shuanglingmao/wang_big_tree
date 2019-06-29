package com.neo.rpc.threadpool;

import java.util.concurrent.Future;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/28 0028
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
public class ThreadPoolAdaptor implements IAsynchronousHandler{

    private IAsynchronousHandler handler;

    private Future<Object> future;

    private final long executeTime;

    public ThreadPoolAdaptor(IAsynchronousHandler handler, long executeTime) {
        this.handler = handler;
        this.executeTime =  System.currentTimeMillis()+executeTime;
    }

    /** 获取真实的任务对象 */
    public IAsynchronousHandler getHandler() {
        return handler;
    }

    Future<Object> getFuture() {
        return future;
    }

    void setFuture(Future<Object> future) {
        this.future = future;
    }

    long getExecuteTime(){
        return executeTime;
    }

    @Override
    public void executeAfter(Throwable t) {
        handler.executeAfter(t);
    }

    @Override
    public void executeBefore(Thread t) {
        handler.executeBefore(t);
    }

    @Override
    public Object call() throws Exception {
        return handler.call();
    }
}
