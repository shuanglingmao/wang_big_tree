package com.msl.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/18 11:16
 */
@Data
public class User {
    private String name;
    private Integer age;
    private String phoneNum;
}
