package com.neo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Inventory;
import com.neo.model.Order;
import com.neo.service.OrderService;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private OrderService orderService;

    @Test
    public void insert() throws Exception {
//        orderMapper.insert(new Order("电视机",1));
//        orderMapper.insert(new Order("冰箱",1));
//        orderMapper.insert(new Order("洗衣机",1));
        inventoryMapper.insert(new Inventory("电视机",99));
        inventoryMapper.insert(new Inventory("冰箱",99));
        inventoryMapper.insert(new Inventory("洗衣机",99));

    }

    @Test
    public void getAll() throws Exception {
        System.out.println(JSONObject.toJSONString(orderMapper.getAll()));
    }

    @Test
    public void getOne() throws Exception {
        System.out.println(orderMapper.getOne(1));
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void svaeOrder() {

//        orderService.sava(new Order("冰箱",10));
    }

    @Test
    public void insert1() throws Exception {
        orderMapper.insert(new Order("电视机",1,"1997-06-00"));



    }

}