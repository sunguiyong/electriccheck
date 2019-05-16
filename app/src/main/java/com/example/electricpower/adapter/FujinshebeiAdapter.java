package com.example.electricpower.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.electricpower.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FujinshebeiAdapter extends BaseAdapter {
    int x = R.layout.item_fujinshebei;
    private Context context;
    private List<BluetoothDevice> list;
    int resourceId;

    public FujinshebeiAdapter(Context context, int resourceId, List<BluetoothDevice> list) {
        this.context = context;
        this.list = list;
        this.resourceId = resourceId;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fujinshebei, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BluetoothDevice device = list.get(position);
        String dn = device.getName();
        if (device.getName() == null || device.getName() == "") {
            dn = "未知设备名";
        }
        viewHolder.name.setText(dn);
        viewHolder.mac.setText(device.getAddress());
        viewHolder.shebeiState.setText(device.getBondState()+"");
        viewHolder.shidu.setText("湿度："+device.getType()+"");

        return convertView;
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
