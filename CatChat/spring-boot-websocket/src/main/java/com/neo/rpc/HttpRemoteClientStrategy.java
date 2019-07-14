package com.neo.rpc;

import com.neo.rpc.threadpool.CommonThreadPool;
import com.neo.rpc.threadpool.IAsynchronousHandler;
import com.neo.rpc.vo.RemoteClientContextVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Description: http远程调用客户端
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/8
 * @Author 毛双领
 */
public class HttpRemoteClientStrategy extends AbstractRemoteClientStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(HttpRemoteClientStrategy.class);

    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    private static final int DEFAULT_READ_TIMEOUT = 15000;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    public Object defaultExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        if (!StringUtils.isEmpty(vo.getUrl())) {
            return assignUrlExecute(vo, serviceId, objects);
        }
        if (vo.getRepeatCount() > 1) {
            return this.repeatExecute(vo, serviceId, objects);
        }
        vo.setRepeatCount(1);
        return this.repeatExecute(vo, serviceId, objects);
    }

    @Override
    public Future<Object> asynExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        String url = super.getUrl(";remoteClientMark=true;jsessionid=null/remoteHttp.do_", serviceId, vo);

//        String projectName = GlobalMessage.getProjectName();
        String projectName = "项目名";
        url = addProjectNameToUrl(url, projectName);
//        url = addGrayValueToUrl(url, GrayContext.getRequestGV());

        Map requestParamMap = (Map) objects[0];
//        readReadTimeFromDatabase(serviceId);
        Future<Object> future = CommonThreadPool.execute(new ServiceRunnable(url, requestParamMap, connectTimeout,
                readTimeout));
        return future;
    }

    @Override
    public Object assignUrlExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        String url = vo.getUrl();
        int index = url.indexOf("?");
        String parameter = "";
        if (index > -1) {
            parameter = url.substring(index + 1, url.length());
            url = url.substring(0, index);
        }
        Object result = null;
        try {
            String urlPath = super.appendUrl(url, ";remoteClientMark=true;jsessionid=null/remoteHttp.do_", serviceId,
                    "&" + parameter);
//            urlPath = addProjectNameToUrl(urlPath, GlobalMessage.getProjectName());
//            urlPath = addGrayValueToUrl(urlPath, GrayContext.getRequestGV());

            Map requestParamMap = (Map) objects[0];
//            readReadTimeFromDatabase(serviceId);
            vo.setRemoteServerUrl(urlPath);
            result = DefaultHttpClient.doPost(urlPath.toString(), requestParamMap, connectTimeout, readTimeout);
        } catch (RpcRuntimeException e) {
            LOG.error("callback failed" + serviceId, e);
            throw new RpcRuntimeException(serviceId + "远程服务调用失败", e);
        }
        return result;
    }

    @Override
    public Object repeatExecute(RemoteClientContextVO vo, String serviceId, Object... objects) {
        String url = super.getUrl(";remoteClientMark=true;jsessionid=null/remoteHttp.do_", serviceId, vo);

//        String projectName = GlobalMessage.getProjectName();
        String projectName = "catChat";
        url = addProjectNameToUrl(url, projectName);
//        url = addGrayValueToUrl(url, GrayContext.getRequestGV());

        Map requestParamMap = (Map) objects[0];
        Object result = null;
//        readReadTimeFromDatabase(serviceId);
        // http://host:8080/carbi/remoteHttp.do?serviceId=服务ID&username=abc&pass=dfd
        try {
            vo.setRemoteServerUrl(url);
            result = DefaultHttpClient.doPost(url.toString(), requestParamMap,"", connectTimeout, readTimeout);
        } catch (RpcRuntimeException e) {

            LOG.error("callback failed" + serviceId, e);
            throw new RpcRuntimeException(serviceId + "远程服务调用失败", e);
        }
        return result;
    }


    /**
     * 异步服务类 <br/>
     * Created on 2013-9-9 上午11:13:07
     *
     * @author litao(litao02@zhuche.com)
     * @since 3.2
     */
    private class ServiceRunnable implements IAsynchronousHandler {

        private String remoteUrl;
        private Map<String, String> paramMap;
        private int connectTimeout;
        private int readTimeout;

        public ServiceRunnable(String remoteUrl, Map<String, String> paramMap, int connectTimeout, int readTimeout) {
            this.remoteUrl = remoteUrl;
            this.paramMap = paramMap;
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
        }

        @Override
        public Object call() throws Exception {
            Object result = DefaultHttpClient.doPost(remoteUrl.toString(), paramMap, connectTimeout, readTimeout);
            return result;
        }

        @Override
        public void executeAfter(Throwable t) {

        }

        @Override
        public void executeBefore(Thread t) {

        }
    }
}
