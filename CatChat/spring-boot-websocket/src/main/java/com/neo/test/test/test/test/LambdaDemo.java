package com.neo.test.test.test.test;

import com.google.common.collect.Lists;
import com.neo.model.User;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/20 0020
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
public class LambdaDemo {
    @Test
    public void testLambda() {

        //  一个参数没有返回值
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("快来玩耍吧");

        System.out.println("*****************************");

        Consumer<String> con1 = s ->  System.out.println(s);
        con1.accept("aaa");
    }

    /**
     * ###### 四大核心函数式接口
     - Consumber<T>  给一个T参数，无返回 消费型
     - Supplier<T> 返回T对象             供给型
     - Function<T,R>                     函数型
     - Predicate<T> 返回boolean          断定型
     */
    @Test
    public void funciton() {
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("Hello Word!");

        System.out.println("************************************");

        //System.out.println(str)  已经有实现的方法了，可以使用方法引用
        PrintStream println = System.out;
        Consumer<String> consumer1 = println ::println;
        Consumer<String> consumer2 = System.out ::println;
    }

    @Test
    public void test1() {
        User user = new User();
        user.setUserName("赵二狗");
        user.setId(1L);
        user.setPassWord("123456");

        Supplier<String> sup = () -> user.getUserName();
        System.out.println(sup.get());

        System.out.println("******************");

        Supplier<String> sup1 = user :: getUserName;
        System.out.println(sup1.get());
    }


    @Test
    public void test2() {
        //Comparator中的 int compare(T t1, T t2);
        //Integer中的    int compare(T t1, T t2);
        Comparator<Integer> com1 = (t1,t2) -> Integer.compare(t1,t2);
        System.out.println(com1.compare(12,21));

        System.out.println("****************");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12,3));

    }
    
    @Test
    public void test3() {
        Function<Double,Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };

        Function<Double,Long> function1 = (d) -> {return Math.round(d);};
        Function<Double,Long> function2 = d -> Math.round(d);
        //Function long apply(double d)
        //Math     long round(double d)
        Function<Double,Long> function3 = Math :: round;
        System.out.println(function3.apply(25.6));
    }



    @Test
    public void test4() {
        //Comparator 中 的 int comapre(T t1,T t2)
        //String     中 的 int t1.compareTo(t2)
        //  两个参数，t1作为调用者 t2作为参数 也可以作为方法引用
        Comparator<String> com1 = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };

        Comparator<String> com2 = (s1,s2) -> s1.compareTo(s2);
        Comparator<String> com3 = String :: compareTo;

    }

    @Test
    public void test5() {

        BiPredicate<String,String> pre = new BiPredicate<String, String>() {
            @Override
            public boolean test(String s1, String s2) {
                return s1.equals(s2);
            }
        };
        //BiPredicate   boolean  test(t1,t2)
        //String        boolean  t1.equals(t2)
        BiPredicate<String,String> pre1 = (s1,s2) -> s1.equals(s2);
        BiPredicate<String,String> pre2 = String :: equals;
    }

    @Test
    public void test6() {
        Function<User,String> function = new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserName();
            }
        };
        //Function R apply(T t);
        //User     String getName();
        // 参数作为调用者  t.getName()  所以还可以用方法引用
        Function<User,String> function1 = user -> user.getUserName();
        Function<User,String> function2 = User :: getUserName;

    }

    @Test
    public void test7() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Lists.transform(list, new com.google.common.base.Function<Integer, String>() {
            @Override
            public String apply(@Nullable Integer integer) {
                return String.valueOf(integer);
            }
        });

        List<String> transform = Lists.transform(list, String::valueOf);
        System.out.println(transform.toString());
    }

    @Test
    public void test8() {
        Supplier<User> sup = new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        };

        //Supplier   User get()
        //User       User 空参构造器
        Supplier<User> sup1 = () -> new User();
        Supplier<User> sup2 = User :: new;

    }

    @Test
    public void test9() {
        Function<Integer,User> fun = new Function<Integer, User>() {
            @Override
            public User apply(Integer id) {
                return new User(id);
            }
        };
        //Function  User apply(Integer id)
        //User      User  User(Integer id)  参数为Integer的有参构造器
        Function<Integer,User> fun1 = id -> new User(id);
        Function<Integer,User> fun2 = User :: new;
    }

    @Test
    public void test10() {
        //数组可以看成特殊的类，构造器引用 同样适用
        Function<Integer,String[]> fun = new Function<Integer, String[]>() {
            @Override
            public String[] apply(Integer length) {
                return new String[length];
            }
        };

        Function<Integer,String[]> fun1 = length -> new String[length];
        Function<Integer,String[]> fun2 = String[] ::new;
    }
}
