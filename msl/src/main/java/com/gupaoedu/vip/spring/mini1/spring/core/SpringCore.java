package com.gupaoedu.vip.spring.mini1.spring.core;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.gupaoedu.vip.spring.mini1.demo.action.DemoAction;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Autowired;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Controller;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Service;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringCore {
    private final String CLASS_PACKAGE_NAME = "com.gupaoedu.vip.spring.mini1.demo";
    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    private List<String> classNames = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(1);
    }

    @Test
    public void start() {
        //扫包
        doScanner(CLASS_PACKAGE_NAME);
        System.out.println("扫描完成，classNames"+ JSONObject.toJSONString(classNames));
        //注册
        doRegistry();
        System.out.println("注册完成,beanMap"+beanMap);

        //依赖注入
        doAutowired();

        System.out.println(beanMap.get("iDemoService"));
        DemoAction demoAction = (DemoAction) beanMap.get("demoAction");
        System.out.println(demoAction.getDemoService());
        demoAction.query(null,null,"王屁呆");
    }


    private void doScanner(String packageName) {
        //获得URL
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName+"."+file.getName());
            } else {
                classNames.add(packageName+"."+file.getName().replaceAll(".class",""));
            }
        }




    }

    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                //判断是否带有Controller  Service注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //默认beanName 类名首字母小写
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    System.out.println(clazz.newInstance());
                    beanMap.put(beanName,clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)){
                    Service service = clazz.getAnnotation(Service.class);
                    String beanNmae = service.value();
                    if (Strings.isNullOrEmpty(beanNmae)) {
                        beanNmae = lowerFirstCase(clazz.getSimpleName());
                    }

                    Object instance = clazz.newInstance();
                    beanMap.put(beanNmae,instance);

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        beanMap.put(lowerFirstCase(i.getSimpleName()),instance);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


    private void doAutowired() {
        if (beanMap.isEmpty()) {return;}
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            //得到所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {continue;}

                Autowired autowired = field.getAnnotation(Autowired.class);
                String beanName = autowired.value().trim();
                if (Strings.isNullOrEmpty(beanName)) {
                    beanName = field.getName();
                }

                //给属性赋值（注入）
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
    }


}
