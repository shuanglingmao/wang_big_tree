package io.bio;


import java.io.*;
import java.net.Socket;

/**
 * Description: bio的客户端  模拟多用个客户端链接服务器
 *
 * @author shuangling.mao
 * @date 2019/5/31 9:25
 */
public class BioClientV2 {

    /**
     * 给客户端发30个hello  打印服务器返回的30个Hello
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Socket s = null;
                try {
                    s = new Socket("localhost",9999);
                    InputStream inputStream = s.getInputStream();
                    OutputStream outputStream = s.getOutputStream();

                    PrintWriter out = new PrintWriter(outputStream);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

                    for (int j = 1; j <= 30; j++) {
                        out.println("hello"+j);
                        out.flush();
                        //打印服务器的回应
                        System.out.println("服务器应答："+in.readLine());
                        Thread.sleep(200);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
