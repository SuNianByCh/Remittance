package com.yaer.remittance.view;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yaer.remittance.R;

public class SelectPicPopupWindow extends PopupWindow {

	public SelectPicPopupWindow(Activity activity, View mMenuView) {
		super(activity);
		this.setContentView(mMenuView);
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.PopupAnimation);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
//		mMenuView.setOnTouchListener(new View.OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//
//			if(event.getAction()==MotionEvent.ACTION_UP){
//				//dismiss();
//			}
//			return true;
//			}
//		});

	}

}
