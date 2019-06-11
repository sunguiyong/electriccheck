package com.example.electricpower.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.adapter.FujinshebeiAdapter;
import com.inuker.bluetooth.library.connect.BleConnectManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;

public class FujinshebeilibiaoActivity1 extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_fujinshebeiliebiao;

    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.list_fujinshebeiliebiao)
    ListView listFujinshebeiliebiao;
    BluetoothManager bluetoothManager;
    BluetoothAdapter mBluetoothAdapter;
    List<BleDevice> list = new ArrayList<>();
    FujinshebeiAdapter fujinshebeiAdapter;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.searchstate_tv)
    TextView searchstateTv;


//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1: {
//                    Log.d("刷新2", "执行");
//                    BleManager.getInstance().cancelScan();
//
//                    list.clear();
//                    doBle();
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    }

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        //设备列表每个item点击监听
//        listFujinshebeiliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("点击了item", position + "");
//                Intent intent = new Intent(FujinshebeilibiaoActivity1.this, ChartTest.class);
//                Log.d("hahaha",list.get(position).getName());
////                intent.putExtra("name", list.get(position).getDevice().getName());
//                startActivity(intent);
//            }
//        });

    }

//    MyHandler myHandler = new MyHandler();
    Timer timer = new Timer();

    @Override
    public void initData() {
        //权限获取
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        //初始化
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true);
        checkBle();

//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                myHandler.sendMessage(message);
//            }
//        }, 2, 3000);
    }


    public void checkBle() {
        if (BleManager.getInstance().isSupportBle()) {
            Log.d("支持ble", "aaa");
            if (BleManager.getInstance().isBlueEnable()) {
                Log.d("支持ble并且打开", "");
                bleConfig();
                doBle();
            } else {
                showToast("请打开蓝牙！");
            }
        } else {
            showToast("不支持ble");
        }

    }
    /**
     * 扫描配置
     */
    public void bleConfig(){
        String[] a = new String[]{"MY"};

        BleScanRuleConfig bleScanRuleConfig = new BleScanRuleConfig.Builder()
                .setDeviceName(true, a)//过滤设备名
                .setScanTimeOut(0)//无限扫描
                .build();
        BleManager.getInstance().initScanRule(bleScanRuleConfig);//注入config配置
    }

    /**
     * 扫描相关
     */
    private void doBle() {
        BleManager.getInstance().scan(new BleScanCallback() {//扫描回调
            @Override
            public void onLeScan(BleDevice bleDevice) {
                Log.d("onLeScan", "执行");
                Log.d("设备名", bleDevice.getName() + bleDevice.getMac());
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                Log.d("onScanFinished", "执行");

            }

            @Override
            public void onScanStarted(boolean success) {
                Log.d("onScanStarted", "执行");
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                Log.d("onScanning", "执行");
                list.add(bleDevice);
                BleManager.getInstance().cancelScan();

                doRefresh();
            }
        });
    }

    /**
     * 刷新listview
     */
    private void doRefresh() {
        fujinshebeiAdapter = new FujinshebeiAdapter(mContext, R.layout.item_fujinshebei, list);
        listFujinshebeiliebiao.setAdapter(fujinshebeiAdapter);
//        fujinshebeiAdapter.notifyDataSetChanged();
//                list.clear();
        doBle();
        list = new ArrayList<>();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BleManager.getInstance().cancelScan();//销毁
//        timer.cancel();
    }
}