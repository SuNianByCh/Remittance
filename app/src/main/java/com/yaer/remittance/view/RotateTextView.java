package com.yaer.remittance.view;

import  android.content.Context;
import  android.graphics.Canvas;
import  android.util.AttributeSet;
import  android.widget.TextView;

public  class  RotateTextView  extends  android.support.v7.widget.AppCompatTextView {
   /* private  static  final  String  NAMESPACE = "https://www.ywlx.net/apk/res/easymobi";
    private  static  final  String  ATTR_ROTATE = "rotate";
    private  static  final  int  DEFAULTVALUE_DEGREES = 0;
    private  int  degrees ;
    public  RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        degrees = attrs.getAttributeIntValue(NAMESPACE, ATTR_ROTATE, DEFAULTVALUE_DEGREES);

    }
    @Override
    protected  void  onDraw(Canvas canvas) {

        canvas.rotate(degrees,getMeasuredWidth()/2,getMeasuredHeight()/2);
        super.onDraw(canvas);
    }*/

    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
        canvas.rotate(45, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        super.onDraw(canvas);


    }
}