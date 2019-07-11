package com.neo.rpc;

import com.neo.rpc.vo.RemoteClientContextVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * @Description: 标准远程服务客户端
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public class StandardRemoteClient implements RemoteClient {
    /** 日志 */
    private static final Logger LOG = LoggerFactory.getLogger(StandardRemoteClient.class);

    /** 远程调用策略 */
    private RemoteClientStrategy strategy;

    /** 远程调用vo */
    private RemoteClientContextVO vo ;

    /**
     * 有参构造器
     * @param strategy
     * @param vo
     */
    public StandardRemoteClient(RemoteClientStrategy strategy , RemoteClientContextVO vo) {
        this.strategy = strategy;
        this.vo = vo != null ? vo : new RemoteClientContextVO();
    }

    @Override
    public Object executeObject(String serviceName, Object... objects) {
        final String oldServiceName = RemoteContext.SERVICE_CONTEXT.get();
        RemoteContext.SERVICE_CONTEXT.set(serviceName);

        try {
            Object result = strategy.defaultExecute(vo, serviceName, objects);
            if (result != null) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(result.toString());
                }
                return result;
            }
            return null;
        } finally {
            RemoteContext.SERVICE_CONTEXT.set(oldServiceName);
        }
    }

    @Override
    public Future<Object> asynExecuteObject(String serviceName, Object... objects) {
        String oldServiceName = RemoteContext.SERVICE_CONTEXT.get();
        RemoteContext.SERVICE_CONTEXT.set(serviceName);

        try{
            Future<Object> future = strategy.asynExecute(vo ,serviceName, objects);
            return future;
        }finally{
            RemoteContext.SERVICE_CONTEXT.set(oldServiceName);
        }
    }
}
