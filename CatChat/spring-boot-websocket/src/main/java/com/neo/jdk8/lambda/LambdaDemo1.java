package com.neo.jdk8.lambda;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 毛双领
 * @description
 * @create 2019-09-18 11:34
 */
public class LambdaDemo1 {
    private static List<Apple> apples = new ArrayList<Apple>(){{
        add(new Apple(1L,"红色",10));
        add(new Apple(2L,"黑色",9));
        add(new Apple(3L,"黄色",8));
        add(new Apple(4L,"白色",7));
        add(new Apple(5L,"绿色",6));
        add(new Apple(6L,"紫色",5));
    }};


    @Test
    public void test1() {
        //按重量排序
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(),o2.getWeight());
            }
        };

        //用lambda表达式
        Comparator<Apple> lambda1 = (o1, o2) -> Integer.compare(o1.getWeight(),o2.getWeight());


        List<Integer> list = Lists.newArrayList(5, 4, 3, 2, 1);
        //从小到大排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        list.forEach(System.out :: println);

        //lambda
        list.sort((o1, o2) -> Integer.compare(o1,o2));
        //方法引用
        list.sort(Integer :: compare);
    }

    /**
     *  Predicate  函数式接口
     */
    @Test
    public void predicate() {
        //通过自己定义的方法 筛选重量大于5的苹果
        List<Apple> result = this.filter(apples, apple -> apple.getWeight() > 5);
        //通过stream直接筛选重量大于5的苹果
        List<Apple> result1 = apples.stream().
                filter(apple -> apple.getWeight() > 5).
                collect(Collectors.toList());
    }

    private <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        list.forEach(element -> {
            if (predicate.test(element)) {
                result.add(element);
            }
        });
        return result;
    }

    /**
     * Consumer 消费
     */
    @Test
    public void consumer () {
        //内部类
        apples.forEach(new Consumer<Apple>() {
            @Override
            public void accept(Apple apple) {
                System.out.println(apple);
            }
        });
        //lambda
        apples.forEach(apple -> System.out.println(apple));
        //方法引用
        apples.forEach(System.out :: println);
    }

    /**
     * 给个参数，返回另外一个参数
     */
    @Test
    public void function() {
        //获取所有的苹果颜色
        List<String> colors = apples.stream().map(Apple::getColor).collect(Collectors.toList());
        colors.forEach(System.out :: println);

        //获取所有苹果的重量
        List<Integer> weights = this.map(apples, Apple::getWeight);
        weights.forEach(System.out :: println);

    }
    private <T,F> List<T> map(List<F> list, Function<F,T> function) {
        List<T> result = new ArrayList<>();
        list.forEach(element -> {
            T apply = function.apply(element);
            result.add(apply);
        });
        return result;
    }

    /**
     * 避免自动装箱 带来的性能影响
     */
    @Test
    public void text() {
        OptionalInt max = apples.stream().mapToInt(null).max();
        max.getAsInt();
    }

}
