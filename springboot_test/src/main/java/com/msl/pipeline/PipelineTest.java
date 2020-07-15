package com.msl.pipeline;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author msl on 2020/5/27.
 */
public class PipelineTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(100, 99, 66, 87, 43, 35, 7, 6, 5, 4, 3, 9, 1, 2);

        Pipeline<List<Integer>, String> pipeline = new Pipeline<>(new FilterEvenHandler())
                .addHandler(new SortEvenHandler())
                .addHandler(new ConvertToString());
        System.out.println(pipeline.execute(list));



        System.out.println(new Pipeline<>((Handler<Integer, Integer>) input -> input * 2)
                .addHandler(input -> input * 3)
                .addHandler(input -> input * 100)
                .addHandler(input -> input.toString())
                .addHandler(input -> Lists.newArrayList(input))
                .execute(2));

    }
}







/**
 * 过滤偶数 handler
 */
class FilterEvenHandler implements Handler<List<Integer>, List<Integer>> {
    @Override
    public List<Integer> process(List<Integer> input) {
        return input.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
    }
}


/**
 * 排序 handler
 */
class SortEvenHandler implements Handler<List<Integer>, List<Integer>> {
    @Override
    public List<Integer> process(List<Integer> input) {
        List<Integer> collect = input.stream().sorted().collect(Collectors.toList());
//        System.out.println("soerted:"+ collect);
        return collect;
    }
}


/**
 * list转String
 */
class ConvertToString implements Handler<List<Integer>, String> {
    @Override
    public String process(List<Integer> input) {
        return "aaa" + JSONObject.toJSONString(input);
    }
}



class AHandler implements Handler<List<Integer>, Void> {
    @Override
    public Void process(List<Integer> input) {
        System.out.println("A处理没问题");
        return null;
    }
}


class BHandler implements Handler<List<Integer>, Void> {
    @Override
    public Void process(List<Integer> input) {
        System.out.println("B处理没问题");
        return null;
    }
}

class CHandler implements Handler<List<Integer>, Void> {
    @Override
    public Void process(List<Integer> input) {
        System.out.println("C处理没问题");
        return null;
    }
}
