package com.example.electricpower.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;

import butterknife.Bind;

public class ChangjianwentiActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_changjianwenti;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.right_img1)
    ImageView rightImg1;
    @Bind(R.id.wenti1)
    RelativeLayout wenti1;
    @Bind(R.id.right_img2)
    ImageView rightImg2;
    @Bind(R.id.wenti2)
    RelativeLayout wenti2;
    @Bind(R.id.right_img3)
    ImageView rightImg3;
    @Bind(R.id.wenti3)
    RelativeLayout wenti3;
    @Bind(R.id.t1)
    TextView t1;
    @Bind(R.id.t2)
    TextView t2;
    @Bind(R.id.t3)
    TextView t3;

    boolean choose1 = true;
    boolean choose2 = true;
    boolean choose3 = true;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.wenti1: {
                if (choose1) {
                    rightImg1.setImageResource(R.drawable.arrow_up);
                    t1.setVisibility(View.VISIBLE);
                    choose1 = false;
                } else {
                    rightImg1.setImageResource(R.drawable.arrow_right);
                    t1.setVisibility(View.GONE);
                    choose1 = true;
                }
                break;
            }
            case R.id.wenti2: {
                if (choose2) {
                    rightImg2.setImageResource(R.drawable.arrow_up);
                    t2.setVisibility(View.VISIBLE);
                    choose2 = false;
                } else {
                    rightImg2.setImageResource(R.drawable.arrow_right);
                    t2.setVisibility(View.GONE);
                    choose2 = true;
                }
                break;
            }
            case R.id.wenti3: {
                if (choose3) {
                    rightImg3.setImageResource(R.drawable.arrow_up);
                    t3.setVisibility(View.VISIBLE);
                    choose3 =false;
                }else {
                    rightImg3.setImageResource(R.drawable.arrow_right);
                    t3.setVisibility(View.GONE);
                    choose3=true;
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void bindListener() {
        wenti1.setOnClickListener(this);
        wenti2.setOnClickListener(this);
        wenti3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_changjianwenti;
    }
}
