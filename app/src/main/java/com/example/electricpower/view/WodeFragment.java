package com.example.electricpower.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.electricpower.BaseFragment;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.SaveData;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WodeFragment extends BaseFragment implements View.OnClickListener {
    int x = R.layout.activity_wode;
    @Bind(R.id.mylogin)
    TextView mylogin;
    @Bind(R.id.geren)
    LinearLayout geren;
    @Bind(R.id.wenti_img)
    ImageView wentiImg;
    @Bind(R.id.changjianwenti_c)
    RelativeLayout changjianwentiC;
    @Bind(R.id.yijianfankui)
    ImageView yijianfankui;
    @Bind(R.id.yijianfankui_c)
    RelativeLayout yijianfankuiC;
    @Bind(R.id.guanyu)
    ImageView guanyu;
    @Bind(R.id.guanyu_c)
    RelativeLayout guanyuC;


    @Override
    public void bindListener() {
        changjianwentiC.setOnClickListener(this);
        yijianfankuiC.setOnClickListener(this);
        guanyuC.setOnClickListener(this);
        geren.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mylogin.setText(SaveData.nick);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geren: {
                Intent intent = new Intent(mContext, GerenxinxiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.changjianwenti_c: {
                Intent intent = new Intent(mContext, ChangjianwentiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.yijianfankui_c: {
                Intent intent = new Intent(mContext, YijianfankuiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.guanyu_c: {
                Intent intent = new Intent(mContext, GuanyuActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
