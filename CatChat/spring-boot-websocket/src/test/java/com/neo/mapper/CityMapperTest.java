package com.neo.mapper;

import com.neo.model.City;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Description: 测试
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CityMapperTest {
    @Autowired
    private CityMapper cityMapper;
    @Test
    public void insert() throws Exception {
        final List<City> cityList = Lists.list(new City("CN20190610001","北京","北京",1),
                new City("CN20190610002","北京","北京",1),
                new City("CN20190610003","张家口","河北",1),
                new City("CN20190610004","承德","河北",1),
                new City("CN20190610005","郑州","河南",1),
                new City("CN20190610006","商丘","河南",1),
                new City("CN20190610007","洛阳","河南",1),
                new City("CN20190610008","周口","河南",1),
                new City("CN20190610009","平顶山","河南",1),
                new City("CN20190610010","成都","四川",1),
                new City("CN20190610011","攀枝花","四川",1),
                new City("CN20190610012","合肥","安徽",1),
                new City("CN20190610013","济南","山东",1),
                new City("CN20190610014","太原","山西",1),
                new City("CN20190610015","临汾","山西",1)
                );
        cityList.forEach(city -> cityMapper.insert(city));
    }

    @Test
    public void getCityById() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        System.out.println(cityMapper.getAll().toString());
    }

    @Test
    public void delete() throws Exception {
    }

}