package com.neo.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 城市实体类
 *
 * @author shuangling.mao
 * @date 2019/6/10 15:46
 */
@Data
@ToString
public class City implements Serializable {
    private Integer cityId;
    private String cityNo;
    private String cityName;
    private String provinceName;
    private Integer flag;

    public City() {
    }

    public City(String cityNo, String cityName, String provinceName, Integer flag) {
        this.cityNo = cityNo;
        this.cityName = cityName;
        this.provinceName = provinceName;
        this.flag = flag;
    }
}
