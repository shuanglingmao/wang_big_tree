package com.neo.test;


import java.lang.reflect.Field;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
public class MyValidator {

    public static <T> boolean validate(T object) throws IllegalAccessException {
        if (object == null){
            throw new RuntimeException("object can not be null");
        }
        try{
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                NotNull annotation = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                if (annotation != null){
                    Object o = field.get(object);
                    if (o == null){
                        return false;
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Student student = new Student();
        System.out.println(MyValidator.validate(student));
    }


    @interface NotNull {

    }

    static class Student {
        @NotNull
        private String namel;
        @NotNull
        private Integer age;
    }

}
