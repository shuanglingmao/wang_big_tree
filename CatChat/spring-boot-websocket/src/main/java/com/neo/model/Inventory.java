package com.neo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
@Data
@ToString
@Alias("inventory")
@NoArgsConstructor
public class Inventory {
    private Integer id;
    private String orderName;
    private Integer totalAmount;

    public Inventory(String orderName,Integer totalAmount) {
        this.orderName = orderName;
        this.totalAmount = totalAmount;
    }
}
