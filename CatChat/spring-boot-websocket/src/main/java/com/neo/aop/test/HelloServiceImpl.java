package com.neo.aop.test;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/6/18 11:17
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello" + name);
    }
}
