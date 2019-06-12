package com.neo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * description:示例爬虫
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/12
 */
public class CatChatTest {
    public static void main(String args[]){
         String strurl="http://v.baidu.com/tv/28898.html";
         try {
             URL url=new URL(strurl);
             //通过url建立与网页的连接
             URLConnection conn=url.openConnection();
             //通过链接取得网页返回的数据
             InputStream is=conn.getInputStream();
             System.out.println(conn.getContentEncoding());
             //一般按行读取网页数据，并进行内容分析
             //因此用BufferedReader和InputStreamReader把字节流转化为字符流的缓冲流
             //进行转换时，需要处理编码格式问题
             BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
             //按行读取并打印
             String line=null;
             while((line=br.readLine())!=null){
                 System.out.println(line);
             }
             br.close();
         } catch (Exception e) {
             e.printStackTrace();
         }

    }
}
