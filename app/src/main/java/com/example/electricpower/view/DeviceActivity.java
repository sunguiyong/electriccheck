package com.example.electricpower.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.adapter.DeviceAdapter;
import com.example.electricpower.entity.to.DeviceInfo;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.deviceall.DeviceGet;
import com.example.electricpower.entity.to.deviceall.DevicePost;
import com.example.electricpower.entity.to.wenshidu.WenShiGet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import butterknife.Bind;

public class DeviceActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_fujinshebeiliebiaoback;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.devicename_tv)
    TextView devicenameTv;
    @Bind(R.id.lishijilu_tv)
    TextView lishijiluTv;
    @Bind(R.id.searchstate_tv)
    TextView searchstateTv;
    @Bind(R.id.list_fujinshebeiliebiao)
    ListView listFujinshebeiliebiao;
    private int page = 1;

    String url = SaveData.mainUrl + "device/deviceAll";
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    private String urlThis = SaveData.mainUrl + "device/realTimeDate/";

    List<DeviceGet.ResultBean> resultBeans;
    List<DeviceGet.ResultBean> saveResultBeans = new ArrayList<>();
    private DeviceAdapter deviceAdapter;

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
//        listFujinshebeiliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mContext, DeviceInfoActivity.class);
//                intent.putExtra("devicename", resultBeans.get(position).getDeviceName());
//                intent.putExtra("deviceid", resultBeans.get(position).getDeviceId());
////                getDataThis(resultBeans.get(position).getDeviceId());
//
//                startActivity(intent);
//            }
//        });
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                if (page != 1) {
                    page--;
                    getData(page);
                }
                refresh.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                page++;
                getData(page);
                Log.d("page", page + "");
            }
        });
    }

    @Override
    public void initData() {
        refresh.setLoadMore(true);
        devicenameTv.setText(getIntent().getStringExtra("buildname"));

        deviceAdapter = new DeviceAdapter(mContext, R.layout.item_deviceall, resultBeans);
//        listFujinshebeiliebiao.setAdapter(deviceAdapter);
        getData(page);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fujinshebeiliebiaoback;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
        }
    }

    private void getData(final int page) {
        DevicePost devicePost = new DevicePost();
//        devicePost.setId(getIntent().getStringExtra("buildId"));
        devicePost.setId(SaveData.deviceId);
        devicePost.setPage(page);
        devicePost.setRows(10);
        Gson gson = new Gson();
        String str = gson.toJson(devicePost);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, DeviceGet.class, new Response.Listener<DeviceGet>() {
            @Override
            public void onResponse(final DeviceGet response) {
                if (page == 1) {
                    saveResultBeans = response.getResult();
                    if (saveResultBeans == null) {
                        saveResultBeans = new ArrayList<>();
                    }
                } else {
                    if (response.getResult() != null) {
                        saveResultBeans.addAll(response.getResult());
                    }
                }


                //设置某个建筑群下的设备列表
                deviceAdapter = new DeviceAdapter(mContext, R.layout.item_deviceall, response.getResult());
                listFujinshebeiliebiao.setAdapter(deviceAdapter);
//                resultBeans = response.getResult();
//                deviceAdapter.setList(saveResultBeans);
                deviceAdapter.notifyDataSetChanged();


                Log.d("成功", "成功");
                if (page != 1) {
                    refresh.finishRefreshLoadMore();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void getDataThis(String id) {
        getDataFromServer(Request.Method.GET, urlThis + id, WenShiGet.class, new Response.Listener<WenShiGet>() {
            @Override
            public void onResponse(WenShiGet response) {
                Log.d("GET请求", "成功");
                if (!response.getResult().getEvent().isEmpty()) {
                    Log.d("不为空执行", "不为空执行此方法");
                    WenShiGet.ResultBean.DeviceBean deviceBean = response.getResult().getDevice();
                    List<WenShiGet.ResultBean.NodesBean> nodesBeans = response.getResult().getNodes();
                    List<WenShiGet.ResultBean.EventBean> eventBeans = response.getResult().getEvent();
                    //获取温度id
//                final String tempureNodeIdStr = GeneteNodeId(Long.parseLong(deviceBean.getSdevId()) ,1288L).toString();
                    final String tempureNodeIdStr = nodesBeans.stream().filter(new Predicate<WenShiGet.ResultBean.NodesBean>() {
                        @Override
                        public boolean test(WenShiGet.ResultBean.NodesBean nodesBean) {
                            return nodesBean.getName().equals("温度");
                        }
                    }).findFirst().get().getSdevNodeid();

                    //获取温度值
                    double tempureNodeValue = eventBeans.stream().filter(new Predicate<WenShiGet.ResultBean.EventBean>() {
                        @Override
                        public boolean test(WenShiGet.ResultBean.EventBean eventBean) {
                            return eventBean.getNodeId().equals(tempureNodeIdStr);
                        }
                    }).findFirst().get().getNewData();

                    //湿度id
                    final String humidityNodeIdStr = nodesBeans.stream().filter(new Predicate<WenShiGet.ResultBean.NodesBean>() {
                        @Override
                        public boolean test(WenShiGet.ResultBean.NodesBean nodesBean) {
                            return nodesBean.getName().equals("湿度");
                        }
                    }).findFirst().get().getSdevNodeid();

                    //获取温度值
                    double humidityNodeValue = eventBeans.stream().filter(new Predicate<WenShiGet.ResultBean.EventBean>() {
                        @Override
                        public boolean test(WenShiGet.ResultBean.EventBean eventBean) {
                            return eventBean.getNodeId().equals(humidityNodeIdStr);
                        }
                    }).findFirst().get().getNewData();
                    DeviceInfo.temperature = tempureNodeValue / 100;
                    DeviceInfo.humidity = humidityNodeValue / 100;
                    Log.d("当前温湿度：", tempureNodeValue + "---" + humidityNodeValue);
                } else {
                    showToast("Event为空！！！");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GET请求", "失败");
            }
        });
    }
}
