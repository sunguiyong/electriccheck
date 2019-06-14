package com.example.electricpower.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.utils.dialog.photo.FileUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.maple.msdialog.ActionSheetDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;

public class GerenxinxiActivityCopy extends BaseActivity implements View.OnClickListener {
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
    public static final int RC_CHOOSE_PHOTO = 2;
    public static final int RC_TAKE_PHOTO = 1;
    private String imgUri = "http://www.desktx.com/d/file/wallpaper/meishi/20170711/a4342d8eec03e0fa463f00b4f1bbfa39.jpg";

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

    private Uri imageUri;

    public void showDialog() {
        new ActionSheetDialog(mContext)
                .addSheetItem("拍照", getResources().getColor(R.color.brown), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        takePhoto();
                    }
                })
                .addSheetItem("从相册中选择", getResources().getColor(R.color.brown), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                })
                .setCancelText("取消", getResources().getColor(R.color.brown), 18, false)
                .setCanceledOnTouchOutside(true)
                .setCancelable(true)
                .show();
    }

    public void takePhoto(){
        File outputImage=new File(getExternalCacheDir(), "outputImage.jpg");
        try {
            if (outputImage.exists()) {
                Log.d("outputImage","delete");
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(GerenxinxiActivityCopy.this, "com.rachel.studyapp.fileprovider", outputImage);
            Log.d("imageUri","Build.VERSION.SDK_INT >= 24");

        } else {
            imageUri = Uri.fromFile(outputImage);
            Log.d("imageUri","Build.VERSION.SDK_INT < 24");

        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, RC_TAKE_PHOTO);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","is invoked!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","is invoked!!");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_TAKE_PHOTO: {
                    touxiangImg.setImageURI(imageUri);
                    Log.d("setImageURI","is invoked!!");
                break;
            }
            default:break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.touxiang: {
                showDialog();
                break;
            }
            case R.id.nicheng: {
                Intent intent = new Intent(mContext, NichengActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.xiugaimima: {
                Intent intent = new Intent(mContext, XiugaimimaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.back_img: {
                finish();
                break;
            }

        }
    }
}
