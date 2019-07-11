/**
 * Description: RegisterCenterUtil.java
 * All Rights Reserved.
 *
 * @version 2.5  2013-4-18 上午9:32:40  by 李洪波（hb.li@zhuche.com）创建
 */
package com.neo.rpc.registercenter;


import com.neo.rpc.RemoteClientFactory;
import com.neo.rpc.RemoteType;
import com.neo.rpc.RpcRuntimeException;
import com.neo.rpc.util.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 注册中心工具方法
 * All Rights Reserved.
 *
 * @version 3.0  2013-4-18 上午9:32:40  by 李洪波（hb.li@zhuche.com）创建
 */
public final class ClientRegisterCenterUtil {

    public static final String REGISTER_CENTER = "registerCenter";

    private static final Logger LOG = LoggerFactory.getLogger(ClientRegisterCenterUtil.class);

    /**
     * 客户端缓存对象
     */
    private static ConcurrentHashMap<String, String> cacheRegisterCenterMap = new ConcurrentHashMap<String, String>();

    //锁对象
    private static Object obj = new Object();

    private ClientRegisterCenterUtil() {

    }

    /**
     * Description: 客户端从注册中心获取服务地址信息
     *
     * @param serviceName
     * @return
     * @Version 3.0 2013-4-18 上午9:34:56 by 李洪波（hb.li@zhuche.com）创建
     */
    public static String getUrl(String serviceName) {
//        String idc = GlobalMessage.getIdc();
        String idc = "";
        String name = idc + "_" + serviceName;
        String url = cacheRegisterCenterMap.get(name);
        if (url == null) {
            synchronized (obj) {
                url = cacheRegisterCenterMap.get(name);
                if (url != null) {
                    return url;
                }
                url = getRemoteUrl(serviceName);
                if (url != null) {
                    cacheRegisterCenterMap.put(name, url);
                    return url;
                }

            }
        }

        return url;
    }

    /**
     * Description: 通过注册中心获取指定服务的地址信息
     *
     * @param serviceName
     * @return
     * @Version 3.0 2013-4-18 上午11:31:44 by 李洪波（hb.li@zhuche.com）创建
     */
    private static String getRemoteUrl(String serviceName) {

        Properties ps = PropertiesReader.getProperties(REGISTER_CENTER);
        if (ps.size() > 1) {
            throw new RpcRuntimeException("注册中心配置错误，请检查配置！");
        }

        try {
            Iterator<Object> it = ps.keySet().iterator();
            if (it.hasNext()) {
                String registerCenterService = String.valueOf(it.next()).trim();
                String registerCenterUrl = ps.getProperty(registerCenterService);
                if (StringUtils.isEmpty(registerCenterUrl)) {
                    throw new RpcRuntimeException("注册中心地址不能为空，请检查配置");
                }

                //兼容并处理测试环境整合后的事项
                String env = "";
                if (registerCenterUrl.contains("test")
                        &&!registerCenterUrl.contains("luckycoffee")) {
                    try {
                        env = registerCenterUrl.substring(registerCenterUrl.indexOf("prism"),
                                registerCenterUrl.indexOf(".")).replaceAll("prism", "");
                    } catch (Exception e) {
                        LOG.error("整合后的prism测试环境，解析url出错：{}", e.getMessage());
                    }
                    //test/test01/test02
                    if (StringUtils.isEmpty(env)) {
                        throw new RpcRuntimeException("注册中心地址配置有误，请检查配置，url=" + registerCenterUrl);
                    }
                    //统一请求到http://prismtest.10101111.com/prism
                    registerCenterUrl = registerCenterUrl.replaceAll("02", "").replaceAll("03", "");
                }
                Object object = RemoteClientFactory.getInstance(registerCenterUrl.trim(), RemoteType.HESSIAN).executeObject(registerCenterService, serviceName + "," + "192.168..." + "," + env);
                if (object != null) {
                    return object.toString();
                }
            }
        } catch (Exception e) {
            LOG.error("获取远程服务异常" + serviceName);
        }

        return null;


    }

}
