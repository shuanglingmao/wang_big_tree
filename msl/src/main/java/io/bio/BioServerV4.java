package io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: bio服务端V4版本    可以处理多次请求   并行处理  线程池
 *
 * @author shuangling.mao
 * @date 2019/5/31 9:34
 */
public class BioServerV4 {

    /**
     * 收到客户端发来的字符转 转成大写发回去
     * TODO 弊端：   虽然可以并行处理多次请求  但是频繁的创建销毁线程 会消耗资源
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        //电话绑定分机号
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("服务器准备就绪~");
        while (true) {
            //开始等人打电话   阻塞状态    有客户端连接 结束阻塞状态 返回socket
            Socket s = ss.accept();
            String uuid = UUID.randomUUID().toString().replace("-","");
            System.out.println("客户端"+s.getInetAddress().getHostAddress()+uuid+"已连接，开始干活!");

            executorService.submit(() -> {
                try {
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
                    System.out.println("服务器处理此次请求"+uuid+"完毕");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
