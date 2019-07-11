/*
 * Description: PropertiesReader.java
 * Copyright belongs to the original author or authors.
 */
package com.neo.rpc.util;

import com.neo.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Properties文件读取器；
 * Properties文件必须存在class根目录下
 * <br/> Created on 2012-10-29 下午03:42:06
 * @author  李青原(liqingyuan1986@aliyun.com)
 * @since 1.0
 */
public final class PropertiesReader {
    private static final Logger CLASS_LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
    //缓存properties文件
    private static Map<String, Properties> propertiesMap = new ConcurrentHashMap<String, Properties>();
    //默认property配置文件后缀
    private static final String DEFAULT="_default";
    //缓存有序properties文件
//    private static Map<String, OrderProperties> orderPropertiesMap = new ConcurrentHashMap<String, OrderProperties>();
    
    private PropertiesReader(){}

    /**
     * 获取指定的properties 中 指定的属性
     * <br/> Created on 2013-1-31 下午1:30:10
     * @author 李洪波（hb.li@zhuche.com）
     * @since 1.0
     * @param propertiesName
     * @param attributeName
     * @param clazz，返回的类型（Integer.class,Boolean.class,String.class,Long.class），只支持此四种类型
     * @return
     */
    public static<T> T getAppointPropertiesAttribute(String propertiesName,String attributeName,Class<T> clazz){
    	
    	Assert.notNull(clazz, "获取指定类型“clazz”参数为null !");
    	
    	Assert.isExist(new Object[]{Integer.class,Boolean.class,String.class,Long.class,Double.class}, clazz, "参数“clazz” 类型不在指定的类型中！");
    	
    	Properties ps = getProperties(propertiesName);
    	return getAppointPropertiesAttribute(ps, attributeName, clazz);
    }
    
    /**
     * 获取指定的properties 中 指定的属性
     * <br/> Created on 2013-1-31 下午1:30:10
     * @author 李青原（hb.li@zhuche.com）
     * @since 3.2.9
     * @param ps
     * @param attributeName
     * @param clazz，返回的类型（Integer.class,Boolean.class,String.class,Long.class），只支持此四种类型
     * @return
     */
    public static<T> T getAppointPropertiesAttribute(Properties ps,String attributeName,Class<T> clazz){
    	
    	Assert.notNull(clazz, "获取指定类型“clazz”参数为null !");
    	
    	Assert.isExist(new Object[]{Integer.class,Boolean.class,String.class,Long.class,Double.class}, clazz, "参数“clazz” 类型不在指定的类型中！");

    	if(ps != null){
    		String value = ps.getProperty(attributeName);
    		if(value == null || "".equals(value)){
    			return null;
    		}
    		
    		if(clazz == Boolean.class){
    			return clazz.cast(Boolean.parseBoolean(value));
    		}else if(clazz == Integer.class){
    			return clazz.cast(Integer.parseInt(value));
    		}else if(clazz == Long.class){
    			return clazz.cast(Long.parseLong(value));
    		}else if(clazz == Double.class){
    			return clazz.cast(Double.parseDouble(value));
    		}else{
    			return clazz.cast(value);
    		}
    		
    	}
    	return null;
    }

    /**
     * 根据参数创建Properties对象
     * <br/> Created on 2012-10-29 下午03:42:06
     * @author  李青原(liqingyuan1986@aliyun.com)
     * @since 1.0 
     * @param propertiesName
     * @return
     */
    public static Properties getProperties(String propertiesName) {
       
    	return getProperties(propertiesName, true);
    	
    }
    
    /**
     * 根据参数创建Properties对象
     * <br/> Created on 2012-10-29 下午03:42:06
     * @author  李青原(liqingyuan1986@aliyun.com), 陈兆英(zy.chen03@zuche.com)
     * @since 1.0 
     * @param propertiesName 资源文件名(资源文件必须存在class根目录下)
     * @param isCache 是否缓存配置的对象(lhb)
     * @return Properties对象
     */
	public static Properties getProperties(String propertiesName,
			boolean isCache) {
		Properties resultProperties = propertiesMap.get(propertiesName);

		// 缓存没有，重新读取并存入缓存
		if (resultProperties == null) {
			Properties defaultProp = createProperties(propertiesName + DEFAULT); // 获取默认property文件
			resultProperties = createProperties(propertiesName);
			if (defaultProp != null && resultProperties != null) {
				resultProperties.putAll(defaultProp);
			}else if(defaultProp != null){
				resultProperties = defaultProp ;
			}

			if (isCache) {
				if(resultProperties != null){
					propertiesMap.put(propertiesName, resultProperties);
				}
			}
		}

		return resultProperties;
	}

//    /**
//     * 根据参数创建Properties对象,获取的properties为有序的
//     * <br/> Created on 2012-10-29 下午03:42:06
//     * @author  李青原(liqingyuan1986@aliyun.com)
//     * @since 1.0
//     * @param propertiesName 资源文件名(资源文件必须存在class根目录下)
//     * @return Properties对象
//     */
//    public static OrderProperties getOrderProperties(String propertiesName) {
//        OrderProperties resultProperties = orderPropertiesMap.get(propertiesName);
//
//        //缓存没有，重新读取并存入缓存
//        if (resultProperties == null) {
//
//        	OrderProperties defaultProp = createOrderProperties(propertiesName + DEFAULT); // 获取默认property文件
//        	 resultProperties = createOrderProperties(propertiesName);
//			if (defaultProp != null && resultProperties != null) {
//				resultProperties.putAll(defaultProp);
//			}else if(defaultProp != null){
//				resultProperties = defaultProp ;
//			}
//
//
//           if(resultProperties != null){
//        	   orderPropertiesMap.put(propertiesName, resultProperties);
//           }
//        }
//
//        return resultProperties;
//    }

    private static Properties createProperties(String propertiesName) {
        InputStream fis = null;
        Properties properties = null;
        try {
            fis = PropertiesReader.class.getResourceAsStream("/" + propertiesName + ".properties");
            if (fis != null) {
                properties = new Properties();
                properties.load(fis);
                fis.close();
            }
        } catch (Exception e) {
        	CLASS_LOGGER.error(e.getMessage(),e);
            fis = null;
        }
        return properties;
    }


//    private static OrderProperties createOrderProperties(String propertiesName) {
//        InputStream fis = null;
//        OrderProperties properties = null;
//        try {
//            fis = PropertiesReader.class.getResourceAsStream("/" + propertiesName + ".properties");
//            if (fis != null) {
//                properties = new OrderProperties();
//                properties.load(fis);
//                fis.close();
//            }
//        } catch (Exception e) {
//        	CLASS_LOGGER.error(e.getMessage(),e);
//            fis = null;
//        }
//        return properties;
//    }
}
