package com.example.electricpower.view;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;

public class LaunchActivity extends BaseActivity {
    Integer time = 2000;    //设置等待时间，单位为毫秒
    Handler handler = new Handler();

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {
        LinearLayout linearLayout =(LinearLayout)findViewById(R.id.linear);

        linearLayout.setSystemUiVisibility(View.INVISIBLE);  //设置不可见

//当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                LaunchActivity.this.finish();
            }
        }, time);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }
}
