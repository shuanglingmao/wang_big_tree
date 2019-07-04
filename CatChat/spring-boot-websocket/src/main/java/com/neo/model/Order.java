package com.neo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
@Data
@ToString
@Alias("order")
@NoArgsConstructor
public class Order implements Serializable{
    private Integer id;
    private String orderName;
    private Integer quantity;
    private String createTime;

    public Order(String orderName, Integer quantity,String createTime) {
        this.orderName = orderName;
        this.quantity = quantity;
        this.createTime = createTime;
    }

    public Order(Integer id, String orderName, Integer quantity, String createTime) {
        this.id = id;
        this.orderName = orderName;
        this.quantity = quantity;
        this.createTime = createTime;
    }
}
