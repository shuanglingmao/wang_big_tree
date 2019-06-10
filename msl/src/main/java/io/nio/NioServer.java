package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-06-01 15:34
 */

public class NioServer {
    public static void main(String[] args) throws Exception{
        //开启一个通道   用来接收客户端请求
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //监听端口
        serverSocketChannel.bind(new InetSocketAddress(9000));
        //配置channel配置 非阻塞   你希望他阻塞吗？
        serverSocketChannel.configureBlocking(false);

        //开启选择器   channel就像病人  selector就像护士   他们有一个注册的过程
        Selector selector = Selector.open();

        //注册那个选择器  对哪类事件感兴趣  也就是说这个channel发生哪类事件的时候通知这个selector
        //OP_ACCEPT 当我接受了一个客户端连接的时候会通知selector
        // 注册感兴趣的时间：  有客户端连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //TODO 这个方法会阻塞  实际相当于护士在那等着，要是没人喊他，他就等她
            //有一个channnel 喊他 就会离开阻塞状态 就会进入38行
            selector.select();
            //事件通过 SelectionKey传递       拿到所有事件发生的SelectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历set  要用迭代器遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //第一件事就是吧他删掉  不删的话 下次还会遍历他
                //呼叫器响了  护士先按灯  再去处理病人
                iterator.remove();
                //看看她到底发生了啥事   是客户端连接吗？
                if (selectionKey.isAcceptable()) {
                    //如果是一定是  serverSocketChannel   只有他才注册这个事件
                    //跟  accept() 是一样的
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置非阻塞
                    socketChannel.configureBlocking(false);
                    //把她也注册到selector上  感兴趣事件：准备好读取数据
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                //当有客户端发送数据时，服务器准备读数据
                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    //找到这个Key背后的channel是谁
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //数据读不完跑不到下一行   但不会阻塞  因为当他读的时候  一定有数据了，不然selectionKey 没法激活
                    //读完的数据都在buffer了
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();

                    //从客户端接收到的内容  ByteBffer --> String
                    String str = Charset.defaultCharset().decode(byteBuffer).toString();

                    //转成大写发回客户端
                    str = str.toUpperCase();
                    //str-->ByteBuffer
                    byteBuffer = ByteBuffer.wrap(str.getBytes());

                    //刚才是读，现在是写  双工  可读可写
                    socketChannel.write(byteBuffer);

                    //多个客户端连接  顺序处理  一个一个处理      而前面的BIO多个线程去并行处理，但是线程里会被流阻塞
                    //客户端0.2秒发送一个hello NIO不会等这0.2秒 谁发过来 给你发回去一个大写的Hello 虽然是单线程，但是众多客户端是可以同时看到大写的Hello的

                }
            }

        }

    }
}
