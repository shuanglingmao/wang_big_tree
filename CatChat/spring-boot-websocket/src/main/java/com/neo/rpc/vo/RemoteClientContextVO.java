/**
 * Description: RemoteClientContextVO.java
 * All Rights Reserved.
 * @version 4.0  2014-12-22 下午1:20:15  by 李洪波（hb.li@zhuche.com）创建
 */
package com.neo.rpc.vo;


import com.neo.rpc.RemoteType;
import com.neo.rpc.loadbalance.LoadBalancerTypeEnum;
import com.neo.rpc.loadbalance.RouteTypeEnum;
import com.neo.utils.IPUtils;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 服务调用，client context vo
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7
 * @Author 毛双领
 */
public class RemoteClientContextVO {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteClientContextVO.class);

    private LoadBalancerTypeEnum remoteRebalanceType = LoadBalancerTypeEnum.Random;

    /** 默认不进行路由 */
	private RouteTypeEnum routeType = RouteTypeEnum.No;
	
	private RemoteType remoteType ;
	
	private String url ;
	
	private int repeatCount ;
	/** 按照某个 值去hash ，做负载均衡 */
	private String hashValue ;


    private ThreadLocal<RemoteServerInfo> remoteServerInfo = ThreadLocal.withInitial(() -> new RemoteServerInfo());

    public void setRemoteServerUrl(String remoteServiceUrl) {
        if (StringUtils.isBlank(remoteServiceUrl)) {
            return;
        }
        try {
            this.remoteServerInfo.get().setRemoteServiceUrl(remoteServiceUrl);
        } catch (Exception e) {
            LOG.error("从远端服务器的url解析ip和port发生错误", e);
        }
    }

    public String getRemoteServerUrl() {
        return this.remoteServerInfo.get().getRemoteServiceUrl();
    }

    public void removeRemoteServerInfo() {
        this.remoteServerInfo.remove();
    }

    public String getRemoteServerIP() {
        return this.remoteServerInfo.get().getRemoteServerIP();
    }

    public String getRemoteAppName() {
        return this.remoteServerInfo.get().getRemoteAppName();
    }

    public String getHashValue() {
		return hashValue;
	}

	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}

	public RemoteType getRemoteType() {
		return remoteType;
	}

	public void setRemoteType(RemoteType remoteType) {
		this.remoteType = remoteType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
        this.setRemoteServerUrl(url);
    }

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	
	public LoadBalancerTypeEnum getRemoteRebalanceType() {
		return remoteRebalanceType;
	}

	public void setRemoteRebalanceType(LoadBalancerTypeEnum remoteRebalanceType) {
		this.remoteRebalanceType = remoteRebalanceType;
	}

	public RouteTypeEnum getRouteType() {
		return routeType;
	}

	public void setRouteType(RouteTypeEnum routeType) {
		this.routeType = routeType;
	}

	public static class RemoteServerInfo{

        private String remoteServerIP;
        private String remoteAppName;
        private String remoteServiceUrl;

        public String getRemoteServerIP() {
            return remoteServerIP;
        }

        public String getRemoteAppName() {
            return remoteAppName;
        }

        public String getRemoteServiceUrl() {
            return remoteServiceUrl;
        }

        public void setRemoteServiceUrl(String remoteServiceUrl) {

            this.remoteServiceUrl = remoteServiceUrl;
            this.remoteServerIP = IPUtils.extractIp(remoteServiceUrl);


        }
    }

    @Override
    public String toString() {
        return "RemoteClientContextVO{" +
                "remoteRebalanceType=" + remoteRebalanceType +
                ", routeType=" + routeType +
                ", remoteType=" + remoteType +
                ", url='" + url + '\'' +
                ", repeatCount=" + repeatCount +
                ", hashValue='" + hashValue + '\'' +
                '}';
    }
}
