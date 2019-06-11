package com.neo.mapper;

import com.neo.model.City;

import java.util.List;

/**
 * Description: cityMapper
 *
 * @author shuangling.mao
 * @date 2019/6/10 16:01
 */
public interface CityMapper {
    void insert(City city);
    City getCityById(Integer cityId);
    List<City> getAll();
    void delete(Integer cityId);
}
