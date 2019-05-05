package util;

import com.alibaba.fastjson.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-05-02 22:01
 */

public class FilterUtils {

    /**
     * 过滤list  set
     * @param collection
     * @param filter
     * @param <E>
     */
    public static <E> void filter(Collection<E> collection,Filter<E> filter) {
        filter(collection.iterator(),filter);
    }

    /**
     * 过滤map
     * @param map
     * @param filter
     * @param <K>
     * @param <V>
     */
    public static <K,V> void filter(Map<K,V> map, Filter<K> filter) {
        filter(map.keySet().iterator(),filter);
    }

    private static <E>void filter(Iterator<E> iterator,Filter<E> filter) {
        while (iterator.hasNext()) {
            if (filter.filter(iterator.next())) {
                iterator.remove();
            }
        }
    }


    public interface Filter<T>{
        boolean filter(T t);
    }

    public static String getLocalIp(){
        try{
            //根据网卡取本机配置的IP
            Enumeration<NetworkInterface> netInterfaces=NetworkInterface.getNetworkInterfaces();
            String ip = null;
            a: while(netInterfaces.hasMoreElements()){
                NetworkInterface ni=netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress ipObj = ips.nextElement();
                    if (ipObj.isSiteLocalAddress()) {
                        ip =  ipObj.getHostAddress();
                        break a;
                    }
                }
            }
            return ip;
        }catch (Exception e){
            System.out.println("获取ip地址失败，返回0.0.0.0"+ e);
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }
}
