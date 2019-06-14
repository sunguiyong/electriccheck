package com.example.electricpower.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.sendSmsInfo.ReceivedCode;
import com.example.electricpower.entity.to.sendSmsInfo.SendSms;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZhuceActivity extends BaseActivity implements View.OnClickListener {
    private String url = "http://192.168.8.30:9981/api/sms/sendSmsInfo";
    int x = R.layout.activity_zhuce;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.zhanghao_tv)
    EditText zhanghaoTv;
    @Bind(R.id.yanzhengma_tv)
    EditText yanzhengmaTv;
    @Bind(R.id.huoquyanzhengma_tv)
    TextView huoquyanzhengmaTv;
    @Bind(R.id.mima_tv)
    EditText mimaTv;
    @Bind(R.id.eye_imgbt)
    ImageView eyeImgbt;
    @Bind(R.id.zhuce_bt)
    Button zhuceBt;
    private boolean eye = false;
    private long time = 60000;
    private CountDownTimer timer = new CountDownTimer(time+100, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            huoquyanzhengmaTv.setClickable(false);
            huoquyanzhengmaTv.setText(millisUntilFinished / 1000 + "秒");
            Log.d("时间---", millisUntilFinished + "");
        }

        @Override
        public void onFinish() {
            huoquyanzhengmaTv.setClickable(true);
            huoquyanzhengmaTv.setText("获取验证码");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.huoquyanzhengma_tv: {
                if (isMobileNumber(zhanghaoTv.getText().toString())){
                    timer.start();
                }else {
                    showToast("请输入正确手机号！");
                }
                break;
            }
            //点击切换密码是否显示
            case R.id.eye_imgbt: {
                if (eye) {
                    mimaTv.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeImgbt.setImageResource(R.drawable.btn_eye_close);
                    eye = false;
                    //光标移动到最后
                    mimaTv.setSelection(mimaTv.getText().length());
                } else {
                    mimaTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeImgbt.setImageResource(R.drawable.btn_eye);
                    eye = true;
                    mimaTv.setSelection(mimaTv.getText().length());
                }
                break;
            }
            case R.id.zhuce_bt: {

                break;
            }
        }
    }

    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        eyeImgbt.setOnClickListener(this);
        zhuceBt.setOnClickListener(this);
        huoquyanzhengmaTv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuce;
    }

    private void getData() {
        /**
         * 请求数据，post请求手机号
         * 获取验证码
         */
        Gson gson = new Gson();
        SendSms sendSms = new SendSms();
        sendSms.setMobile("13775452153");
        String jsonStr = gson.toJson(sendSms);
        Log.d("jsonStr", jsonStr);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, ReceivedCode.class, new Response.Listener<ReceivedCode>() {
            @Override
            public void onResponse(ReceivedCode response) {
                Log.d("收到", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("失败", "");
            }
        });
    }

    /**
     * 验证手机号码是否合法
     * 176, 177, 178;
     * 180, 181, 182, 183, 184, 185, 186, 187, 188, 189;
     * 145, 147;
     * 130, 131, 132, 133, 134, 135, 136, 137, 138, 139;
     * 150, 151, 152, 153, 155, 156, 157, 158, 159;
     *
     * "13"代表前两位为数字13,
     * "[0-9]"代表第二位可以为0-9中的一个,
     * "[^4]" 代表除了4
     * "\\d{8}"代表后面是可以是0～9的数字, 有8位。
     */
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
//        String telRegex2 = "^\\d{11}$";//只做11位校验
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }
}
