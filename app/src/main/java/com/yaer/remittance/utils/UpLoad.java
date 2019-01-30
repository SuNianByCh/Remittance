/*
 * Copyright (C) 2018,米珈科技有限公司 All rights reserved.
 * Project：Remittance
 * Author：马靖乘
 * Date：18-11-17 上午12:01
 */

package com.yaer.remittance.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yaer.remittance.R;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/12.
 */
public class UpLoad {
    private BottomSheetDialog sheetDialog;
    private ActionCallback actionCallback;

    public UpLoad(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    public  void uploadImage(Context context) {
        sheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.talk_photo_camera_layout, null);
        /*相机*/
        TextView tvCamera = view.findViewById(R.id.tvCamera);
        /*相册*/
        TextView tvPhoto = view.findViewById(R.id.tvPhoto);
        TextView tvCancel = view.findViewById(R.id.tvCancel);
        sheetDialog.setContentView(view);
        sheetDialog.show();
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 这里写回调
               // startActForResult(1, 101);
                actionCallback.onClickCamera();
                sheetDialogDismiss();
            }
        });
        tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActForResult(2, 102);
                //TODO 这里写回调
                actionCallback.onClickPhoto();
               sheetDialogDismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sheetDialogDismiss();
            }
        });
    }

    private void sheetDialogDismiss() {
        if (null != sheetDialog && sheetDialog.isShowing()) {
            sheetDialog.dismiss();
        }
    }
    /**
     * 事件回调接口.
     */
    public interface ActionCallback {
        /**
         * paizhao
         */
        void onClickCamera();

        /**
         * tupian
         */
        void onClickPhoto();
    }
}


