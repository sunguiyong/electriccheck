package com.example.electricpower.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electricpower.R;
import com.example.electricpower.entity.to.listviewEntity.Jianzhuliebiao;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JZAdapter extends ArrayAdapter<Jianzhuliebiao> {
    int x = R.layout.item_jianzhuliebiao;

    private int resourId;

    public JZAdapter(Context context, int resource, List<Jianzhuliebiao> objects) {
        super(context, resource, objects);
        resourId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Jianzhuliebiao jianzhuliebiao = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourId, parent, false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.addr.setText(jianzhuliebiao.getAddr()+"");
        viewHolder.shebeishuliang.setText(jianzhuliebiao.getShebeiCount()+"");
        viewHolder.zaixianshuliang.setText(jianzhuliebiao.getZaixianCount()+"");

        return view;
    }

    class ViewHolder {
        @Bind(R.id.addr)
        TextView addr;
        @Bind(R.id.shebeishuliang)
        TextView shebeishuliang;
        @Bind(R.id.zaixianshuliang)
        TextView zaixianshuliang;
        @Bind(R.id.img_right)
        ImageView imgRight;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
