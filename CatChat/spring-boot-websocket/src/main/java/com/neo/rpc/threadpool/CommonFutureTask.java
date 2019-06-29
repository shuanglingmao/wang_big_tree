package com.neo.rpc.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/28 0028
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
public class CommonFutureTask<V> extends FutureTask<V> {
    private IAsynchronousHandler r;

    public CommonFutureTask(Callable callable) {
        super(callable);
        if (callable instanceof IAsynchronousHandler) {
            this.r = (IAsynchronousHandler) callable;
        }
    }

    public CommonFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }

    public IAsynchronousHandler getR() {
        return r;
    }
}
