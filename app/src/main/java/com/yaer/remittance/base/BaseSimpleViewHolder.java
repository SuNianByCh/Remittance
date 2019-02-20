package com.yaer.remittance.base;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.utils.StringUtils;

public class BaseSimpleViewHolder extends BaseViewHolder {
    public BaseSimpleViewHolder(View view) {
        super(view);
    }

    @Override
    public BaseSimpleViewHolder setText(int viewId, int strId) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(strId);
        }
        return this;
    }

    @Override
    public BaseSimpleViewHolder setText(int viewId, CharSequence value) {

        return setText(viewId, value, "");
    }


    public BaseSimpleViewHolder setText(int viewId, CharSequence value, String strDefult) {
        TextView textView = getView(viewId);
        StringUtils.setText(textView, value, strDefult);
        return this;
    }

    public BaseSimpleViewHolder setText(int viewId, String beforContent, CharSequence value, String strDefult) {
        TextView textView = getView(viewId);
        if (StringUtils.isEmpty(beforContent))
            StringUtils.setText(textView, value, strDefult);
        else
            StringUtils.setText(textView,(beforContent+value),strDefult);
        return this;
    }


    public BaseSimpleViewHolder setTextTime(int viewId, String time) {
        return setTextTime(viewId, time, "--");
    }

    public BaseSimpleViewHolder setTextTime(int viewId, long time) {
        return setTextTime(viewId, time, "--");
    }

    public BaseSimpleViewHolder setTextTime(int viewId, String time, String defult) {
        TextView textView = getView(viewId);
        StringUtils.setTextTimeDefault(textView, time, defult);
        return this;
    }

    public BaseSimpleViewHolder setTextTime(int viewId, long time, String defult) {
        return setTextTime(viewId, "" + time, defult);
    }
}
