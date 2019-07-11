package com.neo.rpc;


import com.neo.rpc.util.SwitchConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Description:临时记录服务发现各阶段耗时情况 
 * All Rights Reserved.
 * Created on 2016-2-15 下午4:35:44
 * @author  孔增（kongzeng@zuche.com）
 */
public class GetRemoteServerLog {
	private static final ThreadLocal<Map<String,Object>> timesLogContext = new ThreadLocal<Map<String,Object>>(){
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};
	
	public static void putLog(String key , Object times) {
		if(SwitchConstant.GET_SERVER_LOG_SWITCH) {
			timesLogContext.get().put(key, times);
		}
	}
	
	public static void printLog() {
		if(SwitchConstant.GET_SERVER_LOG_SWITCH) {
			System.out.println("服务发现耗时记录:"+timesLogContext.get());
		}
	}
	
	public static void remoteTimesLogContext() {
		timesLogContext.remove();
	}
	
}
