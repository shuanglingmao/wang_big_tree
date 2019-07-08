/**
 * Description: ThreadPoolParameterVO.java
 * All Rights Reserved.
 * @version 3.2  2013-10-30 下午3:08:07  by 李洪波（hb.li@zhuche.com）创建
 */
package com.neo.rpc.threadpool;

/**
 *  线程池参数vo
 * <br/> Created on 2019-06-28 11:35:52
 * @author  shuangling.mao
 * @since 1.0
 */
public class ThreadPoolParameterVO {
	
	private static final int CORE_POOL_SIZE = 5;
	
	private static final int MAXIMUM_POOLSIZE = 200;
	
	private static final int INITIAL_CAPACITY = 1000000;
	
	private static final int KEEP_ALIVE_TIME = 120;
	
	private int corePoolSize = CORE_POOL_SIZE;
	
	private int maximumPoolSize = MAXIMUM_POOLSIZE;
	
	private int initialCapacity = INITIAL_CAPACITY;
	
	private long keepAliveTime = KEEP_ALIVE_TIME;
	
	private String threadName = "base-framework-threadPool-";
	
	private boolean isDiscard = true ;

	public boolean isDiscard() {
		return isDiscard;
	}

	public void setDiscard(boolean isDiscard) {
		this.isDiscard = isDiscard;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
}
