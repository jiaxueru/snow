package com.example.administrator.zhoukao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 //找到控件；
        mListView = (ListView) findViewById(R.id.listview);
        String path="http://result.eolinker.com/KLn5hSP9f6fed196f92ec0148255a48aebb2c6cc5f97f0e?uri=user";
        MyAsyncTask task=new MyAsyncTask(MainActivity.this,mListView,path);
        task.execute();
        SlidingMenu menu=new SlidingMenu(MainActivity.this);
        //设置滑动模式，从左边滑出；
        menu.setMode(SlidingMenu.LEFT);
        //设置阴影宽度；
        menu.setShadowWidth(10);
        //设置阴影颜色；
        menu.setShadowDrawable(R.drawable.shadow);
        //获取屏幕的宽度；
        int width=this.getResources().getDisplayMetrics().widthPixels;
        //设置滑动菜单视图的宽度--为总屏幕宽度的三分之二；
        menu.setBehindWidth(width/3*2);
       //设置渐入渐出效果的值；
        menu.setFadeDegree(0.35f);
        //设置附加Activity对象;
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置菜单的显示布局；
        menu.setMenu(R.layout.menu);
    }
}
