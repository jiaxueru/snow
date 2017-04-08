package com.example.administrator.zhoukao_demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * date:2017/4/7 0007
 * authom:贾雪茹
 * function:
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<Bean.ListBean>mList;

    public MyAdapter(Context context, List<Bean.ListBean> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if(view==null){
            holder=new MyHolder();
            view=View.inflate(context,R.layout.items,null);
            holder.text1= (TextView) view.findViewById(R.id.textview);
            holder.text2= (TextView) view.findViewById(R.id.textview2);
            view.setTag(holder);
        }else{
            holder= (MyHolder) view.getTag();
        }
        holder.text1.setText(mList.get(i).getSite_name());
        holder.text2.setText(mList.get(i).getAddress());
        return view;
    }
    class MyHolder{
        TextView text1,text2;
    }
}
