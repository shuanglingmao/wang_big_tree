package com.neo.spi;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/8/2 0002
 * @Author 毛双领
 */
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
