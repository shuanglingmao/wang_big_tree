package io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: bio服务端
 *
 * @author shuangling.mao
 * @date 2019/5/31 9:34
 */
public class BioServer {

    /**
     * 收到客户端发来的字符转 转成大写发回去
     * TODO 弊端：   处理一次请求后服务器断开  无法继续处理下一次请求  达不到7x24高可用
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //电话绑定分机号
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("服务器准备就绪~");
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
        ss.close();

    }
}
