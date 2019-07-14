package com.neo.rpc;

import com.neo.rpc.vo.RemoteClientContextVO;

import java.util.concurrent.Future;

/**
 * @Description: 远程调用实现 接口
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public interface RemoteClientStrategy {
    /**
     *
     * Description: 默认执行远程调用
     * url 自动获取，通过缓存服务器
     * @Version 1.5 2012-12-19 上午9:04:55 by 李洪波（hb.li@zhuche.com）创建
     * @param serviceId
     * @param objects
     * @return
     */
    Object defaultExecute(RemoteClientContextVO vo ,String serviceId, Object... objects);


    /**
     *
     * Description:执行异步远程调用
     * @Version1.0 2013-1-14 上午11:59:28 by （litao02@zuche.com）创建
     * @param serviceId
     * @param objects
     * @return
     */
    Future<Object> asynExecute(RemoteClientContextVO vo , String serviceId, Object... objects);
    /**
     *
     * Description: 指定地址的远程调用
     * url由客户端指定
     * @Version 1.5 2012-12-19 上午9:04:59 by 李洪波（hb.li@zhuche.com）创建
     * @param vo
     * @param serviceId
     * @param objects
     * @return
     */
    Object assignUrlExecute(RemoteClientContextVO vo , String serviceId, Object... objects);

    /**
     *
     * Description: 重复执行rpc调用，试用可靠性较强的rpc调用
     * @Version 2.5 2013-4-9 下午3:50:58 by 李洪波（hb.li@zhuche.com）创建
     * @param vo，产生异常时重新调用的次数
     * @param serviceId，服务id
     * @param objects，参数对象
     * @return
     */
    Object repeatExecute(RemoteClientContextVO vo , String serviceId, Object... objects);
}
