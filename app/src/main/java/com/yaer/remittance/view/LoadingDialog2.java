package com.yaer.remittance.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yaer.remittance.R;

/**
 * Created by yanfa1 on 2016/5/17.
 */
public class LoadingDialog2 extends Dialog {

    public LoadingDialog2(Context context, String remind) {
        super(context, R.style.loadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_loading);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }


    @Override
    public void show() {
        try {
            this.getWindow().setGravity(Gravity.CENTER);
            WindowManager wm = (WindowManager) getContext().getSystemService(
                    Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);

            super.show();
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
