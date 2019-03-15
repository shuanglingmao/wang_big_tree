package com.msl.controller;

import com.msl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/15 9:47
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(String name) {
        final Double pay = testService.pay(5D);
        return name+"~结果是"+pay;
    }
}
