package com.example.electricpower.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.utils.dialog.photo.apputil.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuanyuActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_guanyu;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.version_tv)
    TextView versionTv;

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
    }

    @Override
    public void initData() {
        versionTv.setText("版本号：" + AppUtils.getVersionName(mContext));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guanyu;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
        }
    }
}
