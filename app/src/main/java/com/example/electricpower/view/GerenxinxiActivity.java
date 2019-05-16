package com.example.electricpower.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.maple.msdialog.ActionSheetDialog;

import butterknife.Bind;

public class GerenxinxiActivity extends BaseActivity implements View.OnClickListener {
    int x = R.layout.activity_gerenxinxi;
    @Bind(R.id.touxiang_tv)
    TextView touxiangTv;
    @Bind(R.id.touxiang_img)
    SimpleDraweeView touxiangImg;
    @Bind(R.id.right_img)
    ImageView rightImg;
    @Bind(R.id.touxiang)
    RelativeLayout touxiang;
    @Bind(R.id.nicheng_tv)
    TextView nichengTv;
    @Bind(R.id.right1_img)
    ImageView right1Img;
    @Bind(R.id.zhanghao_tv)
    TextView zhanghaoTv;
    @Bind(R.id.tuichudenglu_bt)
    Button tuichudengluBt;
    @Bind(R.id.nicheng)
    RelativeLayout nicheng;
    @Bind(R.id.xiugaimima)
    RelativeLayout xiugaimima;
    @Bind(R.id.back_img)
    ImageView backImg;

    @Override
    public void bindListener() {
        touxiang.setOnClickListener(this);
        nicheng.setOnClickListener(this);
        xiugaimima.setOnClickListener(this);
        backImg.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gerenxinxi;
    }

    public void showDialog() {
//        ActionSheetDialog dialog = new ActionSheetDialog.ActionSheetBuilder(mContext, R.style.Theme_AppCompat_Dialog)
//                .setItems(new CharSequence[]{"拍照", "从相册选择"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        showToast(which + "");
//                    }
//                })
//                .setCancelable(true)
//                .create();
//        dialog.show();

        new ActionSheetDialog(mContext)
                .addSheetItem("拍照", getResources().getColor(R.color.brown), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent intent=new Intent(mContext,GuanyuActivity.class);
                        startActivity(intent);
                    }
                })
                .addSheetItem("从相册中选择", getResources().getColor(R.color.brown), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        showToast("item"+which);
                    }
                })
                .setCancelText("取消",getResources().getColor(R.color.brown),18,false)
                .setCanceledOnTouchOutside(true)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.touxiang: {
//                new BottomMenuFragment(Text02.this)
//                        .addMenuItems(new MenuItem("拍照", MenuItem.MenuItemStyle.COMMON))
//                        .addMenuItems(new MenuItem("从相册选择", MenuItem.MenuItemStyle.COMMON))
//                        .show();
                showDialog();
                break;
            }
            case R.id.nicheng: {
                Intent intent=new Intent(mContext,NichengActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.xiugaimima: {
                Intent intent = new Intent(mContext, XiugaimimaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.back_img:{
                finish();
                break;
            }

        }
    }

}
