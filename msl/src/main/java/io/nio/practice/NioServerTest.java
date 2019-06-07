package io.nio.practice;

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
 * @date 2019-06-01 17:40
 */

public class NioServerTest {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(9000));
        Selector selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    clientChannel.read(byteBuffer);
                    byteBuffer.flip();

                    String re = Charset.defaultCharset().decode(byteBuffer).toString();
                    byteBuffer  = ByteBuffer.wrap(re.toUpperCase().getBytes());
                    clientChannel.write(byteBuffer);
                }
            }
        }
    }
}
