package com.example.administrator.mywork;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import static android.R.attr.path;
import static android.R.attr.version;
import static com.example.administrator.mywork.R.id.listview;
import static com.example.administrator.mywork.R.string.app_name;

/**
 * date:2017/4/15 0015
 * authom:贾雪茹
 * function:
 */

public class MyFragment extends Fragment{
    private String url;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s=(String) msg.obj;
            Gson gson=new Gson();
            HttpBean httpBean = gson.fromJson(s, HttpBean.class);
            List<HttpBean.DataBean> data = httpBean.getData();
            mListview.setAdapter(new FragmentAdapter(data,getActivity()));
        }
    };
    private ListView mListview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
   public MyFragment(String url){
       this.url=url;
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.item,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListview = (ListView) getView().findViewById(listview);

        String path="http://ic.snssdk.com/2/article/v25/stream/?count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4" +
                ".4&uuid=352284045861006&openudid=84c1c7b192991cc6";
        MyAsyncTask task=new MyAsyncTask(getActivity(),handler,path);
        task.execute();

    }
}
