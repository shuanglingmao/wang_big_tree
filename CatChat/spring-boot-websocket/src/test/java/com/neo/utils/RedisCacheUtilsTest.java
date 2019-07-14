package com.neo.utils;

import com.neo.mapper.CityMapper;
import com.neo.model.City;
import com.neo.model.constant.RedisConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 测试
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RedisCacheUtilsTest {

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private CityMapper cityMapper;

    @Test
    public void get() throws Exception {
        System.out.println(get(2));

    }

    private City get(Integer cityId) {
        return redisCacheUtils.get(RedisConstant.CITY_KEY+cityId, () -> cityMapper.getCityById(cityId));
    }

}