package com.example.electricpower.view;

import android.annotation.SuppressLint;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.hjm.bottomtabbar.BottomTabBar;
import java.util.HashMap;
import java.util.Map;
import butterknife.Bind;

public class test01 extends BaseActivity {
    @Bind(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    private String url="http://120.25.153.127:8086/dtuApi/api/device/getDevicePage";
    @Override
    public void bindListener() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initData() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(70,70)
                .setFontSize(16)
                .setTabPadding(30,6,20)
                .setChangeColor(getResources().getColor(R.color.green_near),getResources().getColor(R.color.gray_8f))
                .addTabItem("设备",R.drawable.icon_shebei_p,R.drawable.icon_shebei_n, ShebeiFragment.class)
                .addTabItem("我的",R.drawable.icon_me_p, R.drawable.icon_me_n,WodeFragment.class)
                .isShowDivider(false)
                .setDividerColor(getResources().getColor(R.color.gray))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.d("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                    }
                });




//        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://120.25.153.127:8086/dtuApi/api/device/getDevicePage", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("ok","ok");
//                Log.d("response",response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("lose","lose");
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                //Adding parameters to request
//                params.put("offset","1");
//                params.put("limit","1");
//
//                return params;
//            }
//        };

        //Adding the string request to the queue
//        executeRequest(stringRequest);


    }

    @Override
    public int getLayoutId() {
        return R.layout.tabbar_main;
    }
}
