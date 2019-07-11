/**
 * Description: RemoteContext.java
 * All Rights Reserved.
 * @version 3.2  2013-12-11 下午4:55:42  by 李洪波（hb.li@zhuche.com）创建
 */
package com.neo.rpc;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * rpc调用上下文 <br/>
 * Created on 2013-12-11 下午4:55:42
 * 
 * @author 李洪波(hb.li@zhuche.com)
 * @since 3.2
 */
public final class RemoteContext {
	public static final String WAIT_TIMEOUT = "waitTimeOut";
	private static final ThreadLocal<RemoteContext> LOCAL = ThreadLocal.withInitial(() -> new RemoteContext());
	/** 存储serviceId 上下文对象 */
	public static final ThreadLocal<String> SERVICE_CONTEXT = new ThreadLocal<>();

	protected RemoteContext() {
	}

	private String serviceId;
	
	private String className;

	private String methodName;

	private Class<?>[] parameterTypes;

	private Object[] arguments;

	private InetSocketAddress localAddress;

	private InetSocketAddress remoteAddress;
	
	private Object result;
	
	private String clientProjectName;
	
	private final Map<String, String> attachments = new HashMap<String, String>();

	private final Map<String, Object> values = new HashMap<String, Object>();



	/**
	 * get context.
	 * 
	 * @return context
	 */
	public static RemoteContext getContext() {
		return LOCAL.get();
	}

	/**
	 * remove context.
	 * 
	 */
	public static void removeContext() {
		SERVICE_CONTEXT.remove();
		LOCAL.remove();
	}

	/**
	 * get method name.
	 * 
	 * @return method name.
	 */
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * get parameter types.
	 * 
	 * @serial
	 */
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	/**
	 * get arguments.
	 * 
	 * @return arguments.
	 */
	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * set local address.
	 * 
	 * @param address
	 * @return context
	 */
	public RemoteContext setLocalAddress(InetSocketAddress address) {
		this.localAddress = address;
		return this;
	}

	/**
	 * set local address.
	 * 
	 * @param host
	 * @param port
	 * @return context
	 */
	public RemoteContext setLocalAddress(String host, int port) {
		if (port < 0) {
			port = 0;
		}
		this.localAddress = InetSocketAddress.createUnresolved(host, port);
		return this;
	}

	/**
	 * get local address.
	 * 
	 * @return local address
	 */
	public InetSocketAddress getLocalAddress() {
		return localAddress;
	}

	/**
	 * set remote address.
	 * 
	 * @param address
	 * @return context
	 */
	public RemoteContext setRemoteAddress(InetSocketAddress address) {
		this.remoteAddress = address;
		return this;
	}

	/**
	 * set remote address.
	 * 
	 * @param host
	 * @param port
	 * @return context
	 */
	public RemoteContext setRemoteAddress(String host, int port) {
		if (port < 0) {
			port = 0;
		}
		this.remoteAddress = InetSocketAddress.createUnresolved(host, port);
		return this;
	}

	/**
	 * get remote address.
	 * 
	 * @return remote address
	 */
	public InetSocketAddress getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * get remote host name.
	 * 
	 * @return remote host name
	 */
	public String getRemoteHostName() {
		return remoteAddress == null ? null : remoteAddress.getHostName();
	}

	/**
	 * get local port.
	 * 
	 * @return port
	 */
	public int getLocalPort() {
		return localAddress == null ? 0 : localAddress.getPort();
	}

	/**
	 * get remote port.
	 * 
	 * @return remote port
	 */
	public int getRemotePort() {
		return remoteAddress == null ? 0 : remoteAddress.getPort();
	}

	/**
	 * get attachment.
	 * 
	 * @param key
	 * @return attachment
	 */
	public String getAttachment(String key) {
		return attachments.get(key);
	}

	/**
	 * set attachment.
	 * 
	 * @param key
	 * @param value
	 * @return context
	 */
	public RemoteContext setAttachment(String key, String value) {
		if (value == null) {
			attachments.remove(key);
		} else {
			attachments.put(key, value);
		}
		return this;
	}

	/**
	 * remove attachment.
	 * 
	 * @param key
	 * @return context
	 */
	public RemoteContext removeAttachment(String key) {
		attachments.remove(key);
		return this;
	}

	/**
	 * get attachments.
	 * 
	 * @return attachments
	 */
	public Map<String, String> getAttachments() {
		return attachments;
	}

	/**
	 * set attachments
	 * 
	 * @param attachment
	 * @return context
	 */
	public RemoteContext setAttachments(Map<String, String> attachment) {
		this.attachments.clear();
		if (attachment != null && attachment.size() > 0) {
			this.attachments.putAll(attachment);
		}
		return this;
	}

	public void clearAttachments() {
		this.attachments.clear();
	}

	/**
	 * get values.
	 * 
	 * @return values
	 */
	public Map<String, Object> get() {
		return values;
	}
	
	
	/**
	 * Description: 设置客户端等待时间
	 * @param value
	 * @return
	 */
	public  RemoteContext setWaitTimeout(int value) {
		if (value <= 0) {
			values.remove(WAIT_TIMEOUT);
		} else {
			values.put(WAIT_TIMEOUT,value);
		}
		return this;
	}
	
	public int getWaitTimeout(){
		Object obj = values.get(WAIT_TIMEOUT);
		if(obj == null){
			return 0;
		}
		return (Integer)(obj);
	}

	/**
	 * set value.
	 * 
	 * @param key
	 * @param value
	 * @return context
	 */
	public RemoteContext set(String key, Object value) {
		if (value == null) {
			values.remove(key);
		} else {
			values.put(key, value);
		}
		return this;
	}

	/**
	 * remove value.
	 * 
	 * @param key
	 * @return value
	 */
	public RemoteContext remove(String key) {
		values.remove(key);
		return this;
	}

	/**
	 * get value.
	 * 
	 * @param key
	 * @return value
	 */
	public Object get(String key) {
		return values.get(key);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setClientProject(String projectName){
		this.clientProjectName = projectName;
	}
	
	public String getClientProject(){
		return this.clientProjectName;
	}
}
