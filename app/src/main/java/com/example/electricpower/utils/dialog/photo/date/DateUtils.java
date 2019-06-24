package com.example.electricpower.utils.dialog.photo.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转化为日期
 */
public class DateUtils {
    public static String stamp2time(String stamp) {
        String sd="";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sd=sdf.format(new Date(Long.parseLong(stamp)));
        return null;
    }

}
