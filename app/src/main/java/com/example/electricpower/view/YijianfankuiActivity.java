package com.example.electricpower.view;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YijianfankuiActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_yijianfankui;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.content_et)
    EditText contentEt;
    @Bind(R.id.count_tv)
    TextView countTv;
    @Bind(R.id.tijiao_bt)
    Button tijiaoBt;

    @Override
    public void bindListener() {
        tijiaoBt.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yijianfankui;
    }

    @Override
    public void onClick(View v) {

    }


}
