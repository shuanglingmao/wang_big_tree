package com.msl.controller;

import com.msl.event.PaymentInfo;
import com.msl.event.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/9 14:30
 */
@RestController
public class TestRestController {
    @Autowired
    private PaymentService paymentService;
    @RequestMapping("/string")
    public String printString(String s) {
        return s;
    }

    /**
     * 返回值如果是对象类型  会自动处理成JSON格式
     * @return
     */
    @RequestMapping("/string1")
    public Object printObject() {
        return new HashMap(){{
            put("a","啊啊啊");
            put("b","不不不");
            put("c","擦擦擦");
            put("d","的订单");
            put("e","嗯嗯嗯");
        }};
    }

    @RequestMapping("/event")
    public Object event(Integer id) {
        return paymentService.pay(id);
    }
}
