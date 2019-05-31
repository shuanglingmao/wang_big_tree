package io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: bio服务端V2版本    可以处理多次请求    顺序处理
 *
 * @author shuangling.mao
 * @date 2019/5/31 9:34
 */
public class BioServerV2 {

    /**
     * 收到客户端发来的字符转 转成大写发回去
     * TODO 弊端：   虽然一次请求处理完毕  可以接着处理下一次请求  但是！ 第一个请求如果没处理完  第二个请求就无法被处理
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //电话绑定分机号
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("服务器准备就绪~");
        while (true) {
            //开始等人打电话   阻塞状态    有客户端连接 结束阻塞状态 返回socket
            Socket s = ss.accept();
            System.out.println("客户端"+s.getInetAddress().getHostAddress()+"已连接，开始干活!");

            OutputStream outputStream = s.getOutputStream();
            InputStream inputStream = s.getInputStream();
            PrintWriter out = new PrintWriter(outputStream);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            for (int i = 1; i <= 30; i++) {
                //获得客户端发过来的数据
                final String receive = in.readLine();
                //转成大写发回去
                out.println(receive.toUpperCase());
                out.flush();
            }
            System.out.println("服务器处理此次请求完毕");
            s.close();
        }

    }
}
