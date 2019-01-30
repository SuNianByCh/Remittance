package com.yaer.remittance.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import com.alibaba.fastjson.JSON;
import com.yaer.remittance.base.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/24.
 */
public class SystemUtil {
    private static final int ERROR = -1;

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机唯一设备的id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTimeDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 获取手机当前可用内存大小
     *
     * @param context
     * @return
     */
    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 将获取的内存大小规格化
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     * 获取手机运行的总内存
     *
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("MemTotal:");
            String str = arrayOfString[1].trim();
            String[] strings = str.split(" ");
            BigInteger ingNum = BigInteger.valueOf(Integer.parseInt(strings[0])).multiply(BigInteger.valueOf(1024));
            initial_memory = ingNum.longValue();// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
        } catch (IOException e) {

        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }


    /**
     * SDCARD是否存
     */
    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机内部剩余存储空间
     *
     * @return
     */
    public static String getAvailableInternalMemorySize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, (availableBlocks * blockSize));
    }

    /**
     * 获取手机内部总的存储空间
     *
     * @return
     */
    public static String getTotalInternalMemorySize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, (totalBlocks * blockSize));
    }

    /**
     * 获取SDCARD剩余存储空间
     *
     * @return
     */
    public static String getAvailableExternalMemorySize(Context context) {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return Formatter.formatFileSize(context, (availableBlocks * blockSize));
        } else {
            return "";
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * @param time    1541569323155
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2018-11-07 13:42:03
     */
    public static String getDate2String(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate1(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDatemm(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateauction(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateauctionss(long timeMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*时间转换时间戳*/
    public static String getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return String.valueOf(date.getTime());
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp1(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
        return String.valueOf(ts);
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp1s(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
        return String.valueOf(ts);
    }

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

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDates(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取SDCARD总的存储空间
     *
     * @return
     */
    public static String getTotalExternalMemorySize(Context context) {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return Formatter.formatFileSize(context, (totalBlocks * blockSize));
        } else {
            return "";
        }
    }

    public static int getAppSatus(Context context, String pageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;
        }
    }

    /**
     * 打开第三方的APP
     *
     * @param context
     * @param url
     */
    public static void goOpenUrl(Context context, String url) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(
                "com.fafa.Designer");
        //判断是否存在不存在就打开系统游览器
        if (intent != null) {
            context.startActivity(intent);
        } else {
            if (url != null && !url.equals("")) {
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent2.setData(content_url);
                context.startActivity(intent2);
            } else {
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainIntent);
            }
        }
    }
   /* public static void assembleUrl(Context context,String[] strings){
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (strings.length==2){
            SharedPreferencesUtils.saveData(context,"faDesUrl","");
            context.startActivity(mainIntent);
        }else{
            Map<String,String> maps = (Map) JSON.parse(AppUtile.convertToMapJson(strings[2]));
            if (maps.containsKey("url")){
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(
                        "com.fafa.Designer");
                if (intent!=null){
                    Intent[] intents = {mainIntent, intent};
                    context.startActivities(intents);
                }else{
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(maps.get("url"));
                    intent2.setData(content_url);
                    Intent[] intents = {mainIntent, intent2};
                    context.startActivities(intents);
                }
            }else {
                SharedPreferencesUtils.saveData(context,"faDesUrl","");
            }
        }
    }*/
}
