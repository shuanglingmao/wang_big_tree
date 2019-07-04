package com.neo.utils;


import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public class SpringApplicationContext {

    private static ApplicationContext ac = null;

    private static ApplicationContext mvcAc = null;

    private SpringApplicationContext() {}

    public static Object getBean(String id) {
        Object bean = null;

        try {
            bean = mvcAc.getBean(id);
        } catch (Exception var3) {
            bean = ac.getBean(id);
        }

        return bean;
    }

    public static boolean isSingleton(String id) {
        boolean result;
        try {
            result = mvcAc.isSingleton(id);
        } catch (Exception var3) {
            result = ac.isSingleton(id);
        }

        return result;
    }

    public static <T> Map<String, T> getBeansByType(Class<T> type) {
        try {
            return BeanFactoryUtils.beansOfTypeIncludingAncestors(mvcAc, type);
        } catch (Exception var2) {
            return BeanFactoryUtils.beansOfTypeIncludingAncestors(ac, type);
        }
    }


    public static Set<String> getBeanKeyByType(Class<?> type) {
        return getBeansByType(type).keySet();
    }

    public static void initApplicationContext(ApplicationContext ac) {
        SpringApplicationContext.ac = ac;
        System.out.println("SpringApplicationContext的  ApplicationContext 初始化成功");
    }

    public static void initMvcApplicationContext(ApplicationContext mvcAc) {
        SpringApplicationContext.mvcAc = mvcAc;
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> map1 = new HashMap(0);
        Map<String, Object> map2 = new HashMap(0);
        if (ac != null) {
            map1 = ac.getBeansWithAnnotation(annotationType);
        }

        if (mvcAc != null) {
            map2 = mvcAc.getBeansWithAnnotation(annotationType);
        }

        Map<String, Object> result = new HashMap(((Map)map1).size() + ((Map)map2).size());
        result.putAll((Map)map1);
        result.putAll((Map)map2);
        return result;
    }

    public static boolean containsBean(String id) {
        if (mvcAc.containsBean(id)) {
            return true;
        } else {
            return ac.containsBean(id);
        }
    }
}
