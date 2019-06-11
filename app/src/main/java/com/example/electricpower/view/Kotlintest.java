package com.example.electricpower.view;

import android.view.View;

import com.example.electricpower.BaseActivity;

import java.util.Timer;

public class Kotlintest extends BaseActivity implements View.OnClickListener{
    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:{
                showToast("哈哈");
                break;
            }
            default:break;
        }

    }
    void test(){

    }
}
