package com.gupaoedu.vip.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterMap {
    private RegisterMap(){}
    private static Map<String,Object> map = new ConcurrentHashMap<String, Object>(1);

    public static RegisterMap getInstance(String name) {
       //线程不安全
       /* if (name == null) {
           name = RegisterMap.class.getName();
       }
        if (map.get(name) == null) {
            try {
                map.put(name,new RegisterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterMap) map.get(name);*/
        if (map.containsKey(name)) {
            return (RegisterMap) map.get(name);
        } else {
            try {
                map.put(name,new RegisterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterMap) map.get(name);

    }
}
