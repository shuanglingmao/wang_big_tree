package com.neo.rpc;

import com.neo.rpc.vo.RemoteClientContextVO;

import java.util.concurrent.Future;

/**
 * @Description: 标准远程服务客户端
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public class StandardRemoteClient implements RemoteClient {
    private RemoteClientInternal execute;

    private RemoteClientContextVO vo ;

    StandardRemoteClient(RemoteClientInternal execute , RemoteClientContextVO vo ) {
        this.execute = execute;
        if(vo == null){
            this.vo = new RemoteClientContextVO();
        }else{
            this.vo = vo ;
        }

    }

    @Override
    public Object executeObject(String serviceName, Object... objects) {
        return null;
    }

    @Override
    public Future<Object> asynExecuteObject(String serviceName, Object... objects) {
        return null;
    }
}
