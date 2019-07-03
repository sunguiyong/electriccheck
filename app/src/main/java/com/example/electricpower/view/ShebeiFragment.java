package com.example.electricpower.view;

import android.app.DownloadManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseFragment;
import com.example.electricpower.R;
import com.example.electricpower.adapter.JZAdapter;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.buildlist.BuildReceived;
import com.example.electricpower.entity.to.listviewEntity.Jianzhuliebiao;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ShebeiFragment extends BaseFragment {
    int x = R.layout.activity_jianzhuliebiao;
    @Bind(R.id.list_jianzhuliebiao)
    ListView listJianzhuliebiao;
    private List<BuildReceived.ResultBean> list = new ArrayList<>();
    private String url = "http://192.168.8.30:9981/api/build/buildAll";

    private void initJianzhuliebiao() {
//        for (int i = 0; i < 2; i++) {
//            Jianzhuliebiao j1 = new Jianzhuliebiao("德众广场大厦", 100, 11);
//            list.add(j1);
//            Jianzhuliebiao j2 = new Jianzhuliebiao("会展中心大厦", 200, 22);
//            list.add(j2);
//            Jianzhuliebiao j3 = new Jianzhuliebiao("新希望科技大厦", 300, 33);
//            list.add(j3);
//
//        }
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {
        getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jianzhuliebiao;
    }

    private void getData() {
        getDataFromServer(Request.Method.POST, url, BuildReceived.class, new Response.Listener<BuildReceived>() {
            @Override
            public void onResponse(final BuildReceived response) {
                Log.d("建筑列表", "成功");
                if (response.getStatus() == 200 && response.getResult() != null) {
                    JZAdapter jzAdapter = new JZAdapter(getActivity(), R.layout.item_jianzhuliebiao, response.getResult());
                    listJianzhuliebiao.setAdapter(jzAdapter);
                    listJianzhuliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), DeviceActivity.class);
                            intent.putExtra("buildname", response.getResult().get(position).getBuildName());
                            if (!response.getResult().isEmpty()) {
//                                intent.putExtra("buildId", response.getResult().get(position).getId() + "");
                                SaveData.deviceId=response.getResult().get(position).getId();
                            }
                            startActivity(intent);
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("建筑列表", "失败");
            }
        });
    }

//    private void click(){
//        listJianzhuliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Jianzhuliebiao jianzhuliebiao=list.get(position);
//                showToast(jianzhuliebiao.getAddr());
//                Intent intent = new Intent(getActivity(), ShebeiActivity.class);
//                intent.putExtra("name", list.get(position).getAddr());
//                startActivity(intent);
//            }
//        });
//    }

}
