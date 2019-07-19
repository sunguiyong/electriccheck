package com.example.electricpower.view;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
import com.example.electricpower.entity.to.SaveData;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 可查询历史记录的设备信息
 */
public class ShebeiActivity extends BaseActivity implements View.OnClickListener, OnChartValueSelectedListener, DatePickerDialog.OnDateSetListener {
    int x = R.layout.activity_shebeidetail;
    @Bind(R.id.line_chart1)
    LineChart mLineChart;
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.shebeiname_tv)
    TextView shebeinameTv;
    @Bind(R.id.line_chart2)
    LineChart mlineChart1;
    @Bind(R.id.xuanzeriqi_tv)
    TextView xuanzeriqiTv;
    @Bind(R.id.search_tv)
    TextView searchTv;
    private List<MPToday.ResultBean> list;
    List<MPToday.ResultBean> listT = new ArrayList<>();
    List<MPToday.ResultBean> listH = new ArrayList<>();
    private Calendar calendar;
    private DatePickerDialog dialog;
    String desc;
    private String dateChoose;
    private boolean isChoose = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.xuanzeriqi_tv: {
                calendar = Calendar.getInstance();
                dialog = new DatePickerDialog(ShebeiActivity.this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            }
            case R.id.search_tv: {
                if (isChoose) {
                    if (list != null) {
                        listT.clear();
                        listH.clear();
                        list.clear();
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date date = simpleDateFormat.parse(dateChoose);
                        long time = date.getTime();
                        String url = SaveData.baseUrl
                                + "&dateTime="
//                                + "1561302000000"
                                + time + "";
                        Log.d("desc", "---" + url);
                        getDataThis(url);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    showToast("日期不能为空");
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        desc = String.format("%d年%d月%d日", year, month + 1, dayOfMonth);
        String d = "";
        String m = "";
        String y = year + "";
        if (month + 1 < 10) {
            m = "0" + (month + 1) + "";
        } else {
            m = (month - 1) + "";
        }
        if (dayOfMonth < 10) {
            d = "0" + dayOfMonth;
        } else {
            d = dayOfMonth + "";
        }
        dateChoose = y + "-" + m + "-" + d + " 23:00:00";
        Log.d("日期", dateChoose);
        xuanzeriqiTv.setText(desc);
        isChoose = true;
    }

    @Override
    public void bindListener() {
        mLineChart.setOnChartValueSelectedListener(this);
        backImg.setOnClickListener(this);
        xuanzeriqiTv.setOnClickListener(this);
        searchTv.setOnClickListener(this);
    }


    @Override
    public void initData() {
        String name = getIntent().getExtras().getString("devicename");
        if (name == "" || name == null) {
            name = "未知设备名";
        }
        shebeinameTv.setText(name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataThis(SaveData.url);
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_shebeidetail;
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
        String dateX = DateUtils.stampToDate(listT.get(size - 1).getGmt_create() + "");
        String dateX1 = dateX.substring(11, 13);
        int max = Integer.parseInt(dateX1);
        xAxis.setAxisMaximum(max);

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
//            entries.add(new Entry(i, (float) listT.get(i).getNew_data()));
            String date = DateUtils.stampToDate(listT.get(i).getGmt_create() + "");
            String date1 = date.substring(11, 13);
            Log.d("date1", date1);
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
        mLineChart.animateY(1000);
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
        int min = Integer.parseInt(dateXm1);
        xAxis.setAxisMinimum(min);
        //X轴最大值
        String dateX = DateUtils.stampToDate(listH.get(size - 1).getGmt_create() + "");
        String dateX1 = dateX.substring(11, 13);
        int max = Integer.parseInt(dateX1);
        xAxis.setAxisMaximum(max);

        xAxis.setLabelCount(listH.size()-1);
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
//            entries.add(new Entry(i, (float) listH.get(i).getNew_data()));
            String date = DateUtils.stampToDate(listH.get(i).getGmt_create() + "");
            String date1 = date.substring(11, 13);
            entries.add(new Entry(Integer.parseInt(date1), (int) listH.get(i).getNew_data()));

            Log.d("date1", date1);
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
        mlineChart1.animateY(1000);
    }

    private void getDataThis(String url) {
        getDataFromServer(Request.Method.GET, url, MPToday.class, new Response.Listener<MPToday>() {
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
