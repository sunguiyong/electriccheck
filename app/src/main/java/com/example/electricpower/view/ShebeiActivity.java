package com.example.electricpower.view;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electricpower.BaseActivity;
import com.example.electricpower.R;
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
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShebeiActivity extends BaseActivity implements View.OnClickListener, OnChartValueSelectedListener,DatePickerDialog.OnDateSetListener {
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

    private Calendar calendar;
    private DatePickerDialog dialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img: {
                finish();
                break;
            }
            case R.id.xuanzeriqi_tv: {
                calendar=Calendar.getInstance();
                dialog=new DatePickerDialog(ShebeiActivity.this,this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            }
            default:
                break;
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc=String.format("%d年%d月%d日",year,month+1,dayOfMonth);
        xuanzeriqiTv.setText(desc);
    }

    @Override
    public void bindListener() {
        mLineChart.setOnChartValueSelectedListener(this);
        backImg.setOnClickListener(this);
        xuanzeriqiTv.setOnClickListener(this);
    }


    @Override
    public void initData() {
        String name = getIntent().getExtras().getString("name");
        if (name == "" || name == null) {
            name = "未知设备名";
        }
        shebeinameTv.setText(name);
        initTestData1();
        initTestData2();

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

    public void initTestData1() {
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
        xAxis.setAxisMaximum(24);
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
        for (int i = 0; i < 24; i++) {
            entries.add(new Entry(i, new Random().nextInt(80)));
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

    public void initTestData2() {
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
        xAxis.setAxisMaximum(24);
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
        for (int i = 0; i < 24; i++) {
            entries.add(new Entry(i, new Random().nextInt(80)));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}