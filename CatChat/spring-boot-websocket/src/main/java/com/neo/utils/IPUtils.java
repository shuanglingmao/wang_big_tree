package com.neo.utils;

import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: ip工具类
 *
 * @author shuangling.mao
 * @date 2019/6/14 10:44
 */
public class IPUtils {

    private static Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String unknown = "unknown";
        if (request == null) {
            return unknown;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String extractIp(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        } else {
            String remoteServiceIp = null;
            Matcher m = p.matcher(url.toString());
            if (m.find()) {
                remoteServiceIp = m.group();
            }

            return remoteServiceIp;
        }
    }

}
