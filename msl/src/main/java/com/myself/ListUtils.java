package com.myself;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static <E> void filter(List<E> list, MyFunction<E> myFunction) {
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (myFunction.filter(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static <E> void filterV2(List<E> list, MyFunction<E> myFunction) {
        List<E> list1 = new ArrayList<E>();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (E e : list) {
            if (!myFunction.filter(e)) {
                list1.add(e);
            }
        }
        list.clear();
        list.addAll(list1);

    }

    interface MyFunction<E> {
        Boolean filter(E e);
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(10);
        filter(integers, integer -> integer % 2 == 0);
        System.out.println(JSONObject.toJSONString(integers));

        /**
         * Arrays.asList  返回java.util.Arrays$ArrayList
         * Arrays$ArrayList   是Arrays中一个内部类 没有add remove clear操作 调用了会报错UnsupportedOperationException
         */
//        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        System.out.println(integers.getClass());
//        System.out.println(integers1.getClass());
//        filterV2(integers1, new MyFunction<Integer>() {
//            @Override
//            public Boolean filter(Integer integer) {
//                return integer % 2 == 0;
//            }
//        });
//        System.out.println(JSONObject.toJSONString(integers1));

        //JDK 1.8自带过滤

        List<Integer> integers2 = new ArrayList<Integer>();
        integers2.add(1);
        integers2.add(2);
        integers2.add(3);
        integers2.add(4);
        integers2.add(5);
        integers2.add(6);
        integers2.add(7);
        integers2.add(8);
        integers2.add(9);
        integers2.add(10);
        List<Integer> collect = integers2.stream()
                .filter(integer -> integer > 2)
                .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect));

    }
}
