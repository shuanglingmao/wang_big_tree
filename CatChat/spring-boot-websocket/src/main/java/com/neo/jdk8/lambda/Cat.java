package com.neo.jdk8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 毛双领
 * @description
 * @create 2019-09-18 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private Long id;
    private String name;
    private Integer weight;
    private String color;
}
