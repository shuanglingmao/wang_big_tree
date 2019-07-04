package com.neo.service.impl;

import com.neo.mapper.InventoryMapper;
import com.neo.mapper.OrderMapper;
import com.neo.model.Inventory;
import com.neo.model.Order;
import com.neo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void sava(Order order) {
        orderMapper.insert(order);
        Inventory inventory = new Inventory();
        inventory.setOrderName(order.getOrderName());
        inventory.setTotalAmount(order.getQuantity());
        inventoryMapper.update(inventory);
//        try { int num = 5/0; } catch (Exception e) { e.printStackTrace(); }
        int num = 5/0;

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
            @Override
            public void afterCommit() {
                sendMq();
            }
        });
    }

    private void sendMq() {
        System.out.println("***************发送Mq成功*******************");
    }
}
