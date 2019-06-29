package com.neo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/26 0026
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */

public class ReflectTestDemo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {



//        //调用指定方法
//        Method show = clazz.getDeclaredMethod("show");
//        Object invoke = show.invoke(wdc);
//        System.out.println(invoke);

    }

    @Test
    public void before() {
        //反射之前
        Person p1 = new Person("赵二狗",18);
        p1.setAge(21);
        System.out.println(p1.toString());
        //无法调用私有方法，无法直接调用私有属性,无法调用私有构造器
        p1.show();
    }

    @Test
    public void after() throws Exception{
        //反射之后
        Class clazz = Person.class;
        //通过反射创建Person类的对象
        Constructor constructor = clazz.getConstructor(String.class, Integer.class);
        Person wdc = (Person) constructor.newInstance("王大锤", 33);
        System.out.println(wdc.toString());
        //调用指定属性
        Field age = clazz.getDeclaredField("age");
        //强制访问私有属性
        age.setAccessible(true);
        age.set(wdc,66);
        System.out.println(wdc.toString());
        //调用私有构造器
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Object lerd = constructor1.newInstance("李二蛋");
        System.out.println(lerd.toString());
        //调共有方法  wdc.show();
        Method showMethod = clazz.getDeclaredMethod("show");
        showMethod.invoke(wdc);
        //调私有方法
        Method sayHelloMethod = clazz.getDeclaredMethod("sayHello", String.class);
        sayHelloMethod.setAccessible(true);
        //相当于 lerd.sayHello("德玛西亚")
        System.out.println(sayHelloMethod.invoke(lerd, "德玛西亚"));
    }

    @Test
    public void test3() {
        //类
        Class c1 = Object.class;
        //接口
        Class c2 = Runnable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        //枚举类
        Class c5 = ElementType.class;
        //注解
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        //Class本身
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];

        Class c10 = a.getClass();
        Class c11 = a.getClass();
        //只要数组的元素类型与纬度一样（都是int类型，都是一位数组），就是同一个Class
        System.out.println(c10 == c11);
    }

    @Test
    public void test4() {
        final Person person = new Person();
        Class clazz1 = person.getClass();
        Class clazz2  = Person.class;
        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz1 == clazz2);
    }

    @Test
    public void test5() throws Exception{
        Person person = new Person();
        person.setAge(21);
        final Class<? extends Person> clazz = person.getClass();
        final Field[] declaredFields = clazz.getDeclaredFields();
        final Field name = clazz.getDeclaredField("name");
        final Field age = clazz.getDeclaredField("age");
        System.out.println(name);
        System.out.println(age);
    }

}

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;

    private Person(String name) {
        this.name = name;
    }

    private String sayHello(String name) {
       return "Hello,"+name;
    }
    public void show() {
        System.out.println("show show show");
    }
}

