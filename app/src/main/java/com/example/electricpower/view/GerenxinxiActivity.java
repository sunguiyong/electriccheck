package com.example.electricpower.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.personalinfo.PersonalInfoReceived;
import com.example.electricpower.net.http.ResponseListener;
import com.example.electricpower.utils.dialog.photo.FileUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.maple.msdialog.ActionSheetDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * using
 */
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
    public static final int RC_CHOOSE_PHOTO = 2;
    public static final int RC_TAKE_PHOTO = 1;
    @Bind(R.id.zhanghao)
    TextView zhanghao;
    @Bind(R.id.nick)
    TextView nick;
    private String imgUri = "http://www.desktx.com/d/file/wallpaper/meishi/20170711/a4342d8eec03e0fa463f00b4f1bbfa39.jpg";
    private String url = "http://192.168.8.30:9981/api/manager/myMessage";
    private String uploadImg = "http://192.168.8.30:9981/api/upload/avatar";

    @Override
    public void bindListener() {
        touxiang.setOnClickListener(this);
        nicheng.setOnClickListener(this);
        xiugaimima.setOnClickListener(this);
        backImg.setOnClickListener(this);
        tuichudengluBt.setOnClickListener(this);
    }

    @Override
    public void initData() {
//        Uri uri=Uri.parse(imgUri);
//        touxiangImg.setImageURI(uri);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
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
                        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            //未授权，申请授权(拍照)
                            ActivityCompat.requestPermissions(GerenxinxiActivity.this, new String[]{Manifest.permission.CAMERA}, RC_TAKE_PHOTO);
                        } else {
                            //已授权，获取照片
                            takePhoto();
                        }
                    }
                })
                .addSheetItem("从相册中选择", getResources().getColor(R.color.brown), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
                            ActivityCompat.requestPermissions(GerenxinxiActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
                        } else {
                            //已授权，获取照片
                            choosePhoto();
                        }
                    }
                })
                .setCancelText("取消", getResources().getColor(R.color.brown), 18, false)
                .setCanceledOnTouchOutside(true)
                .setCancelable(true)
                .show();
    }

    /**
     * 跳转系统相册获取图片
     */
    private void choosePhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CHOOSE_PHOTO: {
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    String filePath = FileUtil.getFilePathByUri(this, uri);
                    if (!TextUtils.isEmpty(filePath)) {
                        Log.d("Lujing--", filePath);
                        touxiangImg.setImageURI("file://" + filePath);
                        try {
                            FileInputStream in = new FileInputStream("file://" + filePath);
                            Bitmap bitmap= BitmapFactory.decodeStream(in);
                            Bitmap bm=BitmapFactory.decodeFile("file://" + filePath);
                            //TODO
                            uploadImg(uploadImg, bm, new ResponseListener() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("上传", "成功！");
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("上传", "失败！");

                                }
                            });
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            }
            case RC_TAKE_PHOTO: {

                touxiangImg.setImageURI(imageUri);
                break;
            }
        }
    }

    /**
     * 权限申请结果回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RC_TAKE_PHOTO:   //拍照权限申请返回
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                }
                break;
            case RC_CHOOSE_PHOTO:   //相册选择照片权限申请返回
                choosePhoto();
                break;
        }
    }

    private Uri imageUri;

    private void takePhoto() {
        File outputImage = new File(getExternalCacheDir(), "outputImage.jpg");
        try {
            if (outputImage.exists()) {
                Log.d("outputImage", "delete");
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(GerenxinxiActivity.this, "com.rachel.studyapp.fileprovider", outputImage);
            Log.d("imageUri", "Build.VERSION.SDK_INT >= 24");

        } else {
            imageUri = Uri.fromFile(outputImage);
            Log.d("imageUri", "Build.VERSION.SDK_INT < 24");

        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, RC_TAKE_PHOTO);
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
            case R.id.tuichudenglu_bt: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("确定退出吗？");

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();

                break;
            }

        }
    }

    private CameraDevice cameraDevice;
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            GerenxinxiActivity.this.cameraDevice = camera;
            Log.d("摄像头打开！", "");
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            Log.d("摄像头断开！", "");
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            Log.d("摄像头出错！", "");
        }
    };

    private void getData() {
        getDataFromServer(Request.Method.POST, url, PersonalInfoReceived.class, new Response.Listener<PersonalInfoReceived>() {
            @Override
            public void onResponse(PersonalInfoReceived response) {
                Log.d("个人信息请求", "成功");
                nick.setText(response.getResult().getNick());
                zhanghao.setText(response.getResult().getMobile());
                SaveData.nick = response.getResult().getNick();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("个人信息请求", "失败");
            }
        });
    }
}
