package com.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static java.sql.Timestamp d2t(java.util.Date date){
        if(null==date){
            return null;
        }
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp t){
        if(null==t){
            return null;
        }
        return new java.util.Date(t.getTime());
    }

    public static Date string2date(String sources){
        SimpleDateFormat sdf = getSimpleDateFormat(sources);
        Date date = null;
        try {
            date = sdf.parse(sources);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String date2string (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return s;
    }

    private static SimpleDateFormat getSimpleDateFormat(String sources) {
        SimpleDateFormat sdf = null;
        if (sources.matches("\\d{4}-\\d{2}-\\d{2}"))
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        else if (sources.matches("\\d{4}\\d{2}\\d{2}"))
            sdf = new SimpleDateFormat("yyyyMMdd");
        else if (sources.matches("\\d{4}:\\d{2}:\\d{2}"))
            sdf = new SimpleDateFormat("yyyy:MM:dd");
        else if (sources.matches("\\d{4}年\\d{2}月\\d{2}日"))
            sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf;
    }
}
