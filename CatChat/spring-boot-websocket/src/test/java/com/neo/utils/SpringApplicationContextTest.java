package com.neo.utils;

import com.neo.service.OrderService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringApplicationContextTest {
    @Autowired
    private OrderService orderService1;


    @Test
    public void getBean() throws Exception {
        Object orderService = SpringApplicationContext.getBean("orderService");
        System.out.println(orderService);
        System.out.println(orderService1);
        System.out.println(orderService == orderService1);

        System.out.println(SpringApplicationContext.getBean("testController"));
    }

}