package com.msl.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/4/26 10:27
 */
public class Import {
    private static final String SQL_PACKAGE_URI = "D:\\git_repository\\CARSRMS\\src\\main\\resources\\com\\zuche";
    private static List<File> fileList = new ArrayList<File>();

    private static Set<String> fielNames = new HashSet<String>();


    public Import() throws IOException {
    }

    public static void main(String[] args) throws Exception {

        new Import().doThis();
//        System.out.println(JSONObject.toJSONString(fileList));
        System.out.println(fileList.size());
        System.out.println(fielNames.size());
        System.out.println(fielNames.toString());
    }
    private void doThis() throws Exception{

        //读到所有的sql文件
        getResources(new File(SQL_PACKAGE_URI));
        //挨个一行一行读取sql 符合要求写入E:/sql.txt
        FileWriter fw=new FileWriter(new File("E:/sql.txt"));
        BufferedWriter  bw=new BufferedWriter(fw);
        for (File file : fileList) {
            read(file,bw);
        }
        bw.close();
        fw.close();
    }

    private void getResources(File file) {
        try {
            if (file.exists()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    if (!file1.isDirectory()) {
                        if (file1.getName().endsWith("_sql.xml")) {
                            fileList.add(file1);
                        }
                    } else {
                        getResources(file1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常");
        }
    }

    private void read(File sqlxml,BufferedWriter bw) throws IOException {
        String[] word = {"from","FROM","join","JOIN","delete","DELETE","update","UPDATE","insert","INSERT"};
        FileReader fr=new FileReader(sqlxml);
        BufferedReader br=new BufferedReader(fr);
        String line="";
        while ((line=br.readLine())!=null) {
            if (!StringUtils.isEmpty(line) && !line.trim().startsWith("<")) {
                for (String s : word) {
                    if (line.trim().indexOf(s)!= -1) {
                        if (line.trim().endsWith(s)) {
                            fielNames.add(sqlxml.getName());
                        }
                        bw.write(line+"\t\n");
                    }
                }
            }
        }
        br.close();
        fr.close();
    }
}
