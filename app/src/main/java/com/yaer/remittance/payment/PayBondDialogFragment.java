package com.yaer.remittance.payment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.PayDataBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderDetailsActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.PwdEditText;
import com.yaer.remittance.view.PwdKeyboardView;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 * 支付保证金
 */

public class PayBondDialogFragment extends DialogFragment implements PwdEditText.OnTextInputListener {
    private static final String TAG = "PayDialogFragment";
    private PwdEditText editText;
    private String money;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        //去掉dialog的标题，需要在setContentView()之前
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.layout_pay_dialog, null);
        ImageView exitImgView = view.findViewById(R.id.iv_exit);
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            money = bundle.getString("money");
        }
        exitImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayBondDialogFragment.this.dismiss();
            }
        });
        editText = view.findViewById(R.id.et_input);
        editText.setOnTextInputListener(this);
        PwdKeyboardView keyboardView = view.findViewById(R.id.key_board);
        keyboardView.setOnKeyListener(new PwdKeyboardView.OnKeyListener() {
            @Override
            public void onInput(String text) {
                Log.d(TAG, "onInput: text = " + text);
                editText.append(text);
                String content = editText.getText().toString();
                Log.d(TAG, "onInput: content = " + content);
            }

            @Override
            public void onDelete() {
                Log.d(TAG, "onDelete: ");
                String content = editText.getText().toString();
                if (content.length() > 0) {
                    editText.setText(content.substring(0, content.length() - 1));
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = R.style.DialogFragmentAnimation;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        //去掉dialog默认的padding
//        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void onComplete(String result) {
        Log.d(TAG, "onComplete: result = " + result);
        getOrderpay(result);
    }

    public void getOrderpay(String paypwd) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.PAYBOND)
                .tag(this)
                .params("uid", AppUtile.getUid(getContext()))
                .params("money", money)
                .params("paypwd", paypwd)//订单密码
                .execute(new JsonCallback<BaseMode>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        /*这里会出现一异常*/
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("支付成功");
                            PayBondDialogFragment.this.dismiss();
                        } else if (response.body().code.equals("201")) {
                            editText.setText("");
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("203")) {
                            editText.setText("");
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("507")) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("506")) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("503")) {
                            PayBondDialogFragment.this.dismiss();
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }
}
