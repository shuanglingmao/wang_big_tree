package com.neo.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.neo.model.City;
import com.neo.utils.TypeRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/2 0002
 * @Author 毛双领 <shuangling.mao>
 */
public class DoNothing {
    public static void main(String[] args) {

        List<Map<String, Integer>> list1 = typeRef(new TypeRef<List<Map<String, Integer>>>());

//        City city = City.builder()
//                .cityId(1)
//                .cityName("北京")
//                .cityNo("bj000001")
//                .flag(1)
//                .provinceName("北京")
//                .build();

//        new ArrayList<City>().forEach(city -> {
//            City.builder().cityId(city.getCityId()).provinceName(city.getProvinceName()).flag(city.getFlag()).build();
//        });

    }

    private static <T> T typeRef(TypeRef<T> tTypeRef) {

        T result = null;

        return result;
    }


    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        list.remove(2);
        System.out.println(list);
    }

    @Test
    public void test2() {
        IHello hello = new HelleImpl();
        hello.sayHello();
    }

    @Test
    public void test3() {
        equalsClass(new IHello() {
            @Override
            public void sayHello() {
                System.out.println("aaaaaaaaaa");
            }
        }.getClass());

    }

    private void equalsClass(Class<?> clazz) {
        System.out.println(IHello.class.isAssignableFrom(clazz));
    }

    @Test
    public void test4() {
        List<IHello> ihellos = Lists.newArrayList((IHello) () -> {
            System.out.println("1");
        },(IHello) () -> {
            System.out.println("2");
        });
        System.out.println(ihellos.size());
        ihellos.forEach(hello -> hello.sayHello());
    }

    @Test
    public void test5() {
        String reg = "(\\d+)-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("2019-08-05");
        System.out.println(matcher.matches());
    }

}

interface IHello {
    void sayHello();
}

abstract class BaseHello implements IHello{

    @Override
    public void sayHello() {
        System.out.println(getName()+"\tHello Word");
    }

    abstract String getName();
}

class HelleImpl extends BaseHello {
    @Override
    String getName() {
        return "赵二狗!";
    }
}







