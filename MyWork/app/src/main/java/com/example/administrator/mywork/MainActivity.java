package com.example.administrator.mywork;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends FragmentActivity {
    private TitleBean.DataBeanX mData;
    private TabLayout mTab;
    private ViewPager mViewpager;
    private List<Fragment> mFragmentlist;
    private List<TitleBean.DataBeanX.DataBean> mTitle;
    private List<String> mNamelist;
    private String mName;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s=(String) msg.obj;
            //将读取到的json串进行解析；
            Gson gson=new Gson();
            TitleBean bean = gson.fromJson(s,TitleBean.class);
            mData = bean.getData();
            mTitle = mData.getData();
           // mDatas = mData.getData();
            for (int i = 0; i < mTitle.size(); i++) {
                mName = mTitle.get(i).getName();
                mNamelist = new ArrayList<String>();
            }

            String url="http://ic.snssdk.com/2/article/v25/stream/?count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
            mFragmentlist = new ArrayList<Fragment>();
            for (int i = 0; i < mTitle.size(); i++) {
                mFragmentlist.add(new MyFragment(url+mTitle.get(i).getCategory()));
                Log.i("haha",mTitle.get(i).getCategory());
            }
            //给ViewPager设置适配器；
            mViewpager.setAdapter(new ViewPagerAdapter(mNamelist,getSupportFragmentManager(),MainActivity.this,mTitle,mFragmentlist));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        //找到控件；
        mTab = (TabLayout) findViewById(R.id.tab);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

         mTab.setupWithViewPager(mViewpager);
        //设置Tab模式；
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置字体的颜色；  参数一：int 未选中颜色
//        参数二：int 被选中颜色
        mTab.setTabTextColors(Color.BLACK,Color.RED);
        //给TabLayout绑定要展示的ViewPager；
        String path="http://ic.snssdk.com/article/category/get/v2/?user_city=%E5%AE%89%E9%98%B3&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465099837&categories=%5B%22video%22%2C%22news_hot%22%2C%22news_local%22%2C%22news_society%22%2C%22subscription%22%2C%22news_entertainment%22%2C%22news_tech%22%2C%22news_car%22%2C%22news_sports%22%2C%22news_finance%22%2C%22news_military%22%2C%22news_world%22%2C%22essay_joke%22%2C%22image_funny%22%2C%22image_ppmm%22%2C%22news_health%22%2C%22positive%22%2C%22jinritemai%22%2C%22news_house%22%5D&version=17375902057%7C14%7C1465030267&iid=4471477475&device_id=17375902057&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Samsung+Galaxy+S3+-+4.3+-+API+18+-+720x1280&os_api=18&os_version=4.3&openudid=7036bc89d44f680c";
        MyAsyncTask task=new MyAsyncTask(MainActivity.this,handler,path);
        task.execute();
    }

}
