package com.neo.rpc.loadbalance;
/**
 * 负载均衡策略枚举类<br/> Created on 2013-9-6 下午3:34:48
 * @author  litao(litao02@zhuche.com)
 * @since 3.2
 */
public enum LoadBalancerTypeEnum {
	
	WeightedRoundRobin,Random,Hash,LeastActive;                  //权重,随机,hash，最少连接数
	
   /**
    * 根据负载均衡策略字符串转化枚举<br/> Created on 2013-9-6 下午3:36:20
    * @author  litao(litao02@zhuche.com)
    * @since 3.2 
    * @param type
    * @return
    */
   public static LoadBalancerTypeEnum valueOfEnum(String type) {
		
		if(type.equalsIgnoreCase("WeightedRoundRobin")){
			return WeightedRoundRobin;
		}else if(type.equalsIgnoreCase("random")){
			return Random;
		}else if(type.equalsIgnoreCase("hash")){
			return Hash;
		}else if(type.equalsIgnoreCase("leastActive")){
			return LeastActive;
		}
		else{
			return null;
		}
	}
}
