package com.myself.lambda;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LombdaDemo {
    @Test
    public void test1() {
        I1 i1 = new I1() {
            @Override
            public void test(PiDai piDai) {
                piDai.method();
            }
        };

        I1 i2 = PiDai->PiDai.method();
        I1 i3 = PiDai::method;
        i1.test(new PiDai());
        i2.test(new PiDai());
        i3.test(new PiDai());
    }
    @Test
    public void test2() {
        I2 i2 = PiDai::method;
        i2.test(new PiDai(),"s");
    }

    @Test
    public void test4() {
        I3 i3 = PiDai::method;
        i3.test(new PiDai(),"a","b");
        i3(new PiDai(),"a","b", PiDai::method);
        i3(new PiDai(),"a","b",((piDai, a, b) -> piDai.method(a,b)));

    }


    @Test
    public void test3() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

        filter(list, integer -> integer % 2 == 0);
        System.out.println(JSONObject.toJSONString(list));

        Set<String> set = Sets.newHashSet("a","b","c");
        filter(set,s->s.equals("b"));

        System.out.println(JSONObject.toJSONString(set));

    }




    interface I1 {
        void test(PiDai piDai);
    }
    interface I2 {
        void test(PiDai piDai, String s);
    }
    interface I3 {
        void test(PiDai piDai, String a, String b);
    }
    public void i3(PiDai piDai,String a,String b,I3 i3) {
        i3.test(piDai,a,b);
    }
    class PiDai {
        public void method() {
            System.out.println("method()");
        }
        public void method(String s) {
            System.out.println("method(String s)");
        }
        public void method(String s,String b) {
            System.out.println("method(String s,String b)");
        }
    }

    interface Filter<T>{
        boolean filter(T t);
    }
    public<T> void filter(Collection<T> collection, Filter<T> filter) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (filter.filter(iterator.next())) {
                iterator.remove();
            }
        }
    }

}
