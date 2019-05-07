package com.msl.controller;

import com.msl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: demo
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

//    @PostConstruct
    public void test() {
        System.out.println(111);
        testService.pay(1D);
    }


    /**
     * 用环绕增强返回一句  “叫呆姐干啥”
     * @return
     */
    @RequestMapping("/wdd")
    @ResponseBody
    public String wdd(String name) {
        return name;
    }

    public static void main(String[] args) {
        Integer a = null;
        Integer b = 3;
        System.out.println(b.equals(a));
    }

    @RequestMapping("/showAll1" )
    public String showAll(String name) {
        System.out.println(name);
        return "showAll";
    }
}
