
package com.neo.rpc;

import com.neo.rpc.registercenter.ClientRegisterCenterUtil;
import com.neo.rpc.util.PropertiesReader;
import com.neo.rpc.util.SwitchConstant;
import com.neo.rpc.vo.RemoteClientContextVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AbstractRemoteClientStrategy implements RemoteClientStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRemoteClientStrategy.class);

	private static Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");

	protected String getUrl(String controllerPath,String serviceId , RemoteClientContextVO vo){

		int e = serviceId.indexOf(".");
		if(e>-1){
			String projectName = serviceId.substring(0, e).trim();
			Object ipObj = null;
			if(SwitchConstant.SOFT_BALANCER_SWITCH){
				//注册中心取
				try {
					Long start = System.currentTimeMillis();

//					List<String> servers = ZkRegistryCenter.getInstance().getServers(projectName);
					List<String> servers = null;
					if(servers == null || servers.size() == 0){
						throw new RpcRuntimeException(projectName+"   rpc调用，从ZK获取url异常,请检查switch.properties配置软负载是是否开启状态!解决方案参考");
					}

//					Router router = RouterFactory.getRouter(vo.getRouteType());
//					long beginRoute = System.currentTimeMillis();
//					if(router != null){
//						servers = router.route(serviceId, servers);
//						if(servers == null || servers.size() == 0){
//							throw new RpcRuntimeException(serviceId+" 没有满足路由条件的服务提供者,请检查prism后台配置");
//						}
//					}

					long t1 = System.currentTimeMillis();
					if (SwitchConstant.BLOCK_QUEUE_LB_OPEN){
//						ipObj = LoadBalancerManager.getInstance().getBlockQueueAliveServerUrl(projectName, servers, vo.getRemoteRebalanceType(), vo.getHashValue());
					}else{
						//ipObj = LoadBalancerManager.getInstance().getAliveServerUrl(servers, vo.getRemoteRebalanceType(), vo.getHashValue());
//						ipObj = LoadBalancerManager.getInstance().getAliveServerUrl(projectName, servers, vo.getRemoteRebalanceType(), vo.getHashValue());
					}

					Long end = System.currentTimeMillis();
					if(end - start >= 1) {
						GetRemoteServerLog.putLog("软负载耗时", end - t1);
//						GetRemoteServerLog.putLog("路由耗时", t1 - beginRoute);
						GetRemoteServerLog.putLog("server", ipObj);
						GetRemoteServerLog.putLog("服务发现总耗时", end - start);
						GetRemoteServerLog.putLog("executeTime", new Date());
						GetRemoteServerLog.printLog();
					}

				}finally {
					GetRemoteServerLog.remoteTimesLogContext();
				}
			}
			else{
				//远程服务取
				ipObj = ClientRegisterCenterUtil.getUrl(serviceId);
			}

			//本地配置文件中取
			if(ipObj == null){

				ipObj = PropertiesReader.getAppointPropertiesAttribute("modules", projectName, String.class);

			}

			if(ipObj == null){
//				throw new RpcRuntimeException(serviceId+":取不到配置工程的地址信息!,解决方案参考"+FAQLink.FAQ_001);
			}
//			StringBuilder sb = new StringBuilder();
//			sb.append(ipObj).append(controllerPath).append("?serviceId=").append(serviceId);
//			return sb.toString();
            return this.appendUrl(ipObj, controllerPath, serviceId, "");
		}else{
			throw new RpcRuntimeException(serviceId+":服务名称配置错误，请检查 !");
		}

	}

	protected String appendUrl(Object address,String controllerPath,String serviceId , String parameter){

		StringBuilder sb = new StringBuilder();
		sb.append(address).append(controllerPath).append("?serviceId=").append(serviceId).append(parameter);
		return sb.toString();

	}

	protected String addProjectNameToUrl(String url, String projectName) {
		return url + "&remote_client_projectName=" + projectName;
	}

	protected String addGrayValueToUrl(String url, Integer grayValue) {
		return url + "&remote_client_grayValue="+grayValue;
	}
	/**
	 * 获取新 HTTPsession
	 *
	 * <br/> Created on 2013-12-20 上午11:12:11
	 * @author  李洪波(hb.li@zhuche.com)
	 * @since 3.2
	 * @return
	 */
	protected HttpSession getRpcSession(){
//		HttpSession session = RequestContext.RPC_LOCAL_SESSION.get();
//		if(session == null){
//			return null;
//		}
//		Map<String, Object> map = null;
//		try {
//			map = RequestContext.getSessionToCustomSessionToAttribute(session);
//		} catch (Exception e) {
//			LOG.error(e.getMessage(), e);
//			throw new RpcRuntimeException(e);
//		}
//		if(map == null){
//			return null ;
//		}
//		RemoteHttpSession newSession = new RemoteHttpSession(session.getId(), map);
//		return newSession ;
		return null;
	}



	protected String extractIp(String url){
		String remoteServiceIp = null;

		Matcher m = p.matcher(url.toString());
		if(m.find()){
			remoteServiceIp = m.group();
		}
		if(remoteServiceIp == null){
			throw new RpcRuntimeException("缓存服务器取不到ip信息 !");
		}
		return remoteServiceIp;
	}
}
