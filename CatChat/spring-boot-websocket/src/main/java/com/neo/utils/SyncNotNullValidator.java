package com.neo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/24 0024
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
@Slf4j
public class SyncNotNullValidator implements ConstraintValidator<SyncNotNull,Object> {
    private String[] fieldNames;
    private String[] allFieldNames;
    @Override
    public void initialize(SyncNotNull syncNotNull) {
        this.fieldNames = syncNotNull.fieldNames();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean result = false;
        setAllFieldNames(object);
        try {
            if (fieldNames.length > 0) {
                for (String fieldName : fieldNames) {
                    if (!isExist(fieldName)) {
                        result = false;
                        context.buildConstraintViolationWithTemplate("未找到名为"+fieldName+"的参数").addConstraintViolation();
                        break;
                    }
                    //只要有一个参数不为空 直接返回true
                    if (getProperty(object,fieldName) != null) {
                        result =  true;
                        break;
                    }
                }
            } else {
                for (String fieldName : allFieldNames) {
                    //只要有一个参数不为空 直接返回true
                    if (getProperty(object,fieldName) != null) {
                        result = true;
                        break;
                    }
                }
                context.buildConstraintViolationWithTemplate("不能所有参数都为空").addConstraintViolation();
            }
        } catch (Exception e) {
            log.error("SyncNotNullValidator校验器异常!",e);
            result = true;
        }
        return result;
    }


    private void setAllFieldNames(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        allFieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            allFieldNames[i] = fields[i].getName();
        }

    }


    private boolean isExist(String name) {
        for (String fieldName : allFieldNames) {
            if (fieldName.equals(name)) {
                return true;
            }
        }
        return false;
    }


    public static Object getProperty(Object target,String property) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        final Field declaredField = target.getClass().getDeclaredField(property);
        declaredField.setAccessible(true);
        return  declaredField.get(target);
    }


}
