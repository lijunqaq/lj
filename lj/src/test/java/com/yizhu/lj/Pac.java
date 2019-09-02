package com.yizhu.lj;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java爬虫联系
 *
 * @author Lj
 * @date 2019/7/8
 */
public class Pac {

    static String url1="https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&fm=index&pos=history&word=%E7%BB%AB%E6%BF%91%E9%81%A5%E5%89%A7%E7%85%A7";
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("g:\\Users\\小负子\\Pictures\\test\\123.txt");
        try {
            URL url2 = new URL(url1);
            URLConnection con = url2.openConnection();
            BufferedReader bu = new BufferedReader(new InputStreamReader(con.getInputStream()));
            FileOutputStream fi = new FileOutputStream(file);
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(fi));

            while (bu.readLine() != null) {
                String str = bu.readLine();
                bf.write(str);
                bf.flush();
            }
            bf.flush();
            bu.close();
            bf.close();
        } catch (Exception e) {
            // TODO: handle exception

        }

        String patter="https://ss\\d+\\.bdstatic\\.com\\S+\\.jpg";
        BufferedReader bu1=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Pattern p = Pattern.compile(patter);
        int i=0;
        String str1=null;
        StringBuilder str2=new StringBuilder();
        HashSet<String> set=new HashSet<String>();
        try {
            while((str1=bu1.readLine())!=null) {
                str2.append(str1);

            }
            Matcher m = p.matcher(str2);
            while(m.find()) {
                m.start();
                set.add(m.group());
                i++;
            }
            bu1.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
