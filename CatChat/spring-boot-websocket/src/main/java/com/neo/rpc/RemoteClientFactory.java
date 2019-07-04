/**
 * Description: RemoteServiceFactory.java
 * All Rights Reserved.
 * @version 1.5  2012-12-17 下午3:11:53  by 李洪波（hb.li@zhuche.com）创建
 */
package com.neo.rpc;


import com.neo.rpc.vo.RemoteClientContextVO;
import com.neo.utils.Assert;
import com.neo.utils.SpringApplicationContext;

/**
 * Description: 
 * All Rights Reserved.
 * @version 1.5  2012-12-17 下午3:11:53  by 李洪波（hb.li@zhuche.com）创建
 */
public final class RemoteClientFactory {
	
	
	public  static final String HESSIAN = "remote.hessianRemoteClientInternal";
	
	public  static final String TCP = "remote.tcpRemoteClientInternal";
	
	public  static final String UDP = "remote.udpRemoteClientInternal";
	
	public  static final String HTTP = "remote.httpRemoteClientInternal";
	
	private RemoteClientFactory(){
		
	}
	
	public static RemoteClient getInstance(String... type){
		
		if(type.length>1){
//			throw new RpcRuntimeException("参数个数异常，不大于一个参数的个数，请检查！");
		}
		String id = HESSIAN;
		if(type.length == 1){
			id = type[0];
		}
		Object o = SpringApplicationContext.getBean(id);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
            RemoteClientContextVO vo = new RemoteClientContextVO();
            vo.setRemoteType(RemoteType.getRemoteType(id));
			RemoteClient remoteService = new StandardRemoteClient(execute , vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	
	
	public static RemoteClient getInstance(String url,String...type){
		
		if(type.length>1){
			throw new RpcRuntimeException("参数个数异常，不大于一个参数的个数，请检查！");
		}
		String id = HESSIAN;
		if(type.length == 1){
			id = type[0];
		}
		Object o = SpringApplicationContext.getBean(id);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
			RemoteClientContextVO vo = new RemoteClientContextVO();
			vo.setUrl(url);
            vo.setRemoteType(RemoteType.getRemoteType(id));
            RemoteClient remoteService = new StandardRemoteClient(execute,vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	
	
	public static RemoteClient getInstance(RemoteType remoteType){
		String type = RemoteType.isRemoteType(remoteType);
		Object o = SpringApplicationContext.getBean(type);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
            RemoteClientContextVO vo = new RemoteClientContextVO();
            vo.setRemoteType(remoteType);
            RemoteClient remoteService = new StandardRemoteClient(execute , vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	
	
	
	public static RemoteClient getInstance(String url,RemoteType remoteType){
		String type = RemoteType.isRemoteType(remoteType);
		Object o = SpringApplicationContext.getBean(type);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
			RemoteClientContextVO vo = new RemoteClientContextVO();
			vo.setUrl(url);
			vo.setRemoteType(remoteType);
			RemoteClient remoteService = new StandardRemoteClient(execute,vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	/**
	 * 
	 * Description: 获取出现异常指定执行次数的rpc调用
	 * @Version 2.5 2013-4-9 下午4:43:59 by 李洪波（hb.li@zhuche.com）创建
	 * @param repeatCount
	 * @param remoteType
	 * @return
	 */
	public static RemoteClient getInstance(int repeatCount ,RemoteType remoteType){
		String type = RemoteType.isRemoteType(remoteType);
		Object o = SpringApplicationContext.getBean(type);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
			RemoteClientContextVO vo = new RemoteClientContextVO();
			vo.setRepeatCount(repeatCount);
            vo.setRemoteType(remoteType);
            RemoteClient remoteService = new StandardRemoteClient(execute,vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	/**
	 * 自定义 负载工厂方法
	 * 
	 * <br/> Created on 2014-12-22 下午3:19:24
	 * @author  李洪波(hb.li@zhuche.com)
	 * @since 4.0
	 * @param vo
	 * @return
	 */
	public static RemoteClient getInstance(RemoteClientContextVO vo){
		
		Assert.notNull(vo);
		String type = RemoteType.isRemoteType(vo.getRemoteType());
		Object o = SpringApplicationContext.getBean(type);
		if(o != null && o instanceof RemoteClientInternal){
			RemoteClientInternal execute = (RemoteClientInternal)o;
			RemoteClient remoteService = new StandardRemoteClient(execute,vo);
			return remoteService;
		}else{
			throw new RpcRuntimeException("服务提供者注册异常，请查看！");
		}
		
	}
	
}
