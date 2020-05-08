package com.gupaoedu.vip.pattern.strage;

import com.gupaoedu.vip.spring.mini1.spring.annotation.Autowired;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalSalaryStargeFactoryV2 {
    /**
     * 策略类所在的包名
     */
    private static final String CAL_SALARY_PACKAGE = "com.gupaoedu.vip.pattern.strage";
    /**
     * 策略列表
     */
    private List<Class<? extends CalSalaryStrage>> calSalaryStrages;

    /**
     * 单例
     */
    private CalSalaryStargeFactoryV2() {
        init();
    }

    public CalSalaryStrage getCalSalaryStrage(Double hours) {
        //在策略 列表中查找策略
        for (Class<? extends CalSalaryStrage> calSalaryStrage : calSalaryStrages) {
            //获得策略类的注解
            Hours hour = getHours(calSalaryStrage);
            //根据注解的值选择相应的策略
            if (hours >= hour.min() && hours <= hour.max()) {
                try {
                    return calSalaryStrage.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("获得策略失败！");
                }
            }

        }
        throw new RuntimeException("获得策略失败！");

    }

    private Hours getHours(Class<? extends CalSalaryStrage> calSalaryStrage) {
//        //获得类的方法
//        try {
//            Method calSalary = calSalaryStrage.getMethod("calSalary", Double.class);
//            calSalary.
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        Annotation[] annotations = calSalaryStrage.getAnnotations();
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Hours) {
                return (Hours) annotation;
            }
        }
        return null;
    }

    /**
     * 工厂初始化时 初始化策略列表
     */
    private void init() {
        calSalaryStrages = new ArrayList<Class<? extends CalSalaryStrage>>();

        /**获得包下所有的class文件*/
        File[] files = getResources();
        Class<CalSalaryStrage> calSalaryStrageClazz = null;
        try {
            calSalaryStrageClazz = (Class<CalSalaryStrage>) this.getClass().getClassLoader().loadClass(CalSalaryStrage.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("初始化策略接口失败");
        }


        for (File file : files) {
            //载入包下的类
            try {
                Class<?> clazz = this.getClass().getClassLoader()
                        .loadClass(CAL_SALARY_PACKAGE + "." + file.getName().replaceAll("\\.class", ""));
                //判断是不是CalSalaryStrage实现类并且不是CalSalaryStrage本身，满足的话加入策略列表
                if (CalSalaryStrage.class.isAssignableFrom(clazz) && clazz != calSalaryStrageClazz) {
                    calSalaryStrages.add((Class<? extends CalSalaryStrage>) clazz);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("初始化策略接口失败");
            }

        }

    }

    private File[] getResources() {
        try {
            URL url = this.getClass().getClassLoader().getResource(CAL_SALARY_PACKAGE.replaceAll("\\.", "/"));
            File file = new File(url.toURI());
            return file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".class");
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("未找到合适的策略资源");
        }
    }

    private static class CalSalaryStargeFactoryInstance {
        private static CalSalaryStargeFactoryV2 instance = new CalSalaryStargeFactoryV2();
    }

    public static CalSalaryStargeFactoryV2 getInstace() {
        return CalSalaryStargeFactoryInstance.instance;
    }


}
