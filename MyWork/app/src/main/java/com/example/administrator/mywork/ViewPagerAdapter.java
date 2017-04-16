package com.example.administrator.mywork;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/4/15 0015
 * authom:贾雪茹
 * function:
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private Context context;
    private List<TitleBean.DataBeanX.DataBean> title;
    private List<Fragment>fragmentlist;
    private List<String> sslist=new ArrayList<String>();
    public ViewPagerAdapter(List<String> sslist,FragmentManager fm, Context context, List<TitleBean.DataBeanX.DataBean> title,List<Fragment>fragmentlist) {
        super(fm);
        this.sslist=sslist;
        this.context = context;
        this.title = title;
        this.fragmentlist=fragmentlist;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position).getCategory();
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }
}
