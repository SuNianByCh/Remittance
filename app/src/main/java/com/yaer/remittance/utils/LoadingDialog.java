package com.yaer.remittance.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.ui.user_modular.personal.PersonalInformationActivity;


/**
 * Created by Administrator on 2017/6/12.
 */
public class LoadingDialog {
    private static ProgressBar loading;
    private static Dialog mLoadingDialog;

    /**
     * 显示加载对话框
     *
     * @param context 上下文
     * @param msg     对话框显示内容
     */
    public static void showDialogForLoading(Context context, String msg) {
        //  loading_view
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog = new Dialog(context, R.style.loading_dialog_style);
        mLoadingDialog.setContentView(view);
        TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        loading = (ProgressBar) view.findViewById(R.id.loading);
        if (!TextUtils.isEmpty(msg))
            loadingText.setText(msg);
        mLoadingDialog.setCancelable(false);
      /*  mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));*/
        mLoadingDialog.show();
        mLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mLoadingDialog.dismiss();
                }
                return true;
            }
        });
        mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mLoadingDialog = null;
                loading = null;
            }
        });
    }

    public static void hide() {
        if (mLoadingDialog == null)
            return;
        if (mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
        mLoadingDialog = null;
        loading = null;
    }
}