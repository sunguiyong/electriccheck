package com.example.electricpower.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.electricpower.R;
import com.example.electricpower.entity.to.listviewEntity.Jianzhuliebiao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JianzhuliebiaoAdapter extends ArrayAdapter<Jianzhuliebiao> {
    int x = R.layout.item_jianzhuliebiao;

    private int resourceId;

    public JianzhuliebiaoAdapter(Context context, int resource, List<Jianzhuliebiao> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        Jianzhuliebiao jianzhuliebiao=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView addr=view.findViewById(R.id.addr);
        TextView sbsl=view.findViewById(R.id.shebeishuliang);
        TextView zxsl=view.findViewById(R.id.zaixianshuliang);
        addr.setText(jianzhuliebiao.getAddr());
        sbsl.setText(jianzhuliebiao.getShebeiCount()+"");
        zxsl.setText(jianzhuliebiao.getZaixianCount()+"");
        return view;
    }
}
