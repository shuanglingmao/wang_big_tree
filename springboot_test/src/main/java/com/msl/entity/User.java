package com.msl.entity;

import com.msl.serializer.Serializer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/18 11:16
 */
@Data
public class User implements Serializable{
    private String name;
    private Integer age;
    private String phoneNum;
}
