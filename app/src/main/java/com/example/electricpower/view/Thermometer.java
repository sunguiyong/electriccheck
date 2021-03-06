package com.example.electricpower.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.electricpower.R;

import java.text.DecimalFormat;

public class Thermometer extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;

    //定义温度的范围
    int temperatureRange = 5;

    //定义一个盘快的范围
    private RectF mRange = new RectF();
    //定义温度计的宽度和中心宽度
    int mWith;
    int mHeight;
    int centerWith;
    int centerHeight;
    //定义总的宽度

    //定义温度计刻度总长度
    int temperatureAllLong;

    //定义一下水银的宽度
    int MercuryWith;
    //十的倍数的线长度
    int MaxLineLong;
    //五的倍数的线的长度
    int MidLineLong;
    //其他刻度线的长度
    int MinLineLong;
    //刻度间隔
    int scaleLong;
    //定义温度计距离画布的上宽度
    int abHeight;

    //绘制线条的画笔
    private Paint LinePaint;
    //绘制文本的画笔
    private Paint TextPaint;

    //设置温度上升的速度
    private volatile float mSpeed = 0;

    //设置背景图
    private Bitmap mBitmap;

    /**
     * 定义初始温度，当前显示正在变化也就是显示的温度，还有目标温度
     * 其中，初始温度不变，
     * 当前温度是有程序根据不同的速度和目标温度计算出来的，
     * 目标温度则是由仪器传送过来的数据
     */
    //起始温度
    private float BeginTenperature = (float) 0;
    //结束温度
    private int EndTenperature = 100;
    private volatile float CurrentTemperature = (float) 90;

    //目标温度
    float TargetTemperature = 60;

    /**
     * 定义每一秒绘制的次数
     */
    int everySecondTime = 50;

    //设置文字的大小
    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 40, getResources().getDisplayMetrics());
    private float mSymbolTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 35, getResources().getDisplayMetrics());
    private float mShowSymbolTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 45, getResources().getDisplayMetrics());
    /**
     * 用户绘制的线程
     */
    private Thread mThread;
    /**
     * 根据目标温度改变要显示的当前温度的线程
     */
    private Thread mChangeTemperatureThread;

    /**
     * 设置一个标志位，用于线程的开启还是关闭的标志
     *
     * @param context
     */
    private Boolean isRunning;

    private DecimalFormat fomat;//格式化float

    public Thermometer(Context context) {
        this(context, null);
    }

    public Thermometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);

    }

    @Override
    protected void onMeasure(int with, int height) {
        super.onMeasure(with, height);
        //获取的是view原始的大小，也就是这个view在XML文件中配置或者是代码中设置的大小
        this.mWith = getMeasuredWidth() / 2;
        this.mHeight = getMeasuredHeight();
        Log.d("getMeasuredHeight", getMeasuredHeight() + "");
        //这里先把中心设置在屏幕的中心
        this.centerWith = mWith / 2;
        this.centerHeight = mHeight / 2;
        //设置水银的宽度,暂时设置为总宽度的十五分之一
        MercuryWith = mWith / 15;
        MinLineLong = MercuryWith;
        //刻度线横向长度
        MidLineLong = MinLineLong * 8 / 5;
        MaxLineLong = MidLineLong * 3 / 2;
        //temperatureAllLong表示温度刻度总长度
        temperatureAllLong = mHeight * 7 / 10;
        //设置刻度间隔,包含了刻度线的长度
        scaleLong = temperatureAllLong / temperatureRange / 20;//表示一个温度十个刻度


        abHeight = mHeight / 10;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //初始化画笔
        LinePaint = new Paint();
        //去锯齿
        LinePaint.setAntiAlias(true);
        //刻度颜色
        LinePaint.setColor(Color.GRAY);
        LinePaint.setStyle(Paint.Style.STROKE);
        //刻度线宽
        LinePaint.setStrokeWidth(2);
        //初始化画笔
        TextPaint = new Paint();
        //刻度值字体颜色
        TextPaint.setColor(Color.GRAY);
        TextPaint.setTextSize(mTextSize);
        TextPaint.setShader(null);
        //初始化温度计的范围
        mRange = new RectF(0, 0, mWith, mHeight);
        isRunning = true;
        mThread = new Thread(this);
        mThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        isRunning = false;

    }

    @Override
    public void run() {
        //不断进行绘制
        while (isRunning) {
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if (end - start < everySecondTime) {
                //这里控制一下，一秒绘制二十次。也就是五十毫秒绘制一次
                try {
                    Thread.sleep(everySecondTime - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {

        try {
            mCanvas = mHolder.lockCanvas();
            //这里要判断是不是为空，之因为在用户点击了home以后，可能已经执行到这里
            if (mCanvas != null) {
                //这里是开始绘制自己要的东西
                //先绘制背景,
                drawBg();
                //绘制水银的高度还有，显示体温
                drawShowHeightAndShow();
            }
        } catch (Exception e) {
            // e.printStackTrace();这里的异常不处理，
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }

    private void drawShowHeightAndShow() {

        //这里控制水银的上升速度
        float difference = Math.abs(TargetTemperature - CurrentTemperature) / 60;
        /**
         * //这里定义一个boolean来控制是使用加法还是减法，其中true表示当前温度小于
         * 目标温度，要使用加法，false表示当前温度大于目标温度，要使用减法。
         */
        boolean addORsub = CurrentTemperature >= TargetTemperature ? false : true;
        if (difference == 0 || difference <= 0.005) {
            mSpeed = 0;
            CurrentTemperature = TargetTemperature;
        } else {
            if (difference > 2) {
                mSpeed = (float) 0.03;
            } else {
                if (difference > 1) {
                    mSpeed = (float) 0.025;
                } else {
                    if (difference > 0.5) {
                        mSpeed = (float) 0.015;
                    } else {
                        if (difference > 0.3) {
                            mSpeed = (float) 0.012;
                        } else {
                            if (difference > 0.2) {
                                mSpeed = (float) 0.009;
                            } else {
                                mSpeed = (float) 0.008;
                            }

                        }
                    }
                }
            }
        }
        if (addORsub) {
            CurrentTemperature += mSpeed * 100;
        } else {
            CurrentTemperature -= mSpeed * 100;
        }

        //

        Paint RectPaint = new Paint();
        RectPaint.setStyle(Paint.Style.FILL);
        //水银柱颜色
        RectPaint.setColor(getResources().getColor(R.color.green_near));
//        RectPaint.setColor(getResources().getColor(R.color.theme_color));
//        这里主要是对温度的显示，画矩形的过程中，唯一改变的就是Top这一个值了
        if (Math.abs(CurrentTemperature - TargetTemperature) > 0.8)
            mCanvas.drawRect(centerWith - MercuryWith / 2,
                    (scaleLong) * 10 * (temperatureRange) + abHeight * 2 -
                            (CurrentTemperature - BeginTenperature) * 60 * scaleLong,
                    centerWith + MercuryWith / 2,
                    (scaleLong) * 10 * (temperatureRange) + abHeight * 2,
                    RectPaint);
        else {
            mCanvas.drawRect(centerWith - MercuryWith / 2,
                    (scaleLong) * 10 * (temperatureRange) + abHeight * 2 -
                            (TargetTemperature - BeginTenperature) / 60 * 10 * scaleLong,
                    centerWith + MercuryWith / 2,
                    (scaleLong) * 10 * (temperatureRange) + abHeight * 2,
                    RectPaint);
        }

        //这里开始画显示的数字
        Paint ShowNumberTextPaint = new Paint();
        ShowNumberTextPaint.setColor(Color.RED);
        ShowNumberTextPaint.setTextSize(mShowSymbolTextSize);
        ShowNumberTextPaint.setShader(null);
        fomat = new DecimalFormat("##0.0");
        float display = Float.parseFloat(fomat.format(trueTemperature));
        mCanvas.drawText(display + "",
                mWith * 3 / 2 - ShowNumberTextPaint.getTextSize() * 2,
                temperatureAllLong / 2 - ShowNumberTextPaint.getTextSize(),
                ShowNumberTextPaint
        );
        mCanvas.drawText(display + "",
                mWith * 3 / 2 - ShowNumberTextPaint.getTextSize() * 2,
                temperatureAllLong / 2 + ShowNumberTextPaint.getTextSize(),
                ShowNumberTextPaint
        );

    }

    private void drawBg() {
        mCanvas.drawColor(Color.WHITE);
//        mCanvas.drawLine(0, 0, mWith, mHeight, LinePaint);
        //画右边的刻度
        //定义每一个长刻度的高度
        float everyTemparaturHeight = (scaleLong) * 10;
        for (int i = 0; i < temperatureRange; i++) {
            mCanvas.drawLine(centerWith + MercuryWith / 2,
                    everyTemparaturHeight * i + abHeight * 2,//这里加上两倍的上距离
                    centerWith + MercuryWith / 2 + MaxLineLong,
                    everyTemparaturHeight * i + abHeight * 2, LinePaint);
            mCanvas.drawText(EndTenperature - i + "", centerWith + MercuryWith / 2 + MaxLineLong + MinLineLong / 2,
                    everyTemparaturHeight * i + TextPaint.getTextSize() / 2 + abHeight * 2, TextPaint);

            //刻度线每隔5个变长
            for (int j = 1; j <= 9; j++) {
                if (j == 5) {
                    mCanvas.drawLine(centerWith + MercuryWith / 2,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2,
                            centerWith + MercuryWith / 2 + MidLineLong,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2, LinePaint);
                } else {
                    mCanvas.drawLine(centerWith + MercuryWith / 2,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2,
                            centerWith + MercuryWith / 2 + MinLineLong,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2, LinePaint);
                }

            }
            //画最后一个刻度
            if (i == temperatureRange - 1) {

                mCanvas.drawLine(centerWith + MercuryWith / 2,
                        everyTemparaturHeight * (i + 1) + abHeight * 2,//这里加上两倍的上距离
                        centerWith + MercuryWith / 2 + MaxLineLong,
                        everyTemparaturHeight * (i + 1) + abHeight * 2, LinePaint);
                mCanvas.drawText(EndTenperature - (i + 1) + "", centerWith + MercuryWith / 2 + MaxLineLong + MinLineLong / 3,
                        everyTemparaturHeight * (i + 1) + TextPaint.getTextSize() / 2 + abHeight * 2, TextPaint);
            }
        }
        //画左边的刻度
        for (int i = 0; i < temperatureRange; i++) {
            mCanvas.drawLine(centerWith - MercuryWith / 2,
                    everyTemparaturHeight * i + abHeight * 2,
                    centerWith - MercuryWith / 2 - MaxLineLong,
                    everyTemparaturHeight * i + abHeight * 2, LinePaint);
            mCanvas.drawText(EndTenperature - i + "", centerWith - (MercuryWith / 2 + MaxLineLong + MinLineLong / 3) - TextPaint.getTextSize(),
                    everyTemparaturHeight * i + TextPaint.getTextSize() / 2 + abHeight * 2, TextPaint);
            for (int j = 1; j <= 9; j++) {
                if (j == 5) {
                    mCanvas.drawLine(centerWith - MercuryWith / 2,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2,
                            centerWith - MercuryWith / 2 - MidLineLong,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2, LinePaint);
                } else {
                    mCanvas.drawLine(centerWith - MercuryWith / 2,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2,
                            centerWith - MercuryWith / 2 - MinLineLong,
                            everyTemparaturHeight * i + j * (scaleLong) + abHeight * 2, LinePaint);
                }

            }
            //画最后一个刻度
            if (i == temperatureRange - 1) {
                mCanvas.drawLine(centerWith - MercuryWith / 2,
                        everyTemparaturHeight * (i + 1) + abHeight * 2,
                        centerWith - MercuryWith / 2 - MaxLineLong,
                        everyTemparaturHeight * (i + 1) + abHeight * 2, LinePaint);
                mCanvas.drawText(EndTenperature - (i + 1) + "", centerWith - (MercuryWith / 2 + MaxLineLong + MinLineLong / 3) - TextPaint.getTextSize(),
                        everyTemparaturHeight * (i + 1) + TextPaint.getTextSize() / 2 + abHeight * 2, TextPaint);
            }
        }
        //画水银柱底部圆形
        Paint CirclePaint = new Paint();
        CirclePaint.setStyle(Paint.Style.FILL);
        CirclePaint.setColor(getResources().getColor(R.color.green_near));
//        CirclePaint.setColor(getResources().getColor(R.color.theme_color));
        mCanvas.drawCircle(centerWith,
                everyTemparaturHeight * (temperatureRange) + abHeight * 2 + MercuryWith,
                MercuryWith * 3 / 2, CirclePaint);
        //画摄氏度的符号
        Paint symbolTextPaint = new Paint();
        symbolTextPaint.setColor(Color.BLUE);
        symbolTextPaint.setTextSize(mSymbolTextSize);
        symbolTextPaint.setShader(null);
        mCanvas.drawText("℃",
                centerWith - MaxLineLong / 2 - MercuryWith / 2 - symbolTextPaint.getTextSize() / 2,
                abHeight * 2 - symbolTextPaint.getTextSize(),
                symbolTextPaint
        );
        mCanvas.drawText("℃",
                centerWith + MaxLineLong / 2 + MercuryWith / 2 - symbolTextPaint.getTextSize() / 2,
                abHeight * 2 - symbolTextPaint.getTextSize(),
                symbolTextPaint
        );

        //绘制显示数字的符号和虚线
        Paint ShowsymbolTextPaint = new Paint();
        ShowsymbolTextPaint.setColor(Color.BLUE);
        ShowsymbolTextPaint.setTextSize(mShowSymbolTextSize);
        ShowsymbolTextPaint.setShader(null);
        mCanvas.drawText("℃",
                mWith * 3 / 2,
                temperatureAllLong / 2 - ShowsymbolTextPaint.getTextSize(),
                ShowsymbolTextPaint
        );
        mCanvas.drawText("- - - - - - - -",
                mWith + ShowsymbolTextPaint.getTextSize() * 3,
                temperatureAllLong / 2,
                ShowsymbolTextPaint
        );
        mCanvas.drawText("℃",
                mWith * 3 / 2,
                temperatureAllLong / 2 + ShowsymbolTextPaint.getTextSize(),
                ShowsymbolTextPaint
        );
    }

    private float trueTemperature = 0;

    public void setTargetTemperature(float targetTemperature) {
        trueTemperature = targetTemperature;
        if (targetTemperature < 0) {
            targetTemperature = 0;
        }
        if (targetTemperature > EndTenperature) {
            targetTemperature = EndTenperature;
        }
        this.TargetTemperature = targetTemperature;
    }
}