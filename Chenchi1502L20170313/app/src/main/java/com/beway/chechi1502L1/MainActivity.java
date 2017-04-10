package com.beway.chechi1502L1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.beway.chechi1502L1.adapter.MyBaseadapter;
import com.beway.chechi1502L1.bean.City;
import com.beway.chechi1502L1.utils.MyUtils;
import com.beway.chechi1502L1.utils.NeetWorkUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String path = "http://api.jisuapi.com/weather/city?appkey=b4d06fdd59ed379f";
    private PullToRefreshExpandableListView pull;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String ss = (String) msg.obj;
                Log.i("uuu", ss);
                Gson gson = new Gson();
                City city = gson.fromJson(ss, City.class);
                List<City.ResultBean> result = city.getResult();
               final List<City.ResultBean> parent = new ArrayList<>();
                List<List<City.ResultBean>> child = new ArrayList<>();
                for (City.ResultBean si : result) {
                    if (si.getParentid().equals("0")) {
                        parent.add(si);
                    }
                }
                for (City.ResultBean sii : parent) {
                    List<City.ResultBean> c = new ArrayList<>();
                    for (City.ResultBean s : result) {
                        if (s.getParentid().equals(sii.getCityid())) {
                            c.add(s);
                        }
                    }
                    child.add(c);
                }

                Log.i("uu", city.toString());
                MyBaseadapter mlys = new MyBaseadapter(result, child, MainActivity.this);
                pull.getRefreshableView().setAdapter(mlys);
                pull.getRefreshableView().setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                        for (int j = 0; j < parent.size() ; j++) {
                            if (j!=groupPosition){
                             //   pull.collapseGroup(j);
                            }
                        }
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull = (PullToRefreshExpandableListView) findViewById(R.id.pullTorefres);
        //  pull.setMode(PullToRefreshBase.Mode.BOTH);
        boolean wifi = NeetWorkUtils.isWifi(this);
        if (wifi) {
            Toast.makeText(this, "有wifi", Toast.LENGTH_SHORT).show();
            iviwGet();
        } else {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("没有网络，请使用移动网络");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean mobile = NeetWorkUtils.isMobile(MainActivity.this);
                    if (mobile) {
                        iviwGet();
                    }
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    iviwGet();
                    dialogInterface.dismiss();
                }

            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        }
        pull.getRefreshableView().setGroupIndicator(null);
        pull.setPullToRefreshOverScrollEnabled(true);
        pull.setMode(PullToRefreshBase.Mode.BOTH.PULL_FROM_END);


        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {

                iviwGet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                iviwGet();
            }
        });
        new Thread() {
            @Override
            public void run() {
                super.run();
                iviwGet();
            }
        }.start();

    }

    private void iviwGet() {
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            if (urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                String press = MyUtils.press(inputStream);
                Message msg = Message.obtain(handler, 1, press);
                msg.sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
