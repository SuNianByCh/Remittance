package com.yaer.remittance.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.yaer.remittance.R;
import com.yaer.remittance.view.CustomTimePickerView;
import com.yaer.remittance.view.bar.CustomTimePickerBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppPickerView {
    private static CustomTimePickerView pvTime;

    /***
     * 拍卖起始时间*/
    public static void showWorkingPickerView(final Context context, final TextView tv) {
        final Calendar selectedDate = Calendar.getInstance();//系统时间、、//选择错误后回滚到默认到日期  也是要求日期 超过这个限定就自动回滚
        Calendar startDate = Calendar.getInstance();//起始日期
        startDate.set(2018, 5, 16,1,1);
        Calendar endDate = Calendar.getInstance();//结束日期
        endDate.set(2027, 5, 16,1,1);
        pvTime = new CustomTimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                Date datatow = getDateAfter(new Date(System.currentTimeMillis()), 3);
                Log.i("pvTime", "onTimeSelectChanged" + date);
                if (date.compareTo(selectedDate.getTime()) == -1) {//!date.after(new Date(System.currentTimeMillis()))
                   pvTime.setDate(selectedDate);//选中的时间错误回滚到系统默认时间
                    ToastUtils.showToast("不能小于当前时间");
                    return;
                } else if (date.getTime() >= datatow.getTime()) {
                    pvTime.setDate(selectedDate);
                    ToastUtils.showToast("开始时间最多能预售3天，请重新选择");
                    return;
                }
                String sssss=getTime(date);
                tv.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");
                if(pvTime != null && pvTime.isShowing()){
                    pvTime.dismiss();
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time,null)
                .setType(new boolean[]{false, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .isCyclic(false)
                .build();
        pvTime.show();
    }

    /***
     * 拍卖结束时间*/
    public static void showendWorkingPickerView(final Context context, final TextView tv) {
        final Calendar selectedDate = Calendar.getInstance();//系统时间、、//选择错误后回滚到默认到日期  也是要求日期 超过这个限定就自动回滚
        Calendar startDate = Calendar.getInstance();//起始日期
        startDate.set(2018, 5, 16,1,1);
        Calendar endDate = Calendar.getInstance();//结束日期
        endDate.set(2027, 5, 16,1,1);
        pvTime = new CustomTimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Log.i("pvTime", "onTimeSelectChanged" + date);
                Date datatow = getDateAfter(new Date(System.currentTimeMillis()), 3);
                Date datatows = getDateAfter(new Date(System.currentTimeMillis()), 7);
                Log.i("pvTime", "onTimeSelectChanged" + date);
                if (date.compareTo(selectedDate.getTime()) == -1) {//!date.after(new Date(System.currentTimeMillis()))
                    pvTime.setDate(selectedDate);//选中的时间错误回滚到系统默认时间
                    ToastUtils.showToast("不能小于当前时间");
                    return;
                } else if (date.getTime() <= datatow.getTime()) {
                    pvTime.setDate(selectedDate);
                    ToastUtils.showToast("结束时间最少选择3天，请重新选择");
                    return;
                }else if(date.getTime() >= datatows.getTime()){
                    pvTime.setDate(selectedDate);
                    ToastUtils.showToast("结束时间最多选择最多7天，请重新选择");
                    return;
                }
                String sssss=getTime(date);
                tv.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");
                if(pvTime != null && pvTime.isShowing()){
                    pvTime.dismiss();
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time,null)
                .setType(new boolean[]{false, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .isCyclic(false)
                .build();
        pvTime.show();
    }

    public static long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day >= 1) {
                return day;
            } else {
                if (day == 0) {
                    return 1;
                } else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime() + "当前时间：" + System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
        return format.format(date);
    }
}
