package com.example.electricpower.view;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.electricpower.BaseFragment;
import com.example.electricpower.R;
import com.example.electricpower.adapter.JZAdapter;
import com.example.electricpower.entity.to.listviewEntity.Jianzhuliebiao;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ShebeiFragment extends BaseFragment {
    int x = R.layout.activity_jianzhuliebiao;
    @Bind(R.id.list_jianzhuliebiao)
    ListView listJianzhuliebiao;
    private List<Jianzhuliebiao> list = new ArrayList<>();

    private void initJianzhuliebiao() {
        for (int i = 0; i < 2; i++) {
            Jianzhuliebiao j1 = new Jianzhuliebiao("德众广场大厦", 100, 11);
            list.add(j1);
            Jianzhuliebiao j2 = new Jianzhuliebiao("会展中心大厦", 200, 22);
            list.add(j2);
            Jianzhuliebiao j3 = new Jianzhuliebiao("新希望科技大厦", 300, 33);
            list.add(j3);
        }
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {
        initJianzhuliebiao();
        JZAdapter jzAdapter = new JZAdapter(getActivity(), R.layout.item_jianzhuliebiao, list);
        listJianzhuliebiao.setAdapter(jzAdapter);
        listJianzhuliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Jianzhuliebiao jianzhuliebiao=list.get(position);
//                showToast(jianzhuliebiao.getAddr());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jianzhuliebiao;
    }

}
