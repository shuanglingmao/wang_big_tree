package com.neo.nio;

import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/1 0001
 * @Author 毛双领 <shuangling.mao>
 */
public class NioServerTest {
    public static void main(String[] args) {
        NioServer nioServer = new NioServer(9999) {
            @Override
            protected void read(SocketChannel socketChannel) {

            }

            @Override
            protected void write(SocketChannel socketChannel) {

            }
        };
    }
}
