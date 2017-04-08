package com.example.administrator.zhoukao_demo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * date:2017/4/7 0007
 * authom:贾雪茹
 * function:
 */

public class MyAsyncTask extends AsyncTask<String,String,String>{

    private StringBuilder mBuilder;
    private Context context;
    private List<Bean.ListBean>list;
    private ListView listview;
    private MyAdapter mAdapter;
    private Handler handler;
    public MyAsyncTask(Context context, ListView listview,Handler handler) {
        this.handler=handler;
        this.context = context;
        this.listview = listview;
    }

    @Override
    protected String doInBackground(String... strings) {
         try {
                     URL url=new URL("http://result.eolinker.com/KLn5hSP9f6fed196f92ec0148255a48aebb2c6cc5f97f0e?uri=user");
                     HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                     conn.setConnectTimeout(5000);
                     conn.setRequestMethod("GET");
                     if(conn.getResponseCode()==200){
                         InputStream input = conn.getInputStream();
                         mBuilder = new StringBuilder();
                        BufferedReader buffered=new BufferedReader(new InputStreamReader(input,"utf-8"));
                         String string="";
                         while((string=buffered.readLine())!=null){
                                 mBuilder.append(string);
                         }
                        // String str= builder.toString();
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
        super.onPostExecute(s);

        Message message=Message.obtain();
        message.obj=s;
        message.what=0;
        handler.sendMessage(message);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
