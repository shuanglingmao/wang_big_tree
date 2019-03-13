package com.gupaoedu.vip.pattern.singleton.seriable;

import java.io.Serializable;

//反序列化时导致单例破坏   序列化前后对象不一致
public class Seriable implements Serializable {

    /**
     * 序列化就是吧内存中的状态通过转成字节码的形式
     * 从而转换成一个IO流 写入到其他地方（可以是磁盘、网络IO）
     * 内存中状态给用就保存下来了
     *
     *
     * 反序列化  将已经持久化的字节码内容 转换为IO流
     * 通过IO流的读取  今儿将读取的内容转换为 Java对象
     * 在转换过程中 会重新创建对象  new Object
     */

    private Seriable(){}

    private final static Seriable instance = new Seriable();

    public static Seriable getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;
    }


}
