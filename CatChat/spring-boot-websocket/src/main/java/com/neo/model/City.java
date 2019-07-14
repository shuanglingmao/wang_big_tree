package com.neo.model;

import lombok.Builder;
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

    static class CityBuilder {
        private Integer cityId;
        private String cityNo;
        private String cityName;
        private String provinceName;
        private Integer flag;

        public City build() {
            return new City(cityNo,cityName,provinceName,flag);
        }

    }

    public static void main(String[] args) {
        System.out.println(1 == 1 ? 1 : 5.0);
    }


}
