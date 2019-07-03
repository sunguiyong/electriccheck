package com.example.electricpower.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.DeviceInfo;
import com.example.electricpower.entity.to.SaveData;
import com.example.electricpower.entity.to.mpdata.MPToday;
import com.example.electricpower.entity.to.wenshidu.WenShiGet;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeviceInfoActivity extends BaseActivity implements View.OnClickListener, OnChartValueSelectedListener {
    int x = R.layout.activity_deviceinfo;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.devicename_tv)
    TextView devicenameTv;
    @Bind(R.id.line_chart1)
    LineChart mLineChart;
    @Bind(R.id.line_chart2)
    LineChart mlineChart1;
    @Bind(R.id.history)
    TextView history;
    @Bind(R.id.myview1)
    MyView1 myview1;
    @Bind(R.id.myview2)
    MyView2 myview2;

    private List<MPToday.ResultBean> list;
    List<MPToday.ResultBean> listT = new ArrayList<>();
    List<MPToday.ResultBean> listH = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.history: {
//                showToast("shebeiactivity invoked!!");
                Intent intent = new Intent(mContext, ShebeiActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void bindListener() {
        mLineChart.setOnChartValueSelectedListener(this);
        backImg.setOnClickListener(this);
        history.setOnClickListener(this);
    }


    @Override
    public void initData() {
        devicenameTv.setText(getIntent().getStringExtra("devicename"));
//        String name = getIntent().getExtras().getString("name");
//        if (name == "" || name == null) {
//            name = "未知设备名";
//        }
//        shebeinameTv.setText(name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataThis();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_deviceinfo;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void initTestData1(int xSize) {
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);

        Legend legend = mLineChart.getLegend();
        legend.setEnabled(false);

        //Y轴相关设置
        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);        //保证Y轴从0开始，不然会上移一点
        leftAxis.setDrawGridLines(false);
        leftAxis.setCenterAxisLabels(true);


        leftAxis.setAxisMaximum(100);//Y轴最大值
        leftAxis.setLabelCount(2);//Y轴最小值
        //警戒线
        LimitLine limitLine = new LimitLine(100f, "");
        limitLine.setLineWidth(1f);
        limitLine.setLineColor(getResources().getColor(R.color.green_near));

        LimitLine limitLine1 = new LimitLine(50f, "");
        limitLine1.setLineColor(getResources().getColor(R.color.green_near));

        //x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xSize - 1);
        xAxis.setLabelCount(11);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//x轴位置
        xAxis.setDrawGridLines(false);//x轴方向的背景线
        xAxis.setEnabled(true);//X可见
        xAxis.setDrawLabels(true);//X轴标签可见
        xAxis.setDrawAxisLine(true);//X轴线可见
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setAxisLineColor(getResources().getColor(R.color.green_near));
        xAxis.setAxisLineWidth(1);


        leftAxis.addLimitLine(limitLine);//
        leftAxis.addLimitLine(limitLine1);


        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xSize; i++) {
            entries.add(new Entry(i, (float) listT.get(i).getNew_data()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawFilled(true);
        dataSet.setDrawIcons(false);


        LineData lineData = new LineData(dataSet);


        mLineChart.invalidate();//refresh
        mLineChart.setScaleEnabled(false);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.setPadding(20, 10, 10, 20);
        mLineChart.setData(lineData);
        mLineChart.invalidate();//refresh
        mLineChart.animateY(1000);//动画效果


    }

    public void initTestData2(int xSize) {
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);

        Legend legend = mlineChart1.getLegend();
        legend.setEnabled(false);

        //Y轴相关设置
        YAxis rightAxis = mlineChart1.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = mlineChart1.getAxisLeft();
        leftAxis.setAxisMinimum(0);        //保证Y轴从0开始，不然会上移一点
        leftAxis.setDrawGridLines(false);
        leftAxis.setCenterAxisLabels(true);

        leftAxis.setAxisMaximum(100);//Y轴最大值
        leftAxis.setLabelCount(2);//Y轴最小值
        //警戒线
        LimitLine limitLine = new LimitLine(100f, "");
        limitLine.setLineWidth(1f);
        limitLine.setLineColor(getResources().getColor(R.color.green));

        LimitLine limitLine1 = new LimitLine(50f, "");
        limitLine1.setLineColor(getResources().getColor(R.color.green));

        //x轴
        XAxis xAxis = mlineChart1.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xSize - 1);
        xAxis.setLabelCount(11);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//x轴位置
        xAxis.setDrawGridLines(false);//x轴方向的背景线
        xAxis.setEnabled(true);//X可见
        xAxis.setDrawLabels(true);//X轴标签可见
        xAxis.setDrawAxisLine(true);//X轴线可见
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setAxisLineColor(getResources().getColor(R.color.green));
        xAxis.setAxisLineWidth(1);

        leftAxis.addLimitLine(limitLine);//
        leftAxis.addLimitLine(limitLine1);


        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xSize; i++) {
//            entries.add(new Entry(i, new Random().nextInt(80)));
            entries.add(new Entry(i, (float) listH.get(i).getNew_data()));

        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawFilled(true);
        dataSet.setDrawIcons(false);


        LineData lineData = new LineData(dataSet);


        mlineChart1.setScaleEnabled(false);
        mlineChart1.getDescription().setEnabled(false);
        mlineChart1.setBackgroundColor(Color.WHITE);
        mlineChart1.setPadding(20, 10, 10, 20);
        mlineChart1.invalidate();//refresh
        mlineChart1.setData(lineData);
        mlineChart1.animateY(1000);
    }

    private Long GeneteNodeId(long devId, long nodeid) {
        return devId * 1000000000 + nodeid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (list != null) {
            list.clear();
            listH.clear();
            listT.clear();
        }
    }

    private void getDataThis() {
        getDataFromServer(Request.Method.GET, SaveData.url, MPToday.class, new Response.Listener<MPToday>() {
            @Override
            public void onResponse(MPToday response) {
                Log.d("GETdatathis", "成功");
                if (!response.getResult().isEmpty()) {
                    list = response.getResult();
                    Log.d("listsize", list.size() + "---" + list.get(0).getNew_data());

                    for (int i = 0; i < list.size(); i++) {
                        if (i % 2 == 0) {
                            listT.add(list.get(i));
                        }
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (i % 2 != 0) {
                            listH.add(list.get(i));
                        }
                    }
                    initTestData1(listT.size());
                    initTestData2(listH.size());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GETdatathis", "失败");
            }
        });
    }


}
