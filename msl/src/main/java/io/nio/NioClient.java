package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * $Description: NIO客户端编程
 *
 * @author shuangling.mao
 * @date 2019-06-01 16:45
 */

public class NioClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        //注册 channel   服务端准备好可以被连接 就触发这个事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        //发起连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9000));
        //单开一个线程 向服务器写30个hello
        new Thread(() -> {
            try {
                //链接建立好才开始写
                while (!socketChannel.isConnected()) {}
                for (int i = 1; i <= 50; i++) {
                    String str = "hello"+i;
                    ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
                    socketChannel.write(byteBuffer);
                    Thread.sleep(200);
                }
                socketChannel.close();
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO  注意如果这个时候链接没建立好  发送的数据数据就丢了 所以在for循环之前加判断
        }).start();


        while (true) {
            selector.select();
            //如果selector已经被关掉了  退出死循环
            if (!selector.isOpen()) break;
            //注意selector.selectedKeys()   不要写成 selector.keys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                //服务器通知你可以连接了  通过connect连接服务器 服务器如果接受到请求 回法规来一个确认激活这个时间
                if (selectionKey.isConnectable()) {
                    //如果没有链接完
                    if (!socketChannel.isConnected()) socketChannel.finishConnect();
                        //则完成连接 如果这个方法返回则说明彻底连接好了
                    //当有数据可以读 通知
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                //服务器发回数据了  可以读了
                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    //读到buffer里
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    String str = Charset.defaultCharset().decode(byteBuffer).toString();
                    System.out.println(str);
                }

                //往服务器写怎么办呢？？？？？
            }

        }

    }
}
