package io.nio.practice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-01 17:55
 */

public class NioClientTest {
    public static void main(String[] args) throws Exception{
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress("localhost",9000));

        new Thread(() -> {
            try {
                while (!channel.isConnected()) {
                    //do nothing
                }
                for (int i = 1; i <= 30; i++) {
                    String send = "hello word"+i;
                    ByteBuffer byteBuffer = ByteBuffer.wrap(send.getBytes());
                    channel.write(byteBuffer);
                    Thread.sleep(200);
                }
                selector.close();
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            selector.select();
            if (!selector.isOpen())  break;
            Set<SelectionKey> keys = selector.selectedKeys();
            selector.keys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()) {
                    if (!channel.isConnected()) {
                        channel.finishConnect();
                    }
                    channel.register(selector,SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    channel.read(byteBuffer);
                    byteBuffer.flip();

                    String re = Charset.defaultCharset().decode(byteBuffer).toString();
                    System.out.println(re);
                }
            }
        }


    }
}
