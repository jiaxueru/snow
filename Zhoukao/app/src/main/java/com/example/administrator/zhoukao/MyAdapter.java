package com.example.administrator.zhoukao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * date:2017/4/9 0009
 * authom:贾雪茹
 * function:
 */

public class MyAdapter extends BaseAdapter{
    private List<Bean.ListBean> list;
    private Context context;

    public MyAdapter(List<Bean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            holder.text1= (TextView) view.findViewById(R.id.name);
            holder.text2= (TextView) view.findViewById(R.id.describe);
            view.setTag(holder);
        }else{
            holder = (MyHolder) view.getTag();
        }
        holder.text1.setText(list.get(i).getSite_name());
        holder.text2.setText(list.get(i).getAddress());

        return view;
    }
    class MyHolder{
        TextView text1,text2;

    }
}
