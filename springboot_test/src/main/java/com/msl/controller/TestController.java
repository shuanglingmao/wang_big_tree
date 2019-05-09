package com.msl.controller;

import com.msl.service.MyTestService;
import com.msl.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;

/**
 * Description: demo
 *
 * @author shuangling.mao
 * @date 2019/3/15 9:47
 */
@Controller
public class TestController{
    @Autowired
    private MyTestService myTestService;
    @Autowired
    private LockService lockService;
    /**初始化发强器*/
    private static CountDownLatch cdl = new CountDownLatch(10);
    private static int num = 10;

    @RequestMapping("/kill")
    public void kill() {
//        lockService.modify("1");
        System.out.println("秒杀开始~");
        for (int i = 0; i < 10; i++) {
           new Thread(new Request("线程"+i)).start();
           cdl.countDown();
        }
    }

    public class Request implements Runnable {
        private String name;
        public Request(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invoke(name);
        }
    }


    private void invoke(String name) {

//        if (lockService.getCount("1") > 0) {
//            lockService.subCount("1");
//            System.out.println(name+"抢到优惠券");
//        } else {
//            System.out.println("抢购结束");
//        }
        if (num > 0) {
            num--;
            System.out.println(name+"抢到优惠券"+num);
        } else {
            System.out.println("优惠券已被抢光");
        }
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(String name) {
        final Double pay = myTestService.pay(5D);
        return name+"~结果是"+pay;
    }

//    @PostConstruct
    public void test() {
        System.out.println(111);
        myTestService.pay(1D);
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
