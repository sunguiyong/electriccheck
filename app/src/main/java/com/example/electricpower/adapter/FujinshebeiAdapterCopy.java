package com.example.electricpower.adapter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clj.fastble.data.BleDevice;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.DeviceInfo;
import com.example.electricpower.view.ChartTest;
import com.example.electricpower.view.FujinshebeilibiaoActivity;
import com.example.electricpower.view.FujinshebeilibiaoActivityCopy;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FujinshebeiAdapterCopy extends BaseAdapter {
    int x = R.layout.item_fujinshebei;
    private Context context;
    private List<BleDevice> list;
    int resourceId;

    private LayoutInflater mInflater;
    private ArrayList<BluetoothDevice> mLeDevices;
    private ArrayList<Integer> RSSIs;
    private ArrayList<String> scanRecords;

    public FujinshebeiAdapterCopy(Context context) {
        mLeDevices = new ArrayList<BluetoothDevice>();
        RSSIs = new ArrayList<Integer>();
        scanRecords = new ArrayList<String>();
        this.mInflater = LayoutInflater.from(context);
    }


    public FujinshebeiAdapterCopy(Context context, int resourceId, List<BleDevice> list) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
    }

    public void addDevice(BluetoothDevice device, int RSSI, String scanRecord) {
        Log.d("信号", RSSI + "" + "----" + device.getName() + scanRecord);
        //如果不包含在内就放集合，去重&& device.getName().contains("MY")
        if (!mLeDevices.contains(device)) {
            if (device.getName() != null && device.getName() != "" && device.getName().startsWith("MY")) {
                this.mLeDevices.add(device);
                this.RSSIs.add(RSSI);
                this.scanRecords.add(scanRecord);
            }
        } else {
            //循环判断mac地址是否相等，相等则放入相应的集合中
            for (int i = 0; i < mLeDevices.size(); i++) {
                BluetoothDevice d = mLeDevices.get(i);
                if (device.getAddress().equals(d.getAddress())) {
                    RSSIs.set(i, RSSI);
                    scanRecords.set(i, scanRecord);
                }
                Log.d("循环判断", "执行");
            }
        }
    }

    public BluetoothDevice getDevice(int position) {
        // TODO Auto-generated method stub
        return mLeDevices.get(position);
    }

    @Override
    public int getCount() {
        return mLeDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return mLeDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_fujinshebei, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String name = mLeDevices.get(position).getName();
        if (name != null) {
            viewHolder.name.setText(name);
        } else {
            viewHolder.name.setText("未知设备");
        }
        Log.d("适配器NAME", mLeDevices.get(position).getName() + "===" + mLeDevices.get(position).getAddress());


        viewHolder.shebeiState.setText(RSSIs.get(position).toString() + "");

        String temp = scanRecords.get(position).toString();
        String wendu16 = temp.substring(22, 26);
        String shidu16 = temp.substring(26, 30);
        String wendu = new BigInteger(wendu16, 16).toString();
        String shidu = new BigInteger(shidu16, 16).toString();
        double wenduLast = Double.parseDouble(wendu) / 100;
        double shiduLast = Double.parseDouble(shidu) / 100;
        Log.d("温湿度", wenduLast + "----" + shiduLast);

        viewHolder.shidu.setText("湿度：" + shiduLast + "");
        viewHolder.mac.setText("温度：" + wenduLast + "");
        DeviceInfo.temperature=wenduLast;
        DeviceInfo.humidity=shiduLast;

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("onclick","执行");
//                Intent intent=new Intent(,ChartTest.class);
//                intent.putExtra("name",mLeDevices.get(position).getName());
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }

    public void clear() {
        // TODO Auto-generated method stub
        mLeDevices.clear();
        RSSIs.clear();
        scanRecords.clear();
        this.notifyDataSetChanged();
    }

    /**
     * byte[]转string
     *
     * @param src
     * @return
     */
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

    class ViewHolder {
        @Bind(R.id.img_shebei)
        ImageView imgShebei;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.shebei_state)
        TextView shebeiState;
        @Bind(R.id.name_main)
        LinearLayout nameMain;
        @Bind(R.id.mac)
        TextView mac;
        @Bind(R.id.shidu)
        TextView shidu;
        @Bind(R.id.right_img)
        ImageView rightImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
