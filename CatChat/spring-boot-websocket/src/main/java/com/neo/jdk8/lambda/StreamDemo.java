package com.neo.jdk8.lambda;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * @author 毛双领
 * @description
 * @create 2019-09-18 14:23
 */
public class StreamDemo {
    private static List<Apple> apples = new ArrayList<Apple>(){{
        add(new Apple(3L,"红色",10));
        add(new Apple(2L,"黑色",9));
        add(new Apple(1L,"黄色",8));
        add(new Apple(4L,"白色",7));
        add(new Apple(5L,"绿色",5));
        add(new Apple(7L,"紫色",5));
        add(new Apple(6L,"粉色",5));
    }};


    /**
     * list转map
     */
    @Test
    public void list2Map() {
        //以id为Key,对象为Value   如果有重复的Key会报错, Duplicate key Apple(id=6, color=紫色, weight=5)
//        Map<Long, Apple> collect = apples.stream()
//                .collect(Collectors.toMap(Apple::getId, v -> v));
        //以id为Key,对象为Value    如果有重复的Key不会报错,策略是只保留一个Value
        Map<Long, Apple> collect = apples.stream()
                .collect(Collectors.toMap(Apple::getId, v -> v, (a,b) -> a));
        System.out.println(collect);

        //以id为Key,颜色为Value   如果有重复的Key不会报错,策略是两个颜色用:拼接
        Map<Long, String> collect1 = apples.stream()
                .collect(Collectors.toMap(Apple::getId, Apple::getColor,(a,b) -> a+":"+b));
        System.out.println(collect1);

        //以weight为key,  相同weight的apple List  为value
        Map<Integer, List<Apple>> collect2 = apples.stream()
                .collect(Collectors.groupingBy(Apple::getWeight));
        System.out.println(collect2);

        //以weight为key,  相同weight的apple List  为value  返回concurrentMap
        ConcurrentMap<Integer, List<Apple>> collect3 = apples.stream()
                .collect(Collectors.groupingByConcurrent(Apple::getWeight));
        System.out.println(collect2);

        //自己封装一下
        ConcurrentMap<Integer, List<Apple>> map = toConcurrentMap(apples, Apple::getWeight);
    }
    private <K,T> ConcurrentMap<K,List<T>> toConcurrentMap(List<T> list, Function<T, K> function) {
        return list.stream().collect(Collectors.groupingByConcurrent(function));
    }

    /**
     * map操作
     */
    @Test
    public void mapTest() {
        //将apples里的id转换为String,以逗号分隔
        String collect = apples.stream()
                .map(apple -> String.valueOf(apple.getId()))
                .collect(Collectors.joining(","));
        System.out.println(collect);
        //上面的方式等价于
        //StringUtils.join(ids,",");

        //将apples里的id转换为String收集到List
        List<String> collect1 = apples.stream()
                .map(apple -> String.valueOf(apple.getId()))
                .collect(Collectors.toList());
        System.out.println(collect1);
    }

    /**
     * 转换为数组
     */
    @Test
    public void toArray() {
        //把apples转成乘数组
        Apple[] arrays = apples.stream().toArray(Apple[]::new);
        System.out.println(Arrays.toString(arrays));

        //把所有的颜色转换成数组
        String[] strings = apples.stream().map(Apple::getColor).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));

        //等价于上面
        apples.stream().map(Apple::getColor).toArray(new IntFunction<String[]>() {

            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });

        //等价于上面
        apples.stream().map(Apple::getColor).toArray(value -> new String[value]);
        //最终简化版本   因为value是参数，  new String[]   的括号里也需要一个value参数作为数组的初始长度
        apples.stream().map(Apple::getColor).toArray(String[] :: new);
    }


    /**
     *  转换set
     */
    @Test
    public void toSet() {
        //apples里的重量 放到set里
        Set<Integer> collect = apples.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toSet());
        //apples里的重量 放到treeSet里   具体子类     Collectors.  没有提供
        TreeSet<Integer> treeSet = apples.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
    }

    /**
     *  转换list
     */
    @Test
    public void toList() {
        //apples里的重量 放到List里      默认返回的是java.util.ArrayList
        List<Integer> list = apples.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toList());
        System.out.println(list.getClass());

        //apples里的重量 放到LinkedList
        LinkedList<Integer> linkedList = apples.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedList.getClass());
    }

    /**
     * 过滤
     */
    @Test
    public void filterTest() {
        //找出颜色是绿色,重量小于9的苹果
        apples.add(null);
        List<Apple> list = apples.stream()
                .filter(Objects::nonNull)
                .filter(apple -> "绿色".equals(apple.getColor()))
                .filter(apple -> apple.getWeight() < 9)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void transform() {
//        List<Dog> dogs = Lists.transform(apples, new Function<Apple, Dog>() {
//            @Nullable
//            @Override
//            public Dog apply(@Nullable Apple input) {
//                return new Dog(input.getId(),input.getColor(),input.getWeight());
//            }
//        });
//        System.out.println(apples);
////        System.out.println(dogs);
//        System.out.println("------------");
//        System.out.println("接下来清空apples数据");
//        //接下来对apples做出修改
////        apples.clear();
////        apples.add(new Apple());
////        dogs.add(new Dog());
//        Dog dog = dogs.get(0);
//        System.out.println("apples:"+ apples);
//        System.out.println("dogs:"+ dogs);
    }

    @Test
    public void biFunction() {
        Apple apple = new Apple(1L, "白色", 1);
        Dog dog = new Dog(1L,"小白",2);
        //要求 将apple和dog共同组成 一个cat对象，  id相加，weight相加, 名字和颜色直接使用

        this.builder(apple,dog, (apple1,dog1) -> {
            Cat cat = new Cat();
            cat.setId(apple.getId() + dog.getId());
            cat.setName(dog.getName());
            cat.setColor(apple.getColor());
            cat.setWeight(apple.getWeight() + dog.getWeight());
            return cat;
        });
    }

    private <T,U,R> R builder(T t,U u, BiFunction<T,U,R> biFunction) {
        return biFunction.apply(t,u);
    }


    /**
     * 比较器复合
     */
    @Test
    public void testThen() {
        //Comparator接口居然有一个静态方法   reversed() 重量 逆序排序 从大到小   如果有重复的再根据id从小到大排
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getId));
        System.out.println(apples);
    }

    /**
     * 谓词复合
     */
    @Test
    public void predicateThen() {
        //选出绿色的苹果
        List<Apple> collect = apples.stream().filter(apple -> "绿色".equals(apple.getColor())).collect(Collectors.toList());
        System.out.println(collect);

        //选出非绿色的苹果
        Predicate<Apple> predicate = apple -> "绿色".equals(apple.getColor());
        List<Apple> collect1 = apples.stream().filter(predicate.negate()).collect(Collectors.toList());
        System.out.println(collect1);
    }


}
