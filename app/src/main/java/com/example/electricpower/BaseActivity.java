package com.example.electricpower;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.electricpower.net.http.ATTJsonRequest;
import com.example.electricpower.net.http.FormImage;
import com.example.electricpower.net.http.PostUploadRequest;
import com.example.electricpower.net.http.ResponseListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by 孙
 */
public abstract class BaseActivity extends AppCompatActivity implements InitViews {
    protected String TAG;

    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName().toString();
        mContext = this;
        setContentView(getLayoutId());
        //添加activity到堆栈
        AppApplication.getInstance().addActivity(this);
//		AppManager.addActivity(this);

//		StatusBarUtil.darkMode(this);
        ButterKnife.bind(this);

//        startTimer();
        initData();
        bindListener();

    }

    @Override
    protected void onDestroy() {
        //将activity从堆栈中移除
        AppApplication.getInstance().getRequestQueue().cancelAll("VolleyRequest");
        AppApplication.getInstance().finishActivity(this);

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 是否在事件总线中注册
     *
     * @return 返回true则要在相应的activity中定义public void onEvent(SomeEvent event)方法,默认返回false
     */
    protected boolean registerEventBus() {
        return false;
    }


    /**
     * 自定义方法
     *
     * @param method
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     */
    public <T> void getDataFromServer(int method, String url, JsonObject jsonObject, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(url, clazz, jsonObject, listener, errorListener);
        executeRequest(qBaoJsonRequest);
    }

    public <T> void getDataFromServer(int method, String url, Class<T> clazz, Listener<T> listener,
                                      ErrorListener errorListener) {
        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener);
        Log.i("Http", "url:" + url);
        executeRequest(qBaoJsonRequest);
    }


    public <T> void getDataFromServer(int method, String url, HashMap<String, String> params,
                                      Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {

        ATTJsonRequest<T> qBaoJsonRequest = new ATTJsonRequest<T>(method, url, clazz, listener, errorListener);
        if (params != null) {
            qBaoJsonRequest.addParams(params);
        }
        executeRequest(qBaoJsonRequest);
    }

    public <T> void getDataFromServer(String url, JSONObject jsonRequest,
                                      Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        ATTJsonRequest<T> attJsonRequest = new ATTJsonRequest<T>(url, clazz, jsonRequest, listener, errorListener);
        executeRequest(attJsonRequest);
    }

    public void getImageFromServer(String url, Listener<Bitmap> listener, ErrorListener errorListener) {
        ImageRequest imageRequest = new ImageRequest(
                url, listener, 0, 0, Bitmap.Config.RGB_565, errorListener);
        executeRequest(imageRequest);
    }

    public void uploadImg(String url, Bitmap bitmap, ResponseListener listener) {
        List<FormImage> imageList = new ArrayList<FormImage>();
        imageList.add(new FormImage(bitmap));
        Request request = new PostUploadRequest(url, imageList, listener);
        executeRequest(request);
    }
    public void uploadImg(String url,String path,ResponseListener listener){
        List<FormImage> imageList = new ArrayList<FormImage>();
        imageList.add(new FormImage(path));
        Request request = new PostUploadRequest(url, imageList, listener);
        executeRequest(request);
    }

    protected void executeRequest(Request<?> request) {
        request.setTag(this);
        AppApplication.getInstance().getRequestQueue().add(request);
    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String msg, int time) {
        Toast.makeText(mContext, msg, time).show();
    }


    /**
     * session失效跳转到登录页面，在本页面的onActivityResult方法中处理
     * requestCode == Constants.REQUESTCODE_SESSION_OUTTIME这种情况
     */
    public void skipToLoginActivity() {
        showToast("您的账号已在其他设备上登录");
//        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
//        intent.putExtra("from", "session");
//        startActivity(intent);
//        AppApplication.getInstance().finishOtherActivity();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void setTranslucentStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void finishAllActivity() {
        AppApplication.getInstance().finishAllActivity();
    }

}