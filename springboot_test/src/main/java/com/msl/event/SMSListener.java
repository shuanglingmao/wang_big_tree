package com.msl.event;

import com.msl.event.event.PaymentStatusUpdateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: 短信监听器   支付状态改变通知库存   无序监听器
 *
 * @author shuangling.mao
 * @date 2019/5/10 9:54
 */
@Component
public class SMSListener implements ApplicationListener<PaymentStatusUpdateEvent> {

    @Override
    public void onApplicationEvent(PaymentStatusUpdateEvent paymentStatusUpdateEvent) {
        System.out.println(String.format("短信服务收到支付状态改变信息:%s,当前线程：%s",paymentStatusUpdateEvent,Thread.currentThread().getName()));
    }
}
