package com.neo;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * description:爬取图片工具
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/12
 */
public class ReptileUtils {

    public static void reptile(String strUrl,Boolean isPicture,String file){
        try {
            URL url=new URL(strUrl);
            //通过url建立与网页的连接
            URLConnection conn=url.openConnection();
            //通过链接取得网页返回的数据
            InputStream is=conn.getInputStream();
            System.out.println(conn.getContentEncoding());
            //一般按行读取网页数据，并进行内容分析
            //因此用BufferedReader和InputStreamReader把字节流转化为字符流的缓冲流
            //进行转换时，需要处理编码格式问题
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            //按行读取并打印
            String line=null;
            while((line=br.readLine())!=null){
                if (isPicture){
                    outputStreamWriter.write(line);
                }else{
                    if (line.contains(".png") || line.contains(".jpg")){
                        reptile("",true,file);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
