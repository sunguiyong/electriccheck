package com.example.electricpower.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.adapter.FujinshebeiAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;

public class FujinshebeilibiaoActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_fujinshebeiliebiao;

    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.list_fujinshebeiliebiao)
    ListView listFujinshebeiliebiao;

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    List<BluetoothDevice> list = new ArrayList<>();
    FujinshebeiAdapter fujinshebeiAdapter;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.searchstate_tv)
    TextView searchstateTv;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;


    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        //设备列表每个item点击监听
        listFujinshebeiliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FujinshebeilibiaoActivity.this, ChartTest.class);
                intent.putExtra("name", list.get(position).getName());
                startActivity(intent);
                mBluetoothAdapter.cancelDiscovery();
                Log.d("search cancel", "取消搜索");
                searchstateTv.setText("设备列表");
            }
        });
        //下拉刷新监听
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新块点击监听
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBluetoothAdapter.cancelDiscovery();
                        refresh.finishRefresh();
                    }
                });
                doBluetooth();
                if (isOpen){
                    list.clear();
                    mBluetoothAdapter.startDiscovery();
                }else {
                    showToast("请检查蓝牙是否打开！");
                }
            }
        });
    }

    @Override
    public void initData() {
        //权限获取
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        //广播注册
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, intentFilter);
        IntentFilter intentFilter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intentFilter1);
        IntentFilter intentFilter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(mReceiver, intentFilter2);
        Log.d("开始搜索设备！------", "开始搜索设备");
        //蓝牙权限检测
        doBluetooth();
        mBluetoothAdapter.startDiscovery();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onPause() {
//        unregisterReceiver(mReceiver);
//        mBluetoothAdapter.cancelDiscovery();
//        Log.d("search cancel","取消搜索");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 蓝牙相关权限检测
     */
    private boolean isOpen = false;

    private void doBluetooth() {
        if (mBluetoothAdapter == null) {
            showToast("该设备不支持蓝牙");
        }
        //检查当前是否已启用蓝牙。 如果此方法返回 false，则表示蓝牙处于停用状态。要请求启用蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
            finish();
        } else {
            isOpen = true;
            //已扫描过的设备
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    Log.d("已扫描过的设备", "invoked");

                    list.add(device);
                }
                FujinshebeiAdapter fujinshebeiAdapter = new FujinshebeiAdapter(mContext, R.layout.item_fujinshebei, list);
                listFujinshebeiliebiao.setAdapter(fujinshebeiAdapter);
                fujinshebeiAdapter.notifyDataSetChanged();
            } else {
                Log.d("===", "之前扫描设备为空");
            }
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_fujinshebeiliebiao;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            default:
                break;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                tt1.append("\n" + device.getName() + "--->" + device.getAddress() + "--" + device.getBondState() + "-" + device.getType());
                if (!list.contains(device)){
                    list.add(device);
                }
                FujinshebeiAdapter adapter = new FujinshebeiAdapter(FujinshebeilibiaoActivity.this, R.layout.item_fujinshebei, list);
                listFujinshebeiliebiao.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("搜索中...", "Searching！");
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                mBluetoothAdapter.cancelDiscovery();
                Log.d("搜索完成！", "Searching");
                searchstateTv.setText("设备列表");
                refresh.finishRefresh();
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                Log.d("开始搜索", "start search!");
                searchstateTv.setText("搜索中...");
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        mBluetoothAdapter.cancelDiscovery();
        Log.d("search cancel", "取消搜索");
        super.onDestroy();
    }
}