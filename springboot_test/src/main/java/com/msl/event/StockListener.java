package com.msl.event;

import com.msl.event.event.PaymentStatusUpdateEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: 库存监听器   支付状态改变通知库存        有序监听器
 *
 * @author shuangling.mao
 * @date 2019/5/10 9:54
 */
@Component
public class StockListener implements SmartApplicationListener {



    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == PaymentStatusUpdateEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> source) {
        return source == PaymentInfo.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(String.format("库存服务收到支付状态改变信息:%s,当前线程：%s",applicationEvent,Thread.currentThread().getName()));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
