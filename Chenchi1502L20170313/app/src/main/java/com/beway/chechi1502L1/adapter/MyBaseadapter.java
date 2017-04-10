package com.beway.chechi1502L1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.beway.chechi1502L1.R;
import com.beway.chechi1502L1.bean.City;

import java.util.List;

/**
 * 作  者： 陈驰 on 2017/3/13 09:35
 * 类用途：
 */

public class MyBaseadapter extends BaseExpandableListAdapter {
    List<City.ResultBean> list;
    List<List<City.ResultBean>> child;
    Context context;

    public MyBaseadapter(List<City.ResultBean> list, List<List<City.ResultBean>> child, Context context) {
        this.list = list;
        this.child = child;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean b, View convertview, ViewGroup viewGroup) {
      if (convertview==null){
          convertview=View.inflate(context, R.layout.item_one,null);
          TextView city_one = (TextView) convertview.findViewById(R.id.city_one);
          city_one.setText(list.get(position).getCity());
      }
        return convertview;
    }

    @Override
    public View getChildView(int position, int i1, boolean b, View convertview, ViewGroup viewGroup) {
        if (convertview==null){
            convertview=View.inflate(context, R.layout.item_two,null);
            TextView city_two = (TextView) convertview.findViewById(R.id.city_two);
            city_two.setText(child.get(position).get(i1).getCity());

        }
        return convertview;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
