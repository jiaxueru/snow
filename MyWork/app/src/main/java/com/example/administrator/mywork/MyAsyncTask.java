package com.example.administrator.mywork;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * date:2017/4/15 0015
 * authom:贾雪茹
 * function:
 */

public class MyAsyncTask extends AsyncTask<String,String,String>{
    private Context context;
    private StringBuilder mBuilder;
    private ListView listview;
    private Handler handler;
    private String path;
    public MyAsyncTask(Context context, Handler handler,String path) {
        this.context = context;
        this.handler = handler;
        this.path=path;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL(path);
           HttpURLConnection http=(HttpURLConnection)url.openConnection();
            //设置延迟时间；
            http.setConnectTimeout(5000);
            http.setRequestMethod("GET");
            if(http.getResponseCode()==200){
                mBuilder = new StringBuilder();
                InputStream input =http.getInputStream();
                BufferedReader buffer=new BufferedReader(new InputStreamReader(input,"utf-8"));
                String str="";
                while((str=buffer.readLine())!=null){
                    mBuilder.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mBuilder.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Message message=Message.obtain();
        message.obj=s;
        message.what=0;
        handler.sendMessage(message);
        super.onPostExecute(s);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
