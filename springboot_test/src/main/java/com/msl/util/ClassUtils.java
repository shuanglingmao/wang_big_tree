package com.msl.util;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019/3/14 10:21
 */
public final class ClassUtils {
    private ClassUtils() {
    }

    public static void getFields(Class<?> clazz, List<Field> allField) {
        Field[] fields = clazz.getDeclaredFields();

        for(int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            allField.add(field);
        }

        if (clazz.getSuperclass() != null) {
            getFields(clazz.getSuperclass(), allField);
        }

    }

    public static Field getAssignField(Class<?> clazz, String field) {
        try {
            Field fil = clazz.getDeclaredField(field);
            return fil;
        } catch (SecurityException var3) {
            //异常定义
            throw new RuntimeException(var3);
        } catch (NoSuchFieldException var4) {
            return clazz.getSuperclass() != null ? getAssignField(clazz.getSuperclass(), field) : null;
        }
    }
}
