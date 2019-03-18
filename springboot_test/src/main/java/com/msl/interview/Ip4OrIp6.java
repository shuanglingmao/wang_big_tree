package com.msl.interview;

/**
 * Description: 编写一个程序 判断输入地址是IP4地址还是IP6地址
 * ip4 : 10.99.39.78          192.168.1.200
 * ip6 : fe80::5d4d:724d:5923:319a%5
 *
 * @author shuangling.mao
 * @date 2019/3/18 18:07
 */
public class Ip4OrIp6 {
    public static void main(String[] args) {
        final boolean ip4 = new Ip4OrIp6().isIp4("10.99.39.78 ");
        System.out.println(ip4);
    }



    public boolean isIp4(String ip4) {
        if (ip4 == null || ip4.length() == 0) {
            return false;
        }
        final String[] split = ip4.trim().split("\\.");
        if (split.length != 4) {
            return false;
        }
        if (split[0].charAt(0) == '0') {
            return false;
        }
        for(String s : split) {
            Integer n = Integer.parseInt(s);
            if (n < 0 || n > 255) {
                return false;
            }
        }
        return true;
    }

    public boolean isIp6(String ip6) {

        return true;
    }
}
