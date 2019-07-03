package com.example.electricpower.utils.dialog.photo.overridempchart;

import com.github.mikephil.charting.components.AxisBase;

public class AxisMyX extends AxisBase {
    protected boolean mCustomAxisMax = false;
    public int mAxisMaximum = 0;
    public int mAxisRange = 0;
    public int mAxisMinimum = 0;



    public void setAxisMaximum(int max) {
        mCustomAxisMax = true;
        mAxisMaximum = max;
        this.mAxisRange = Math.abs(max - mAxisMinimum);
    }
}
