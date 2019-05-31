package com.msl.event;

import com.msl.event.event.PaymentStatusUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
     * Description: 模拟变更支付状态
     *
     * @author shuangling.mao
     * @date 2019/5/10 10:13
     */
    @Service
    public class PaymentService {

        @Autowired
        private ApplicationContext context;

        public PaymentInfo pay(Integer id) {
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setId(id);
            paymentInfo.setStatus(0);
            paymentInfo.setMsg("支付成功");
            //新建支付状态变更事件
            PaymentStatusUpdateEvent paymentStatusUpdateEvent = new PaymentStatusUpdateEvent(paymentInfo);
            //推送事件
            context.publishEvent(paymentStatusUpdateEvent);

            return paymentInfo;
        }

}
