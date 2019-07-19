package com.example.electricpower.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.TokenSave;
import com.example.electricpower.entity.to.login.LoginPost;
import com.example.electricpower.entity.to.login.LoginReceived;
import com.example.electricpower.utils.dialog.photo.apputil.AppUtils;
import com.example.electricpower.utils.dialog.photo.permissions.PermissionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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


    String url = "http://118.31.229.187:9981/api/manager/login";
    @Bind(R.id.version_tv)
    TextView versionTv;
    private boolean eye = false;

    @Override
    public void bindListener() {
        dengluBt.setOnClickListener(this);
        eyeImgbt.setOnClickListener(this);
        fujinshebeiTv.setOnClickListener(this);
        wangjimimaTv.setOnClickListener(this);
        zhucezhanghaoTv.setOnClickListener(this);
    }

    private PermissionUtil permissionUtil;
    private final int REQUEST_CODE = 2;//请求码

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("request", "success");
                } else {
                    Log.i("request", "failed");
                }

                return;
            }
        }
    }

    @Override
    public void initData() {
        versionTv.setText("版本号：" + AppUtils.getVersionName(mContext));
        fujinshebeiTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);


//        if (permissionUtil.isOwnPermisson(this, Manifest.permission.INTERNET)) {
//            //如果已经拥有改权限
//            Log.i("request","own");
//        } else {
//            //没有改权限，需要进行请求
//            permissionUtil.requestPermission(this, Manifest.permission.INTERNET, REQUEST_CODE);
//        }
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
                getData();
                break;
            }
            case R.id.fujinshebei_tv: {
                Intent intent = new Intent(mContext, FujinshebeilibiaoActivityCopy.class);
                startActivity(intent);
                break;
            }
            case R.id.wangjimima_tv: {
                Intent intent = new Intent(mContext, WangjimimaActivity.class);
//                Intent intent=new Intent(mContext,ShebeiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.zhucezhanghao_tv: {
                Intent intent = new Intent(mContext, ZhuceActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    private void getData() {
        Gson gson = new Gson();
        LoginPost loginPost = new LoginPost();
        loginPost.setMobile(zhanghaoTv.getText().toString());
        loginPost.setPassword(mimaTv.getText().toString());
        String jsonStr = gson.toJson(loginPost);
        Log.d("登录json", jsonStr);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        getDataFromServer(Request.Method.POST, url, jsonObject, LoginReceived.class, new Response.Listener<LoginReceived>() {
            @Override
            public void onResponse(LoginReceived response) {
                Log.d("请求", "成功");
                if (response.getStatus() == 200) {
                    TokenSave.token = response.getResult().getToken();
                    SaveData.userName = zhanghaoTv.getText().toString();
                    SaveData.nick = response.getResult().getNick();
                    Intent intent = new Intent(mContext, test01.class);
                    startActivity(intent);
                } else {
                    showToast(response.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("请求", "失败");
                error.printStackTrace();
            }
        });
    }
}
