package com.example.administrator.zhoukao_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import static android.R.id.list;
import static com.example.administrator.zhoukao_demo.R.id.listview;

public class MainActivity extends AppCompatActivity {
    private ListView mListview;
    private List<Bean.ListBean> mList;
    private Handler handler=new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             String str= (String) msg.obj;
            Gson gson=new Gson();

            Bean bean = gson.fromJson(str, Bean.class);
            //List<Bean.ListBean> list = bean.getList();
            mList = bean.getList();
            mMAdapter = new MyAdapter(MainActivity.this,mList);
            mListview.setAdapter(mMAdapter);
        }
    };
    private MyAdapter mMAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = (ListView) findViewById(listview);

        MyAsyncTask task=new MyAsyncTask(MainActivity.this,mListview, handler);
        task.execute();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,mList.get(i).getId()+"",Toast.LENGTH_LONG).show();
            }
        });
        mListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mList.remove(i);
                mMAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
