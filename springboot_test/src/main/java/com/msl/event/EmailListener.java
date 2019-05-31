package com.msl.event;

import com.msl.event.event.PaymentStatusUpdateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: 邮件服务监听器   支付状态改变通知邮件服务发邮件    无序监听器
 *
 * @author shuangling.mao
 * @date 2019/5/10 9:54
 */
@Component
public class EmailListener implements ApplicationListener<PaymentStatusUpdateEvent> {

    @Override
    public void onApplicationEvent(PaymentStatusUpdateEvent paymentStatusUpdateEvent) {
        System.out.println(String.format("邮件服务收到支付状态改变信息:%s,当前线程：%s",paymentStatusUpdateEvent,Thread.currentThread().getName()));
    }
}
