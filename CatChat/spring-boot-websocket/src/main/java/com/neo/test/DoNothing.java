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
import java.util.List;
import java.util.Map;

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

}

