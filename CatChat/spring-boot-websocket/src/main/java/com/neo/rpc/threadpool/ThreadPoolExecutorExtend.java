package com.neo.rpc.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/28 0028
 * @Author 毛双领 <shuangling.mao>
 */
public class ThreadPoolExecutorExtend extends ThreadPoolExecutor {

    /** 线程池名字 */
    private String threadPollName;

    /** 实例计数器 */
    private AtomicInteger newNumber = new AtomicInteger();

    final AtomicInteger submittedTasksCount = new AtomicInteger();

    private long preCompletedTaskCount = -1;
    private long lastCalTime = -1;



    public ThreadPoolExecutorExtend(int corePoolSize, int maximumPoolSize,
                                    long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, handler);
        newNumber.getAndIncrement();
    }

    /**
     * 获取当前线程池正在执行的任务数
     * @return
     */
    public AtomicInteger getSubmittedTasksCount(){

        return this.submittedTasksCount ;
    }


    /**
     * 重写execute方法
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        submittedTasksCount.incrementAndGet();
        super.execute(command);
    }

    /**
     * 重写afterExecute
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        //执行完毕减一
        submittedTasksCount.decrementAndGet();
        if (r instanceof CommonFutureTask) {
            IAsynchronousHandler handler = ((CommonFutureTask) r).getR();
            if (handler == null) {
                throw new NullPointerException("线程池参数对象为Null");
            }
            handler.executeAfter(t);
        }
    }

    /**
     * 重写beforeExecute
     * @param r
     * @param t
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof CommonFutureTask) {
            IAsynchronousHandler handler = ((CommonFutureTask) r).getR();
            if (handler == null) {
                throw new NullPointerException("线程池参数对象为Null");
            }
            handler.executeBefore(t);
        }
    }

    @Override
    public String toString() {
        return "ThreadPoolExecutor: ActiveCount = "+this.getActiveCount() + " CompletedTaskCount = "+ this.getCompletedTaskCount() + " CorePoolSize = "+ this.getCorePoolSize()
                + " LargestPoolSize = "+this.getLargestPoolSize() + " MaximumPoolSize = "+ this.getMaximumPoolSize() + " PoolSize = "+this.getPoolSize() + " queueSize = "+this.getQueue().size()
                +" queueString=[" + this.getQueue().toString() + "]";
    }

    /**
     * 设置线程池名称
     * @param threadPollName
     */
    public void setThreadPollName (String threadPollName) {
        if (threadPollName == null || threadPollName.equals("")) {
            threadPollName = "ThreadPoolExecutorExtend_" + newNumber.get();
        }
        this.threadPollName = threadPollName;
    }


    private long getCompletedTasksRecently() {
        if (preCompletedTaskCount == -1) {
            preCompletedTaskCount = super.getCompletedTaskCount();
            lastCalTime = System.currentTimeMillis();
            return 0;
        }
        long curCompletedTaskCount = super.getCompletedTaskCount();
        long count = curCompletedTaskCount - preCompletedTaskCount;

        long curCalTime = System.currentTimeMillis();
        long delta = curCalTime - lastCalTime;

        preCompletedTaskCount = curCompletedTaskCount;
        lastCalTime = curCalTime;

        return count*1000/delta;
    }




}
