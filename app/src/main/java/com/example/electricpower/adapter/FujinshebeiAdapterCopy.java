package com.example.electricpower.adapter;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.clj.fastble.data.BleDevice;
import com.example.electricpower.AppApplication;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.DeviceInfo;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.devicenickname.DeviceNickReceived;
import com.example.electricpower.entity.to.devicenickname.PostMac;
import com.example.electricpower.entity.to.devicenickname.UpdateNickPost;
import com.example.electricpower.entity.to.devicenickname.UpdateNickReceived;
import com.example.electricpower.entity.to.nick.NickReceived;
import com.example.electricpower.net.http.ATTJsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigInteger;
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
    private String address;
    private ArrayList<Double> saveData;
    private Context mContext;


    public ArrayList<Double> getSaveData() {
        return saveData;
    }

    public void setSaveData(ArrayList<Double> saveData) {
        this.saveData = saveData;
    }

    public ArrayList<String> getScanRecords() {
        return scanRecords;
    }

    public void setScanRecords(ArrayList<String> scanRecords) {
        this.scanRecords = scanRecords;
    }

    public FujinshebeiAdapterCopy(Context context) {
        this.listNick = new ArrayList<>();
        mLeDevices = new ArrayList<BluetoothDevice>();
        RSSIs = new ArrayList<Integer>();
        scanRecords = new ArrayList<String>();
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        //存储别名，用于下个页面取值
        SaveData.listName=listNick;
    }


    public FujinshebeiAdapterCopy(Context context, int resourceId, List<BleDevice> list) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
    }

    List<String> listNick;
    private String nickName = null;

    public void addDevice(BluetoothDevice device, int RSSI, String scanRecord) {
        Log.d("信号", RSSI + "" + "----" + device.getName() + scanRecord);
        //如果不包含在内就放集合
        if (!mLeDevices.contains(device)) {
            // if (device.getName() != null && device.getName() != "" && device.getName().startsWith("YG")) {
            getData(device);
            this.mLeDevices.add(device);
            this.RSSIs.add(RSSI);
            this.scanRecords.add(scanRecord);
            // }
        } else {
            //循环判断mac地址是否相等，相等则放入相应的集合中
            for (int i = 0; i < mLeDevices.size(); i++) {
                BluetoothDevice d = mLeDevices.get(i);
                if (device.getAddress().equals(d.getAddress())) {
                    RSSIs.set(i, RSSI);
                    scanRecords.set(i, scanRecord);
                }
            }
        }
    }

    public BluetoothDevice getDevice(int position) {
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_fujinshebei, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String name = mLeDevices.get(position).getName();

        //蓝牙mac地址处理
        address = mLeDevices.get(position).getAddress();
        DeviceInfo.mac = address;
        String address1 = address.replaceAll(":", "");
        final String addressLast = address1.substring(0, 6);

//        viewHolder.name.setText("YG-" + addressLast);

        if (listNick.size() == getCount()) {
            viewHolder.name.setText(listNick.get(position));
            DeviceInfo.deviceName = "YG-" + addressLast;
            Log.d("适配器NAME", mLeDevices.get(position).getName() + "===" + mLeDevices.get(position).getAddress());

            viewHolder.shebeiState.setText(RSSIs.get(position).toString() + "");

            //获取到蓝牙广播数据进行处理，得到温湿度
            String temp = scanRecords.get(position).toString();
            Log.d("positionMy", position + "");
            String check = temp.substring(22, 23);
            Log.d("checkMy", check);
            String wendu16 = temp.substring(22, 26);
            String shidu16 = temp.substring(26, 30);

            //转16进制
            String wendu = new BigInteger(wendu16, 16).toString();
            String shidu = new BigInteger(shidu16, 16).toString();
            double wenduLast = hexToInt(wendu16) / 100d;
            double shiduLast = hexToInt(shidu16) / 100d;
            Log.d("温湿度", wenduLast + "----" + shiduLast);
            viewHolder.shidu.setText("湿度：" + shiduLast + "%");
            viewHolder.mac.setText("温度：" + wenduLast + "℃");
        }

        viewHolder.imgShebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("点击了：", position + "");
                final EditText editText = new EditText(mContext);

                new AlertDialog.Builder(mContext)
                        .setView(editText)
                        .setTitle("修改别名：")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editName = editText.getText().toString();
                                if (editName.isEmpty()) {
                                    Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
                                } else {
                                    String mac = changeMac(mLeDevices.get(position).getAddress());
                                    listNick.set(position, editName);
                                    Log.d("点击后的mac", mac);
                                    updateName(mac);
                                    viewHolder.name.setText(editName);
                                }
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });

        return convertView;
    }

    private String editName;

    public void checkScanResult(ArrayList<String> scanRecords, int position) {
        //获取到蓝牙广播数据进行处理
        String bleResult = scanRecords.get(position).toString();
        Log.d("positionMy", position + "");
        String check = bleResult.substring(22, 23);
        Log.d("checkMy", check);
        String wendu16 = bleResult.substring(22, 26);
        String shidu16 = bleResult.substring(26, 30);

        //转16进制
        String wendu = new BigInteger(wendu16, 16).toString();
        String shidu = new BigInteger(shidu16, 16).toString();

        double wenduLast = hexToInt(wendu16) / 100d;
        double shiduLast = hexToInt(shidu16) / 100d;
        //存储温湿度
        DeviceInfo.temperature = wenduLast;
        DeviceInfo.humidity = shiduLast;
    }

    public double hexToInt(String hexString) {
        String flag = hexString.substring(0, 1);
        if ("F".equals(flag)) {
            BigInteger bi = new BigInteger("FFFF" + hexString, 16);
            Integer a = bi.intValue();
            Double value = Double.parseDouble(a.toString());
            return value;
        } else {
            Integer tmp = Integer.parseInt(hexString, 16);
            Double value = Double.parseDouble(tmp.toString());
            return value;
        }
    }

    public void clear() {
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
        @Bind(R.id.data_main)
        LinearLayout dataMain;
        @Bind(R.id.itemmain)
        RelativeLayout itemMain;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 获取设备名
     *
     * @param device
     */
    private void getData(final BluetoothDevice device) {
        //获取mac地址用于请求接口
        String mac = changeMac(device.getAddress());
        PostMac postMac = new PostMac(mac);
        Gson gson = new Gson();
        String str = gson.toJson(postMac);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();

        getDataFromServer(Request.Method.POST, SaveData.mainUrl + "bluetooth/selectDevice", jsonObject, DeviceNickReceived.class, new Response.Listener<DeviceNickReceived>() {
            @Override
            public void onResponse(DeviceNickReceived response) {
                Log.d("设备别名", "成功");
                if (response.getResult() != null) {
                    nickName = response.getResult().getDeviceName();
                    listNick.add(nickName);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("设备别名", "失败");

            }
        });
    }

    private int isUpdateSuccess = 0;

    /**
     * 更新设备别名
     *
     * @param mac
     */
    private void updateName(String mac) {
        Gson gson = new Gson();
        UpdateNickPost updateNickPost = new UpdateNickPost();
        updateNickPost.setDeviceName(editName);
        updateNickPost.setMac(mac);
        String str = gson.toJson(updateNickPost);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        getDataFromServer(Request.Method.POST, SaveData.mainUrl + "bluetooth/updateName", jsonObject, UpdateNickReceived.class, new Response.Listener<UpdateNickReceived>() {

            @Override
            public void onResponse(UpdateNickReceived response) {
                if ("success".equals(response.getMessage())) {
                    Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("改名请求失败", "---");
            }
        });
    }

    public <T> void getDataFromServer(int method, String url, JsonObject jsonObject, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(url, clazz, jsonObject, listener, errorListener);
        executeRequest(qBaoJsonRequest);
    }

    protected void executeRequest(Request<?> request) {
        request.setTag(this);
        AppApplication.getInstance().getRequestQueue().add(request);
    }

    //
    public String changeMac(String mac) {
        String mac1 = mac.replace(":", "");
        String mac2 = mac1.substring(0, 6);
        String mac3 = mac1.substring(6, 12);
        String s = mac3 + mac2;
        String s1 = s.substring(6, 8);
        String s2 = s.substring(10, 12);
        StringBuilder sb = new StringBuilder(s);
        sb.replace(6, 8, s2);
        sb.replace(10, 12, s1);
        return sb + "";
    }
}
