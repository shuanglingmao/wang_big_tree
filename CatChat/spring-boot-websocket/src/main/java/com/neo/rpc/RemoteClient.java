package com.neo.rpc;

import java.util.concurrent.Future;

/**
 * @Description: 远程调用接口
 * 客户端使用此接口进行远程调用
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public interface RemoteClient {
    /**
     * 同步调用
     * @param serviceName
     * @param objects
     * @return
     */
    Object executeObject(String serviceName, Object ... objects);

    /**
     * 异步调用
     * @param serviceName
     * @param objects
     * @return
     */
    Future<Object> asynExecuteObject(String serviceName, Object ... objects);
}
