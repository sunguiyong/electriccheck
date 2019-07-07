package com.example.electricpower.view;

import android.graphics.Color;
import android.os.Bundle;
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
import com.example.electricpower.entity.to.blepostmac.BlePostMac;
import com.example.electricpower.entity.to.mpdata.MPToday;
import com.example.electricpower.utils.dialog.photo.date.DateUtils;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ChartTest extends BaseActivity implements View.OnClickListener, OnChartValueSelectedListener {
    int x = R.layout.testchart;
    @Bind(R.id.line_chart1)
    LineChart mLineChart;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.shebeiname_tv)
    TextView shebeinameTv;
    @Bind(R.id.line_chart2)
    LineChart mlineChart1;
    private List<MPToday.ResultBean> list;
    List<MPToday.ResultBean> listT = new ArrayList<>();
    List<MPToday.ResultBean> listH = new ArrayList<>();
    private String getBTDeviceUrl = SaveData.mainUrl + "bluetooth/getBTDevice";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
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
    }


    @Override
    public void initData() {
        shebeinameTv.setText(DeviceInfo.deviceName);
        getDataThis();
    }

    @Override
    public int getLayoutId() {
        return R.layout.testchart;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void initTestData1(int size) {
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);

        Legend legend = mLineChart.getLegend();
        legend.setEnabled(false);

        //Y轴相关设置
        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(-30);        //保证Y轴从0开始，不然会上移一点
        leftAxis.setDrawGridLines(false);
        leftAxis.setCenterAxisLabels(true);


        leftAxis.setAxisMaximum(70);//Y轴最大值
        leftAxis.setLabelCount(2);//Y轴最小值
        //警戒线
        LimitLine limitLine = new LimitLine(70f, "");
        limitLine.setLineWidth(1f);
        limitLine.setLineColor(getResources().getColor(R.color.green_near));

        LimitLine limitLine1 = new LimitLine(20f, "");
        limitLine1.setLineColor(getResources().getColor(R.color.green_near));

        //x轴
        XAxis xAxis = mLineChart.getXAxis();

        //X轴最小值
        String dateXm = DateUtils.stampToDate(listT.get(0).getGmt_create() + "");
        String dateXm1 = dateXm.substring(11, 13);
        int min = Integer.parseInt(dateXm1);
        xAxis.setAxisMinimum(min);

        //X轴最大值
        String dateX = DateUtils.stampToDate(listH.get(size - 1).getGmt_create() + "");
        String dateX1 = dateX.substring(11, 13);
        int max = Integer.parseInt(dateX1);
        xAxis.setAxisMaximum(max);

        Log.d("min", min + "---" + max);
        xAxis.setLabelCount(listT.size() - 1);

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
        for (int i = 0; i < size; i++) {
//            entries.add(new Entry(i, new Random().nextInt(80)));
//            entries.add(new Entry(i, (float) listT.get(i).getNew_data()));
            String date = DateUtils.stampToDate(listT.get(i).getGmt_create() + "");
            String date1 = date.substring(11, 13);
            entries.add(new Entry(Integer.parseInt(date1), (int) listT.get(i).getNew_data()));
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


    }

    public void initTestData2(int size) {
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

        //X轴最小值
        String dateXm = DateUtils.stampToDate(listH.get(0).getGmt_create() + "");
        String dateXm1 = dateXm.substring(11, 13);
        xAxis.setAxisMinimum(Integer.parseInt(dateXm1));

        //X轴最大值
        String dateX = DateUtils.stampToDate(listH.get(size - 1).getGmt_create() + "");
        String dateX1 = dateX.substring(11, 13);

        xAxis.setAxisMaximum(Integer.parseInt(dateX1));
        Log.d("listH.get", listH.get(size - 1).getGmt_create() + "");
        xAxis.setLabelCount(listH.size());
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
        for (int i = 0; i < size; i++) {
//            entries.add(new Entry(i, new Random().nextInt(80)));
            String date = DateUtils.stampToDate(listH.get(i).getGmt_create() + "");
            String date1 = date.substring(11, 13);
            Log.d("dateMy", date1);
            entries.add(new Entry(Float.parseFloat(date1), (float) listH.get(i).getNew_data()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawFilled(true);
        dataSet.setDrawIcons(false);


        LineData lineData = new LineData(dataSet);


        mlineChart1.invalidate();//refresh
        mlineChart1.setScaleEnabled(false);
        mlineChart1.getDescription().setEnabled(false);
        mlineChart1.setBackgroundColor(Color.WHITE);
        mlineChart1.setPadding(20, 10, 10, 20);
        mlineChart1.setData(lineData);
    }

    private void getDataThis() {
        Gson gson = new Gson();
        BlePostMac blePostMac = new BlePostMac();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = System.currentTimeMillis() + "";// 获取当前时间戳
        Log.d("datetime", dateTime);
        blePostMac.setDateTime(dateTime);//测试，模拟数据
//        blePostMac.setMac(getIntent().getStringExtra("003100350031"));
        String mac = getIntent().getStringExtra("mac");
        String mac1 = mac.replace(":", "");
        String mac2 = mac1.substring(0, 6);
        String mac3 = mac1.substring(6, 12);
        String s = mac3 + mac2;
        String s1 = s.substring(6, 8);
        String s2 = s.substring(10, 12);
        StringBuilder sb = new StringBuilder(s);
        sb.replace(6, 8, s2);
        sb.replace(10, 12, s1);
        Log.d("stringMac", sb.toString());

        blePostMac.setMac(sb.toString());
        String str = gson.toJson(blePostMac);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();

        getDataFromServer(Request.Method.POST, getBTDeviceUrl, jsonObject, MPToday.class, new Response.Listener<MPToday>() {
            @Override
            public void onResponse(MPToday response) {
                Log.d("GETdatathis", "成功");
                if (response.getResult() != null) {
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

                } else {
                    showToast("历史数据暂无");
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
