package com.msl.event.event;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * Description: 支付状态更新事件
 *
 * @author shuangling.mao
 * @date 2019/5/10 9:51
 */
@ToString
public class PaymentStatusUpdateEvent extends ApplicationEvent{

    public PaymentStatusUpdateEvent(Object source) {
        super(source);
    }
}
