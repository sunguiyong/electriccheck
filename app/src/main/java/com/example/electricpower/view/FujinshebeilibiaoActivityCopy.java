package com.example.electricpower.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
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
import com.example.electricpower.adapter.FujinshebeiAdapterCopy;
import com.example.electricpower.entity.to.DeviceInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;

/**
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

    private BluetoothAdapter.LeScanCallback mLeScanCallback;
    FujinshebeiAdapterCopy fujinshebeiAdapterCopy;
    Handler handler;
    ArrayList<BluetoothDevice> bluetoothDevices;

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        //设备列表每个item点击监听
        listFujinshebeiliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice device = fujinshebeiAdapterCopy
                        .getDevice(position);
                Intent intent = new Intent(FujinshebeilibiaoActivityCopy.this, ChartTest.class);
                intent.putExtra("name", device.getName());

                startActivity(intent);
            }
        });
        //下拉刷新监听
//        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
//            @Override
//            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//                //下拉刷新块点击监听
//                refresh.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mBluetoothAdapter.getBluetoothLeScanner().stopScan(scanCallback);
//                        refresh.finishRefresh();
//                    }
//                });
//                doBluetooth();
//                if (isOpen) {
////                    list.clear();
////                    mBluetoothAdapter.getBluetoothLeScanner().startScan(scanCallback);
//                } else {
//                    showToast("请检查蓝牙是否打开！");
//                }
//            }
//        });
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
//        doBluetooth();
        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        //设置数据适配器
        fujinshebeiAdapterCopy = new FujinshebeiAdapterCopy(this);
        listFujinshebeiliebiao.setAdapter(fujinshebeiAdapterCopy);
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

    private void getScanResualt() {
        mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device, final int rssi,
                                 final byte[] scanRecord) {
                FujinshebeilibiaoActivityCopy.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("scancallback：", "运行");
                        if (device.getName() != "" || device.getName() != null) {
                            fujinshebeiAdapterCopy.addDevice(device, rssi,
                                    bytesToHex(scanRecord));

                            fujinshebeiAdapterCopy.notifyDataSetChanged();
                            invalidateOptionsMenu();
                        }

                    }
                });
            }
        };
    }

    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            String data = bytesToHexString(result.getScanRecord().getBytes());
            Log.d("设备名称：" + result.getDevice().getName(), data);

            if (result.getDevice().getType() == 2) {
                list.add(result);
            }
        }
    };

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

        } else {
            isOpen = true;
            //已扫描过的设备
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    Log.d("已扫描过的设备", "invoked");

//                    list.add(device);
                }
//                FujinshebeiAdapter fujinshebeiAdapter = new FujinshebeiAdapter(mContext, R.layout.item_fujinshebei, list);
//                listFujinshebeiliebiao.setAdapter(fujinshebeiAdapter);
//                fujinshebeiAdapter.notifyDataSetChanged();
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