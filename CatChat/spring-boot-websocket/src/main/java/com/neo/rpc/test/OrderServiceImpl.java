package com.neo.rpc.test;

import com.neo.model.Order;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Override
    public Order getOrderById(Integer id) {
        return new Order(1,"洗衣机",1,"2019-07-04");
    }
}
