package com.yaer.remittance.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;
import com.yaer.remittance.R;
import com.yaer.remittance.eventmessage.TypeMessage;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AuctionGoodActivity;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.CommodityActivity;

import java.io.File;

/**
 * Created by Administrator on 2017/6/28.
 * PopupWindow 工具类
 */
public class WsbPopUtile {
    private static Context mContext;
    private static WsbPopUtile mWsbPopUtile;
    private static PopupWindow mPopupWindow;
    private static WindowManager wm;

    public static WsbPopUtile getInstence(Context context) {
        mContext = context;
        wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        return mWsbPopUtile = new WsbPopUtile();
    }

    /**
     * 设置通用的pop
     *
     * @param view    填充布局
     * @param v       跟布局
     * @param hight   高度
     * @param gravity 显示位置
     */
    public void setPop(View view, View v, int hight, int width, int gravity) {
        mPopupWindow = new PopupWindow(view);
        // 设置弹出窗体的宽和高
        mPopupWindow.setHeight(hight);
        mPopupWindow.setWidth(width);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(android.R.color.transparent));
        mPopupWindow.showAtLocation(v, gravity, 0, 0);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popDismiss();
            }
        });
    }

    /**
     * 设置通用的pop
     *
     * @param view  填充布局
     * @param v     跟布局
     * @param hight 高度
     */
    public void setPop(View view, View v, int hight, int width) {
        mPopupWindow = new PopupWindow(view);
        // 设置弹出窗体的宽和高
        mPopupWindow.setHeight(hight);
        mPopupWindow.setWidth(width);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(android.R.color.transparent));
        mPopupWindow.showAsDropDown(v);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popDismiss();
            }
        });
    }

    public static void popDismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }


    /**
     * 拍照弹框
     *
     * @param v
     */
    public void showTalkPhoto(View v, final TakePhoto takePhoto, final TypeMessage ty) {
        final Uri imageUri = getImageCropUri();
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        View view = LayoutInflater.from(mContext).inflate(R.layout.talk_photo_layout, null);
        TextView btn_camera_pop_camera = (TextView) view.findViewById(R.id.btn_camera_pop_camera);
        TextView btn_camera_pop_album = (TextView) view.findViewById(R.id.btn_camera_pop_album);
        TextView btn_camera_pop_cancel = (TextView) view.findViewById(R.id.btn_camera_pop_cancel);
        LinearLayout ly_close = (LinearLayout) view.findViewById(R.id.ly_close);
        ly_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        setPop(view, v, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        btn_camera_pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDismiss();
            }
        });
        btn_camera_pop_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //相册
                if (ty == TypeMessage.close) {
                    takePhoto.onPickFromGallery(); //不支持裁剪
                } else {
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions()); //支持裁剪
                }
                popDismiss();
            }
        });
        btn_camera_pop_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //相机
                if (ty == TypeMessage.close) {
                    takePhoto.onPickFromCapture(imageUri);//
                } else {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());//支持裁剪
                }
                popDismiss();
            }
        });
    }

    /**
     * 发布弹框
     *
     * @param
     */
    public void showTalkCommAuction(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.commodity_auction_layout, null);
        TextView btn_camera_pop_auction = (TextView) view.findViewById(R.id.btn_camera_pop_auction);
        TextView btn_camera_pop_commodity = (TextView) view.findViewById(R.id.btn_camera_pop_commodity);
        TextView btn_camera_pop_cancel = (TextView) view.findViewById(R.id.btn_camera_pop_cancel);
        LinearLayout ly_close = (LinearLayout) view.findViewById(R.id.ly_close);
        ly_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        setPop(view, v, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        btn_camera_pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDismiss();
            }
        });
        /*商品*/
        btn_camera_pop_commodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommodityActivity.class);
                mContext.startActivity(intent);
                popDismiss();
            }
        });
        /*拍品*/
        btn_camera_pop_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AuctionGoodActivity.class);
                mContext.startActivity(intent);
                popDismiss();
            }
        });
    }

    /**
     * 设值裁剪
     *
     * @return
     */
    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int heigh = mContext.getResources().getDisplayMetrics().heightPixels;
        builder.setAspectX(width).setAspectY(1000);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

    /**
     * 设置拍照
     * .     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true); //使用系统自带的相册
        builder.setCorrectImage(true); //纠正拍照的照片旋转角度
        takePhoto.setTakePhotoOptions(builder.create());
    }

    /**
     * 设置图片压缩
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto) {
        //设置压缩参数
        CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, false); //设置为需要压缩
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/paipinhuiApp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
