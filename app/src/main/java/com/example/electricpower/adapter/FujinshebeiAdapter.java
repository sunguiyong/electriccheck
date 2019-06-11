package com.example.electricpower.adapter;

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
import com.example.electricpower.view.ChartTest;

import java.text.NumberFormat;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FujinshebeiAdapter extends BaseAdapter {
    int x = R.layout.item_fujinshebei;
    private Context context;
    private List<BleDevice> list;
    int resourceId;

    public FujinshebeiAdapter(Context context, int resourceId, List<BleDevice> list) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public BleDevice getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fujinshebei, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BleDevice device = getItem(position);
        String dn = device.getDevice().getName();
        if (device.getDevice().getName() == null || device.getDevice().getName() == "") {
            dn = "未知设备名";
        }

        NumberFormat ddf1=NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(1);
//        System.out.println("ddf1="+ddf1.format(749.1666666666666));
        //获取蓝牙广播的携带广告值，进行转换
        String wenshiduInit = bytesToHexString(device.getScanRecord());
        String wendu = wenshiduInit.substring(22, 26);
        String shidu = wenshiduInit.substring(26, 30);
//        String wenduD=ddf1.format(Double.parseDouble(wendu));
//        String shiduD=ddf1.format(Double.parseDouble(shidu));
        double wenduInt = (double)Integer.parseInt(wendu, 16) / 100d;
        double shiduInt = (double)Integer.parseInt(shidu, 16) / 100d;

        wenduInt=(double)Math.round(wenduInt*100)/100.0;
//        Log.d("温湿度：",wenduInt+"---"+shiduInt);

        viewHolder.name.setText(dn);
        viewHolder.mac.setText(device.getDevice().getAddress());
        viewHolder.shebeiState.setText(device.getRssi() + "");
        viewHolder.shidu.setText("温湿度：" + wenduInt + "℃" + "，" + shiduInt + "" + "%");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChartTest.class);
                Log.d("haha",list.get(position).getName());
                intent.putExtra("name",list.get(position).getName());
                context.startActivity(intent);
            }
        });
        return convertView;
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
