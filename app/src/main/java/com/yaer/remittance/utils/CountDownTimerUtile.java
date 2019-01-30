package com.yaer.remittance.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.yaer.remittance.R;

/**
 * Created by Administrator on 2017/6/15.
 * 计时器封装
 */
public class CountDownTimerUtile extends CountDownTimer {
    private TextView mTextView;
    private Context context;
    public CountDownTimerUtile(Context context, TextView mTextView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context=context;
        this.mTextView=mTextView;
    }
    @Override
    public void onTick(long l) {
        mTextView.setEnabled(false);
        mTextView.setText(l/1000+"s");
        mTextView.setPadding(10, 10, 10, 10);
      //  mTextView.setBackground(context.getResources().getDrawable(R.drawable.gray_));
    }
    @Override
    public void onFinish() {
        mTextView.setEnabled(true);
        mTextView.setText("重新获取");
        mTextView.setPadding(10, 10, 10, 10);
        mTextView.setBackground(context.getResources().getDrawable(R.drawable.red_));
    }
}
