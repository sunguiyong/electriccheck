package com.example.electricpower.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.changepsw.ChangepswPost;
import com.example.electricpower.entity.to.changepsw.ChangepswReceived;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XiugaimimaActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_xiugaimima;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.oldpsw_et)
    EditText oldpswEt;
    @Bind(R.id.newpsw_et)
    EditText newpswEt;
    @Bind(R.id.myswitch)
    Switch myswitch;
    @Bind(R.id.define)
    Button define;

    private String url="http://118.31.229.187:9981/api/manager/updatePassword";
    @Override
    public void bindListener() {
        define.setOnClickListener(this);
        backImg.setOnClickListener(this);
        //switch开关监听
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    oldpswEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpswEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    oldpswEt.setSelection(oldpswEt.getText().length());
                    newpswEt.setSelection(newpswEt.getText().length());
                }else {
                    oldpswEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpswEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    oldpswEt.setSelection(oldpswEt.getText().length());
                    newpswEt.setSelection(newpswEt.getText().length());
                }
            }
        });
    }

    @Override
    public void initData() {
        myswitch.setChecked(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_xiugaimima;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:{
                finish();
                break;
            }
            case R.id.define:{
                getData();
                break;
            }
        }
    }

    private void getData(){
        ChangepswPost changepswPost=new ChangepswPost();
        changepswPost.setOldpassword(oldpswEt.getText().toString());
        changepswPost.setNewpassword(newpswEt.getText().toString());
        Gson gson=new Gson();
        String str=gson.toJson(changepswPost);
        JsonObject jsonObject=new JsonParser().parse(str).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, ChangepswReceived.class, new Response.Listener<ChangepswReceived>() {
            @Override
            public void onResponse(ChangepswReceived response) {
                if (response.getStatus()==200){
                    showToast("修改密码成功，请重新登录！");
                    finishAllActivity();
                    Intent intent=new Intent(XiugaimimaActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    showToast("修改密码失败！");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
