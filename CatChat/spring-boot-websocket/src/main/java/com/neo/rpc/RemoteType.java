/**
 * Description: RemoteType.java
 * All Rights Reserved.
 * @version 1.0  2012-12-20 下午4:43:22  by （litao02@zuche.com）创建
 */
package com.neo.rpc;


/**
 * Description: 
 * All Rights Reserved.
 * @version 1.0  2012-12-20 下午4:43:22  by （litao02@zuche.com）创建
 */
public enum RemoteType {
	 HESSIAN, HTTP,TCP,UDP;

	    public static String isRemoteType(RemoteType type) {
	        if (HESSIAN == type) {
	            return RemoteClientFactory.HESSIAN;
	        }
	        else if(HTTP == type){
	        	return RemoteClientFactory.HTTP;
	        }
	        else if(TCP == type){
	        	return RemoteClientFactory.TCP;
	        }else if(UDP == type){
	        	return RemoteClientFactory.UDP;
	        }
			return null;
	    }

    public static String getCleanRemoteType(RemoteType type) {
        if (HESSIAN == type) {
            return "HESSIAN";
        }
        else if(HTTP == type){
            return "HTTP";
        }
        else if(TCP == type){
            return "TCP";
        }else if(UDP == type){
            return "UDP";
        }
        return null;
    }

    public static RemoteType getRemoteType(String type) {
        if(RemoteClientFactory.HESSIAN.equals(type)){
            return HESSIAN;
        }else if(RemoteClientFactory.HTTP.equals(type)){
            return HTTP;
        }if(RemoteClientFactory.TCP.equals(type)){
            return TCP;
        }if(RemoteClientFactory.UDP.equals(type)){
            return UDP;
        }
        return null;
    }
}
