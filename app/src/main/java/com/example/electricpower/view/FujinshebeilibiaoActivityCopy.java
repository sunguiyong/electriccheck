package com.example.electricpower.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.adapter.FujinshebeiAdapterCopy;
import com.example.electricpower.entity.to.SaveData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 使用中using
 * 低功耗蓝牙扫描附近设备
 */
public class FujinshebeilibiaoActivityCopy extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_fujinshebeiliebiao;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.list_fujinshebeiliebiao)
    ListView listFujinshebeiliebiao;


    BluetoothManager bluetoothManager;

    BluetoothAdapter mBluetoothAdapter;
    List<ScanResult> list = new ArrayList<>();
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.searchstate_tv)
    TextView searchstateTv;
    //    @Bind(R.id.refresh)
//    MaterialRefreshLayout refresh;
    private ArrayList<String> scanRecords;

    private BluetoothAdapter.LeScanCallback mLeScanCallback;
    FujinshebeiAdapterCopy fujinshebeiAdapterCopy;

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        //设备列表每个item点击监听
        listFujinshebeiliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice device = fujinshebeiAdapterCopy
                        .getDevice(position);
//                showToast(fujinshebeiAdapterCopy.getScanRecords().get(0).toString());
                scanRecords = fujinshebeiAdapterCopy.getScanRecords();
                fujinshebeiAdapterCopy.checkScanResult(scanRecords, position);

                Intent intent = new Intent(FujinshebeilibiaoActivityCopy.this, ChartTest.class);

                intent.putExtra("mac", device.getAddress());
                intent.putExtra("macsave",SaveData.listName.get(position));
                Log.d("macItem",device.getAddress());
                startActivity(intent);
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

        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        //蓝牙权限检测
        doBluetooth();
        //设置数据适配器
        fujinshebeiAdapterCopy = new FujinshebeiAdapterCopy(this);
        listFujinshebeiliebiao.setAdapter(fujinshebeiAdapterCopy);
//        handler.postDelayed(task,1000);
        getScanResualt();
        //新线程开启ble扫描
        new Thread(new Runnable() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                mBluetoothAdapter.startLeScan(mLeScanCallback);
            }
        }).start();
    }

    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
            getScanResualt();
        }
    };

    private void getScanResualt() {
        mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device, final int rssi,
                                 final byte[] scanRecord) {
                FujinshebeilibiaoActivityCopy.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (device.getName() != "" && device.getName() != null && device.getName().startsWith("YG")) {
                            refreshListView(device, rssi, scanRecord);
                        }
                    }
                });
            }
        };
    }

    /**
     * 存放扫描到的设备并且更新listview
     */
    public void refreshListView(BluetoothDevice device, int rssi, byte[] scanRecord) {
        Log.d("scancallback：", "运行");
        String address = device.getAddress();
        Log.d("addressMy", address);
        fujinshebeiAdapterCopy.addDevice(device, rssi,
                bytesToHex(scanRecord));
        fujinshebeiAdapterCopy.notifyDataSetChanged();
//        invalidateOptionsMenu();
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

    private void doBluetooth() {
        if (mBluetoothAdapter == null) {
            showToast("该设备不支持蓝牙");
        }
        //检查当前是否已启用蓝牙。 如果此方法返回 false，则表示蓝牙处于停用状态。要请求启用蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            finish();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
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
            Log.d("onReceive method", "is invoked");
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                if (!list.contains(device)) {
//                    list.add(device);
//                }
//
//                FujinshebeiAdapter adapter = new FujinshebeiAdapter(FujinshebeilibiaoActivity.this, R.layout.item_fujinshebei, list);
//                listFujinshebeiliebiao.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                Log.d("搜索到设备", device.getName() + "==" + device.getAddress());
            } else if (action.equals(mBluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
//                if (Build.MODEL.equals("PADT00")){
//                    mBluetoothAdapter.cancelDiscovery();
//                    unregisterReceiver(mReceiver);
//                }
//                mBluetoothAdapter.cancelDiscovery();
                Log.d("搜索完成！", Build.MODEL + "");
                searchstateTv.setText("设备列表");
//                refresh.finishRefresh();
            } else if (action.equals(mBluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                Log.d("开始搜索", "start search!");
                searchstateTv.setText("搜索中...");
            } else if (action.equals(mBluetoothAdapter.STATE_ON)) {
                Log.d("STATE_ON", action);
            } else if (action.equals(mBluetoothAdapter.STATE_OFF)) {
                Log.d("STATE_OFF", action);
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        mBluetoothAdapter.cancelDiscovery();

        Log.d("search cancel", "取消搜索");
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
        super.onDestroy();
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static final char[] hexArray = "0123456789ABCDEF".toCharArray();


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}