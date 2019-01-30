package com.yaer.remittance.utils;

/**
 * Created by Administrator on 2018-09-11.
 */

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.yaer.remittance.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by min on 2017/11/28.
 */

public class NotificationsUtils {
    private static String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 判断该应用通知开关是否开启
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {
        try {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass = null;
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断手机通知是否开启，没有开启则打开应用管理权限页面
     * @param context
     */
    public static void showDelDialog(final Context context, String message){
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("温馨提示");
        builder.setIcon(R.mipmap.logo);
        builder.setMessage(message);
        // 更新
        builder.setPositiveButton("获取权限", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }
}
