package com.yaer.remittance.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.yaer.remittance.BaseApplication;


/**
 * 显示一个Toast
 */
public class ToastUtils {
    public static void showToast(String message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(int message) {
        Toast.makeText(BaseApplication.getInstance(), message+"", Toast.LENGTH_SHORT).show();
    }
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast1 = null;
    private static Object synObj = new Object();

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast;
    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 方法说明：
     */
    public static void close(Context context) {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 快速关闭toast
     * 不停的疯狂的点击某个按钮，触发了toast以后，toast内容会一直排着队的显示出来，不能很快的消失。这样可能会影响用户的使用。
     *
     * @param context {@link Context} 当前窗体的上下文
     * @param str     {@link String} 消息主体
     */
    public void showToastQuickClose(final Context context,
                                    final String str) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast1 != null) {
                                toast1.setText(str);
                                toast1.setDuration(Toast.LENGTH_SHORT);
                            } else {
                                toast1 = Toast.makeText(context, str,
                                        Toast.LENGTH_SHORT);
                            }
                            toast1.show();
                        }
                    }
                });
            }
        }).start();
    }
}
