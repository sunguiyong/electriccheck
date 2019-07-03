package com.example.electricpower.utils.dialog.photo.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转化为日期
 */
public class DateUtils {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}
