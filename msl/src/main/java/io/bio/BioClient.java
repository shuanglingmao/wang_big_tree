package io.bio;


import java.io.*;
import java.net.Socket;

/**
 * Description: bio的客户端
 *
 * @author shuangling.mao
 * @date 2019/5/31 9:25
 */
public class BioClient {

    /**
     * 给客户端发30个hello  打印服务器返回的30个Hello
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s = new Socket("localhost",9999);

        InputStream inputStream = s.getInputStream();
        OutputStream outputStream = s.getOutputStream();

        PrintWriter out = new PrintWriter(outputStream);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        for (int i = 1; i <= 30; i++) {
            out.println("hello"+i);
            out.flush();
            //打印服务器的回应
            System.out.println("服务器应答："+in.readLine());
            Thread.sleep(200);
        }

        s.close();
    }
}
