package com.neo.jdk8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 毛双领
 * @description
 * @create 2019-09-18 11:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private Long id;
    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }
}
