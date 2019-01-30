package com.yaer.remittance.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;
import com.yaer.remittance.R;

import java.io.File;

/**
 * Created by geyifeng on 2017/6/4.
 */

public class GlideImageLoaders implements ImageLoader {
    /* @Override
     public void displayImage(Context context, Object path, ImageView imageView) {
         Glide.with(context)                             //配置上下文
                 .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                 .error(R.mipmap.default_image)           //设置错误图片
                 .placeholder(R.mipmap.default_image)     //设置占位图片
                 .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                 .into(imageView);
         //Glide 加载图片简单用法
         Glide.with(context).load(path).into(imageView);
         Glide.with(context.getApplicationContext())
                 .load(path)
                 .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                 .placeholder(R.drawable.test)
                 .into(imageView);
     }*/
   /* @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.default_image)           //设置错误图片
                .placeholder(R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }*/

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.imagedetail_failed)           //设置错误图片
                .placeholder(R.mipmap.imagedetail_defaullt)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.imagedetail_failed)           //设置错误图片
                .placeholder(R.mipmap.imagedetail_defaullt)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    /*   @Override
       public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
           Glide.with(activity)                             //配置上下文
                   .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                   .error(R.mipmap.imagedetail_failed)           //设置错误图片
                   .placeholder(R.mipmap.imagedetail_defaullt)     //设置占位图片
                   .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                   .into(imageView);
       }
   */
    @Override
    public void clearMemoryCache() {

    }
}
