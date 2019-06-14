package com.example.electricpower.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.feedback.FeedBackPost;
import com.example.electricpower.entity.to.feedback.FeedBackReceived;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

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
    @Bind(R.id.phoneormail_et)
    EditText phoneormailEt;
    private String url = "http://192.168.8.30:9981/api/feedback/addFeedback";

    @Override
    public void bindListener() {
        tijiaoBt.setOnClickListener(this);
        backImg.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.tijiao_bt: {
                if (contentEt.getText()==null||contentEt.getText().toString()==""&&phoneormailEt.getText().toString()==""){
                    showToast("内容不能为空哦！");
                }else {
                    getData();
                }
                break;
            }
        }
    }

    private void getData() {
        Gson gson = new Gson();
        FeedBackPost feedBackPost = new FeedBackPost();
        feedBackPost.setInfo(contentEt.getText().toString());
        feedBackPost.setMobile(phoneormailEt.getText().toString());
        String jsonStr=gson.toJson(feedBackPost);
        JsonObject jsonObject=new JsonParser().parse(jsonStr).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, FeedBackReceived.class, new Response.Listener<FeedBackReceived>() {
            @Override
            public void onResponse(FeedBackReceived response) {
                if (response.getStatus()==200){
                    Log.d("反馈成功","success");
                    showToast("反馈成功！");
                    contentEt.setText(null);
                    phoneormailEt.setText(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("反馈请求","失败");
            }
        });
    }

}
