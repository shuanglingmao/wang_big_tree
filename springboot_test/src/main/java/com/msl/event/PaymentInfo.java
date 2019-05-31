package com.msl.event;

import lombok.Data;
import lombok.ToString;

/**
 * Description: 支付实体类
 *
 * @author shuangling.mao
 * @date 2019/5/10 9:50
 */
@Data
@ToString
public class PaymentInfo {
    private Integer id;
    private Integer status;
    private String msg;
}
