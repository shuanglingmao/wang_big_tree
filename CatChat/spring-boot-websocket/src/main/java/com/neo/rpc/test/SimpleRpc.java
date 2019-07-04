package com.neo.rpc.test;

import com.neo.model.Order;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public class SimpleRpc {

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        //本地调用
        Order order = orderService.getOrderById(1);
        System.out.println(order);
    }
}





