package com.example.administrator.mywork;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * date:2017/4/15 0015
 * authom:贾雪茹
 * function:
 */

public class FragmentAdapter extends BaseAdapter{
    private List<HttpBean.DataBean>list;
    private Context context;

    public FragmentAdapter(List<HttpBean.DataBean> list, Context context) {
       this.list=list;
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
            view=View.inflate(context,R.layout.listviewitem,null);
            holder.imageview= (ImageView) view.findViewById(R.id.imageview);
            holder.textview= (TextView) view.findViewById(R.id.textview);
            holder.textview2= (TextView) view.findViewById(R.id.text2);
            view.setTag(holder);
        }else{
            holder=(MyHolder) view.getTag();
        }
        if(list.get(i).getMedia_info()!=null){
          if(list.get(i).getArticle_url()!=null){
              ImageLoader.getInstance().displayImage(list.get(i).getMedia_info().getAvatar_url(),holder.imageview);
          }
        }

        holder.textview.setText(list.get(i).getTitle());

        return view;
    }
    class MyHolder{
        ImageView imageview;
        TextView textview,textview2;
    }
}
