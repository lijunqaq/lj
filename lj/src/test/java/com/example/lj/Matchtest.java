package com.example.lj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Matchtest {

    public Matchtest(String str1,int i) throws IOException {
        // TODO Auto-generated method stub
        File test=new File("g:\\Users\\小负子\\Pictures\\test\\绫濑遥图片");
        if(!test.exists()) {
            test.mkdir();
        }
        File file=new File("g:\\Users\\小负子\\Pictures\\test\\绫濑遥图片\\"+"第"+i+"张"+".jpg");
        URL url = null;
        try {
            url = new URL(str1);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputStream io=con.getInputStream();
        FileOutputStream fi = null;
        try {
            fi = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            byte[] buf = new byte[1024];
            int len = 0;
            while((len=io.read(buf)) != -1) {

                fi.write(buf, 0, len);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fi.close();
        io.close();

    }

}
