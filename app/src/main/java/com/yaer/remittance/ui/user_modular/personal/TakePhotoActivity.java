package com.yaer.remittance.ui.user_modular.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TException;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yaer.remittance.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Author: 海晨忆
 * Date: 2018/3/2
 * Desc:
 */
public class TakePhotoActivity extends AppCompatActivity implements TakePhoto.TakeResultListener, InvokeListener {
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private int flag;
    //private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra("flag", 0);
        initData();
        if (flag == 1) {
            imageUri = getImageCropUri();
            //相机获取照片并剪裁
            takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
            //相机获取不剪裁
            //takePhoto.onPickFromCapture(uri);
        } else if (flag == 2) {
            //imageUri = getImageCropUri();
            //相册获取照片并剪裁
            takePhoto.onPickMultipleWithCrop(1, getCropOptions());
            //相册获取不剪裁
//      takePhoto.onPickFromGallery();
        }
    }

    private void initData() {
        //获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置压缩参数
        compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true);  //设置为需要压缩
    }

    /**
     * 设值裁剪
     *
     * @return
     */
    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);
        builder.setWithOwnCrop(false);
        return builder.create();
      /*  CropOptions cropOptions = new CropOptions.Builder().setAspectX(800).setAspectY(800).setWithOwnCrop(true).create();
        return cropOptions;*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
 /* public TakePhoto getTakePhoto() {
    if (takePhoto == null) {
      takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
    }
    //设置压缩规则，最大500kb
    //设置压缩参数
    compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
    takePhoto.onEnableCompress(compressConfig, true); //设置为需要压缩
    return takePhoto;
  }*/

    @Override
    public void takeSuccess(TResult result) {
        if (flag != 3) {
            String compressPath = result.getImage().getCompressPath();
            setResult(Activity.RESULT_OK, new Intent().putExtra("data", null != compressPath ? compressPath : result.getImage().getOriginalPath()));
            Log.v("WZ", "compressPath:" + compressPath);
            Log.v("WZ", "OriginalPath:" + result.getImage().getOriginalPath());
        }
        finish();
    }


    @Override
    public void takeFail(TResult result, String msg) {
        Log.v("WZ", "takeFail:" + msg);
        if (flag != 3) {
            setResult(Activity.RESULT_OK, new Intent().putExtra("data", ""));
        }
        finish();
    }

    @Override
    public void takeCancel() {
        Log.v("WZ", getResources().getString(R.string.msg_operation_canceled));
        if (flag != 3) {
            setResult(Activity.RESULT_OK, new Intent().putExtra("data", ""));
        }
        finish();
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
