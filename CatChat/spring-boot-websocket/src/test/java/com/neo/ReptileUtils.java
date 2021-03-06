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

    public static void main(String[] args) {
        reptile("https://pro.jd.com/mall/active/2enL5dxxLXG5SKmdLEEmsL7HrRrT/index.html?utm_source=kong&utm_medium=zssc&utm_campaign=t_1000027280_100756&utm_term=537708b4-3f80-4f60-8b86-109fb61084b2-p_1999-pr_1664-at_100756&jd_pop=537708b4-3f80-4f60-8b86-109fb61084b2&abt=0","E:\\image\\");
    }


    public static void reptile(String strUrl,String file){
        try {
            System.out.println("測試爬取"+strUrl);
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
            //按行读取并打印
            String line=null;
            while((line=br.readLine())!=null){
                if (line.contains(".png") || line.contains(".jpg") || line.contains(".gif")){
                    while(true){
                        int src = line.indexOf("src=");
                        int jpg = line.indexOf(".jpg");
                        int png = line.indexOf(".png");
                        int gif = line.indexOf(".gif");
                        if (jpg>png || jpg == -1){
                            jpg = png;
                        }
                        if (jpg>gif || jpg == -1){
                            jpg = gif;
                        }
                        if (src > jpg){
                            line = line.substring(jpg+5,line.length());
                            continue;
                        }
                        if (src == -1 || jpg == -1) {
                            break;
                        }
                        reptileImg("http://"+line.substring(src,jpg+4).split("//")[1],file+ System.currentTimeMillis()+line.substring(jpg,jpg+4));
                        line = line.substring(jpg+5,line.length());
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reptileImg(String strUrl,String file) throws IOException {
        System.out.println("測試下载"+strUrl);
        URL url = new URL(strUrl);
        //通过url建立与网页的连接
        URLConnection conn=url.openConnection();
        //通过链接取得网页返回的数据
        InputStream is=conn.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bs = new byte[2000];
        while (is.read(bs) != -1){
            fileOutputStream.write(bs);
        }
        fileOutputStream.flush();
        is.close();
        fileOutputStream.close();
    }


}
