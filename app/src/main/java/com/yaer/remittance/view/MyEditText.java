package com.yaer.remittance.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/6/15.
 */
public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(final CallBackInfo mCallBackInfo) {

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
              mCallBackInfo.afterTextString(editable.toString());
            }
        });
    }


    public interface CallBackInfo{
        void afterTextString(String str);
    }
    private CallBackInfo mCallBackInfo;
    public void setCallBackInfo(CallBackInfo mCallBackInfo){
        this.mCallBackInfo=mCallBackInfo;
    }
}
