package com.example.administrator.zhoukao;

import android.content.Context;
import android.os.AsyncTask;
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

import static android.os.Build.VERSION_CODES.M;

/**
 * date:2017/4/9 0009
 * authom:贾雪茹
 * function:
 */

public class MyAsyncTask extends AsyncTask<String,String,String>{
    private Context context;
    private StringBuilder mBuilder;
     private ListView listview;
    private String path;
    private List<Bean.ListBean> mList;

    public MyAsyncTask(Context context, ListView listview, String path) {
        this.context = context;
        this.listview = listview;
        this.path = path;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson=new Gson();
        Bean bean = gson.fromJson(s, Bean.class);
        mList = bean.getList();
        final MyAdapter adapter=new MyAdapter(mList,context);
        listview.setAdapter(adapter);
        //条目的点击事件；
       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(context, mList.get(i).getId()+"",Toast.LENGTH_LONG).show();
           }
       });
        //条目的长按事件；
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
             mList.remove(i);
             adapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
       try {
                   URL url=new URL(path);
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
}
