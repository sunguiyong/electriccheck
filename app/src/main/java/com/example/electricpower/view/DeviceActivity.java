package com.example.electricpower.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeviceActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_fujinshebeiliebiaoback;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.devicename_tv)
    TextView devicenameTv;
    @Bind(R.id.lishijilu_tv)
    TextView lishijiluTv;
    @Bind(R.id.searchstate_tv)
    TextView searchstateTv;
    @Bind(R.id.list_fujinshebeiliebiao)
    ListView listFujinshebeiliebiao;


    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fujinshebeiliebiaoback;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:{
                finish();
                break;
            }
        }
    }

    private void getData(){

    }
}
