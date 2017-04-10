package com.beway.chechi1502L1.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MyUtils {
    public static String press(InputStream inputStream){
        try {
        byte[] b=new byte[1024];
        int len=0;
        ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
       while((len=inputStream.read(b))!=-1){
         arrayOutputStream.write(b,0,len);
       }
            return  arrayOutputStream.toString("utf-8");
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }
}
