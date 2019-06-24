package com.example.electricpower.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.electricpower.R;
import com.example.electricpower.entity.to.DeviceInfo;

public class MyView1 extends View {
    private Path path1 = new Path();
    //传进来的温度
    private float temperature = (float) DeviceInfo.temperature;

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet set) {
        super(context, set);
    }

    private LinearGradient mShader = new LinearGradient(0f, 0f, 40f, 60f, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}, null, Shader.TileMode.REPEAT);
    private RectF rect = new RectF();
    //定义画笔
    private Paint paint = new Paint();
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();
    private Paint paint4 = new Paint();
    //重写该方法，进行绘图

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);
        //去锯齿
        //文字
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
        paint.setTextSize(50f);
        paint.setTextAlign(Paint.Align.CENTER);

        //圆角矩形
        paint1.setAntiAlias(true);
        paint1.setColor(getResources().getColor(R.color.green_near));
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(10f);
        int viewWidth = this.getWidth();

        //水银柱矩形
        paint2.setAntiAlias(true);
        paint2.setColor(getResources().getColor(R.color.green_near));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(10f);

        paint3.setAntiAlias(true);
        paint3.setColor(Color.GRAY);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setStrokeWidth(2f);
        paint3.setTextSize(30f);
        paint3.setTextAlign(Paint.Align.CENTER);

        //背景
        paint4.setAntiAlias(true);
        paint4.setColor(getResources().getColor(R.color.lightgray));

        /*
        画水银底部圆角矩形
         */
        canvas.drawRoundRect(viewWidth / 2 + 100f,
                getHeight() - 20f, viewWidth / 2 - 100f,
                getHeight() - 150f, 15f, 15f, paint1);
        //画底部温度文字
        canvas.drawText("温度", viewWidth / 2, getHeight() - 65f, paint);
        //画水银柱矩形背景
        canvas.drawRect(viewWidth / 2 - 45f, getHeight() - 150f, viewWidth / 2 + 45f, 95f, paint4);
        //画水银柱矩形
        canvas.drawRect(viewWidth / 2 - 40f, getHeight() - 150f, viewWidth / 2 + 40f, ((getHeight() - 300f) / 100f) * (100f - temperature) + 100f, paint2);
        //画刻度线
//        canvas.drawLine(getWidth() / 2 + 150f,
//                getHeight() - 150f, getWidth() - 350f, getHeight() - 150f, paint3);
        //大刻度高度
        float big = (getHeight() - 300f) / 5;
        //中刻度高度
        float mid = (getHeight() - 300f) / 5;
        //小刻度高度
        float small = (getHeight() - 300f) / 50;
        //垂直刻度线
//        canvas.drawLine(getWidth() / 2 + 100f,
//                100f,
//                getWidth() / 2 + 100f,
//                getHeight() - 200f, paint3);

        float heightTotal = getHeight() - 150f;

//        Log.d("getWidth()+getHeight()", getWidth() + "---" + getHeight());
        //绘制大刻度
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(getWidth() / 2 + 50f,
                    getHeight() - 200f - i * big,
                    getWidth() / 2 + 100f,
                    getHeight() - 200f - i * big, paint3);
        }
        //小刻度
        for (int j = 0; j < 50; j++) {
            canvas.drawLine(getWidth() / 2 + 50f,
                    getHeight() - 200f - j * small,
                    getWidth() / 2 + 60f,
                    getHeight() - 200f - j * small, paint3);
        }
        //中刻度
        for (int k = 0; k < 5; k++) {
            canvas.drawLine(getWidth() / 2 + 50f,
                    getHeight() - 200f - k * mid - big / 2,
                    getWidth() / 2 + 70f,
                    getHeight() - 200f - k * mid - big / 2,
                    paint3);
        }
        /**
         * 画刻度值
         */
        for (int i = 0; i < 6; i++) {
            canvas.drawText(i * 20 + "",
                    getWidth() / 2 + 130f,
                    getHeight() - 190f - i * big,
                    paint3);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wendu_bg);
        Rect src, dst;
        src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dst = new Rect(0, 0, bitmap.getWidth() / 6, bitmap.getHeight() / 6);
        dst.left = 20;
        dst.top = getHeight()/2-60;
        dst.bottom=getHeight()/2+60;
        dst.right=getWidth()/2-80;

        canvas.drawBitmap(bitmap, src, dst, null);

        canvas.drawText((int)temperature+"℃",100,getHeight()/2+15,paint);

    }
}
