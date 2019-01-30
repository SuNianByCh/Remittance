package com.yaer.remittance.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaer.remittance.R;

public class GlideLoadUtils {
    private String TAG = "ImageLoader";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public GlideLoadUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideLoadUtils INSTANCE = new GlideLoadUtils();
    }

    public static GlideLoadUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url           加载图片的url地址  String
     * @param imageView     加载图片的ImageView 控件
     * @param default_image 图片展示错误的本地图片 id
     */
    public void glideLoad(Context context, String url, ImageView imageView) {
        if (context != null) {
            Glide.with(context).load(url).error(R.drawable.error_failed).crossFade
                    ().into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,context is null");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void glideLoad(Activity activity, String url, ImageView imageView, int default_image) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).error(default_image).crossFade
                    ().into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,activity is Destroyed");
        }
    }
}
