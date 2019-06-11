package com.example.electricpower.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_login_main;
    @Bind(R.id.fujinshebei_tv)
    TextView fujinshebeiTv;
    @Bind(R.id.zhanghu_img)
    ImageView zhanghuImg;
    @Bind(R.id.zhanghao_tv)
    EditText zhanghaoTv;
    @Bind(R.id.mima_img)
    ImageView mimaImg;
    @Bind(R.id.mima_tv)
    EditText mimaTv;
    @Bind(R.id.eye_imgbt)
    ImageView eyeImgbt;
    @Bind(R.id.denglu_bt)
    Button dengluBt;
    @Bind(R.id.wangjimima_tv)
    TextView wangjimimaTv;
    @Bind(R.id.shuxian)
    TextView shuxian;
    @Bind(R.id.zhucezhanghao_tv)
    TextView zhucezhanghaoTv;


    private boolean eye = false;

    @Override
    public void bindListener() {
        dengluBt.setOnClickListener(this);
        eyeImgbt.setOnClickListener(this);
        fujinshebeiTv.setOnClickListener(this);
        wangjimimaTv.setOnClickListener(this);
        zhucezhanghaoTv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        fujinshebeiTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eye_imgbt: {
                if (eye) {
                    mimaTv.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeImgbt.setImageResource(R.drawable.btn_eye_close);
                    eye = false;
                    mimaTv.setSelection(mimaTv.getText().length());
                } else {
                    mimaTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeImgbt.setImageResource(R.drawable.btn_eye);
                    eye = true;
                    mimaTv.setSelection(mimaTv.getText().length());
                }
                break;
            }
            case R.id.denglu_bt: {
                Intent intent = new Intent(mContext, test01.class);
                startActivity(intent);
                break;
            }
            case R.id.fujinshebei_tv: {
                Intent intent = new Intent(mContext, FujinshebeilibiaoActivityCopy.class);
                startActivity(intent);
                break;
            }
            case R.id.wangjimima_tv:
            {
                Intent intent=new Intent(mContext,WangjimimaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.zhucezhanghao_tv:
            {
                Intent intent=new Intent(mContext,ZhuceActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
