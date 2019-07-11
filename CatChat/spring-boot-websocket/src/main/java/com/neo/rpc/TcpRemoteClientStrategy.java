package com.neo.rpc;

import com.neo.rpc.vo.RemoteClientContextVO;

import java.util.concurrent.Future;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/8 0008
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
public class TcpRemoteClientStrategy extends AbstractRemoteClientStrategy{
    @Override
    public Object defaultExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        return null;
    }

    @Override
    public Future<Object> asynExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        return null;
    }

    @Override
    public Object assignUrlExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        return null;
    }

    @Override
    public Object repeatExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        return null;
    }
}
