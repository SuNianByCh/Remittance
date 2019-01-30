package com.yaer.remittance.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaer.remittance.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12
 */
public class ImageManager {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    // 将资源ID转为Uri
    public static Uri resourceIdToUri(Context context , int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    //加载首页图片
    public static void loadFisrtImage(Context context, String url, ImageView imageView){
        Glide.with(context.getApplicationContext())
                .load(url)
                .error(R.drawable.goods)
                .crossFade()
                .into(imageView);
    }

    // 加载网络图片
    public static void loadUrlImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .crossFade()
                .placeholder(R.drawable.goods)
                .error(R.drawable.goods)
                .into(imageView);
    }
    // 加载促销图片
    public static void loadUrlCuImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .crossFade()
                .into(imageView);
    }
    // 加载促销图片
    public static void loadClassCuImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .fitCenter()
                .into(imageView);
    }
    // 加载网络图片
    public static void loadHeadlImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .error(R.drawable.ic_heart)
                .crossFade()
                .into(imageView);
    }
    // 加载网络图片
    public static void loadUrlBannerImage(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .error(R.drawable.banner)
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public static void loadResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(resourceIdToUri(context,resId))
                .crossFade()
                .into(imageView);
    }

    /**
     * 把ImageView转化String
     * @param imageView 图片控件
     * @return
     */
   public static String imageChangBitmap(ImageView imageView){
       Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
       return  bitmapToBase64(bitmap);
   }
    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    private static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
