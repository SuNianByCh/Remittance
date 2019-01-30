package com.yaer.remittance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by min on 2017/7/6.
 */
public class DateUtile {

    public static String[] timesTwo(long t){
        String [] result = new String[3];
        if (t>0){
            int  d =(int) Math.floor(t / 1000 / 60 / 60 / 24);
            int h = (int)Math.floor(t / 1000 / 60 / 60 % 24);
            int m = (int) Math.floor(t / 1000 / 60 % 60);
            int s = (int) Math.floor(t / 1000 % 60);
            // h += d > 0 ? d * 24 : 0;

            result[0] = String.format("%02d",(h+d*24));
            result[1] = String.format("%02d",m);
            result[2] = String.format("%02d",s);
        }
        return result;
    }

    public static String times(long time)  {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000));
        return date;
    }

    public static  String countDown(long tmi){
        String [] result = new String[3];
        String endTimeStr =times(tmi);
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        try {
            Date end = sdf.parse(endTimeStr);
            Date now = new Date();
            long t = end.getTime() - now.getTime();
            if (t > 0) {
                int  d =(int) Math.floor(t / 1000 / 60 / 60 / 24);
                int h = (int)Math.floor(t / 1000 / 60 / 60 % 24);
                int m = (int) Math.floor(t / 1000 / 60 % 60);
                int s = (int) Math.floor(t / 1000 % 60);
               // h += d > 0 ? d * 24 : 0;
                result[0] = String.format("%02d",d);
                result[1] = String.format("%02d",h);
                result[2] = String.format("%02d",m);

            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        if (result[0]==null){
            result[0]="0";
        }
        if (result[1]==null){
            result[1]="0";
        }
        if (result[2]==null){
            result[2]="0";
        }
        return  result[0]+"天"+result[1]+"时"+result[2]+"分";
    }

    public static long timeDifference(String endtime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diff = 0;
        try {
            //系统时间转化为Date形式
            Date dstart = new Date();
            //活动结束时间转化为Date形式
            Date dend = format.parse(endtime);
            //算出时间差，用ms表示
            diff = dend.getTime() - dstart.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //返回时间差
        return diff;
    }
    public static long timeDiffer(long endtime){
        long diff = 0;
        Date dstart = new Date();
        diff=endtime-dstart.getTime();
        return diff;
    }
    public static long timeDifferen(String endtime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dend= format.parse(endtime);
            return dend.getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 把时间戳转化yyyy.MM.dd 时间格式
     * @param endtime
     * @return
     */
    public static String diffTime(String endtime) {
        long time=Long.valueOf(endtime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(new Date(time));
    }



}
