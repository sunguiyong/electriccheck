package com.example.electricpower.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.login.LoginReceived;
import com.example.electricpower.entity.to.nick.NickPost;
import com.example.electricpower.entity.to.nick.NickReceived;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NichengActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_nicheng;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.nicheng)
    EditText nicheng;
    @Bind(R.id.define)
    TextView define;
    private String url="http://192.168.8.30:9981/api/manager/updateNick";
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.define: {
                getData();
                break;
            }
        }
    }


    @Override
    public void bindListener() {
        backImg.setOnClickListener(this);
        define.setOnClickListener(this);
    }

    @Override
    public void initData() {
        nicheng.setHint(SaveData.nick);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nicheng;
    }

    private void getData() {
        NickPost nickPost=new NickPost();
        nickPost.setNick(nicheng.getText().toString());
        Gson gson=new Gson();
        String jsonStr=gson.toJson(nickPost);
        JsonObject jsonObject=new JsonParser().parse(jsonStr).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, NickReceived.class, new Response.Listener<NickReceived>() {
            @Override
            public void onResponse(NickReceived response) {
                if (response.getStatus()==200){
                    finish();
                    showToast("修改成功！");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
