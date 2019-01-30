package com.yaer.remittance.view;

import android.view.View;

import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.view.TimePickerView;

public class CustomTimePickerView extends TimePickerView {
    public CustomTimePickerView(PickerOptions pickerOptions) {
        super(pickerOptions);
    }
    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if ("submit".equals(tag)) {
            returnData();
        }else {
            dismiss();
        }

    }
}
