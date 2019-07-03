package com.example.electricpower.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricpower.R;
import com.example.electricpower.entity.to.DeviceInfo;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.deviceall.DeviceGet;
import com.example.electricpower.view.DeviceInfoActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeviceAdapter extends ArrayAdapter<DeviceGet.ResultBean> {
    int x = R.layout.item_deviceall;
    private String urlToday;
    private int resourId;

    public DeviceAdapter(Context context, int resource, List<DeviceGet.ResultBean> objects) {
        super(context, resource, objects);
        resourId = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DeviceGet.ResultBean resultBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourId, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(resultBean.getDeviceName() + "");
        viewHolder.shebeiState.setText(resultBean.getALiYunDeviceState() + "");
        if (resultBean.getStoreData().get(0) != null) {
            viewHolder.shiduTv.setText("湿度：" + (double) Math.round((resultBean.getStoreData().get(0).getNewData()) * 100) / 100 + "");
        } else {
            viewHolder.shiduTv.setText("--");
        }

        if (resultBean.getStoreData().get(1) != null) {
            viewHolder.wenduTv.setText("温度：" + (double) Math.round((resultBean.getStoreData().get(1).getNewData()) * 100) / 100 + "");

        } else {
            viewHolder.wenduTv.setText("--");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DeviceInfoActivity.class);
                //给下个页面使用的值
                intent.putExtra("devicename", resultBean.getDeviceName());
                intent.putExtra("deviceid", resultBean.getDeviceId());

                if (resultBean.getStoreData().get(0) != null && resultBean.getStoreData().get(1) != null) {
                    //湿度
                    DeviceInfo.humidity = (double) Math.round((resultBean.getStoreData().get(0).getNewData()) * 100) / 100;
                    intent.putExtra("humidity_nodeid", resultBean.getStoreData().get(0).getNodeId() + "");
                    //温度
                    DeviceInfo.temperature = (double) Math.round((resultBean.getStoreData().get(1).getNewData()) * 100) / 100;
                    intent.putExtra("temperature_nodeid", resultBean.getStoreData().get(1).getNodeId() + "");

                    Log.d("humidityTem_nodeid", resultBean.getStoreData().get(0).getNodeId() + "---" + resultBean.getStoreData().get(1).getNodeId() + "");
                    intent.putExtra("datetime", resultBean.getStoreData().get(0).getDateStr());
                    Log.d("datetime", resultBean.getStoreData().get(0).getDateStr() + "");
                    String dateStr = resultBean.getStoreData().get(0).getDateStr();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date date = simpleDateFormat.parse(dateStr);
                        long time = date.getTime();


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    DeviceInfo.humidity = 0;
                    DeviceInfo.temperature = 0;
                    Toast.makeText(getContext(), "当前无温湿度数据", Toast.LENGTH_SHORT).show();
                }
                SaveData.url = urlToday = "http://192.168.8.30:9981/api/device/historyData?"
                        + "deviceNodeIdW="
                        + resultBean.getNodeList().get(1).getSdevNodeid() + ""
                        + "&deviceNodeIdS="
                        + resultBean.getNodeList().get(0).getSdevNodeid() + ""
                        + "&dateTime="
//                                + time + ""
//                        + "1561302000000"
                        + System.currentTimeMillis() + ""
                        + "&deviceId="
                        + resultBean.getNodeList().get(0).getSdevId() + "";
                Log.d("urlTest", urlToday);
                SaveData.baseUrl = "http://192.168.8.30:9981/api/device/historyData?"
                        + "deviceNodeIdW="
                        + resultBean.getNodeList().get(1).getSdevNodeid() + ""
                        + "&deviceNodeIdS="
                        + resultBean.getNodeList().get(0).getSdevNodeid() + ""
                        + "&deviceId="
                        + resultBean.getNodeList().get(0).getSdevId() + "";
                getContext().startActivity(intent);

            }
        });
        return view;
    }


    static
    class ViewHolder {
        @Bind(R.id.img_shebei)
        ImageView imgShebei;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.shebei_state)
        TextView shebeiState;
        @Bind(R.id.name_main)
        LinearLayout nameMain;
        @Bind(R.id.wendu_tv)
        TextView wenduTv;
        @Bind(R.id.shidu_tv)
        TextView shiduTv;
        @Bind(R.id.right_img)
        ImageView rightImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
