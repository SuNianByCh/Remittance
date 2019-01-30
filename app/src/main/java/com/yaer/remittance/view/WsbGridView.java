package com.yaer.remittance.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/6/28.
 */
public class WsbGridView extends GridView {
    public WsbGridView(Context context) {
        super(context);
    }

    public WsbGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WsbGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
