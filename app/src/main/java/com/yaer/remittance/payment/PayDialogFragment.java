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
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.PwdEditText;
import com.yaer.remittance.view.PwdKeyboardView;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 */

public class PayDialogFragment extends DialogFragment implements PwdEditText.OnTextInputListener {
    private static final String TAG = "PayDialogFragment";
    private PwdEditText editText;
    private double ototalvalue;//订单金额
    private String onumber;//外面传进来的订单编号
    private String payftype = "";//传进来的状态，判断跳转到哪个界面
    private Gson gson = new Gson();
    private String paydata;
    private int uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        //去掉dialog的标题，需要在setContentView()之前
        uid = AppUtile.getUid(getActivity());
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.layout_pay_dialog, null);
        ImageView exitImgView = view.findViewById(R.id.iv_exit);
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            onumber = bundle.getString("onumber");
            payftype = bundle.getString("payftype");
            ototalvalue = bundle.getDouble("ototalvalue");
        }
        List<PayDataBean> payDataBeanList = new ArrayList<>();
        PayDataBean payDataBean = new PayDataBean();
        payDataBean.setUid(uid);//用户id
        payDataBean.setOnumber(onumber);//订单编号
        payDataBean.setPaymoney(ototalvalue);//金额,
        payDataBeanList.add(payDataBean);
        paydata = gson.toJson(payDataBeanList);
        // Selectorderinfo(onumber);

        exitImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelDialog();
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

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(
                getActivity(), "确认要放弃支付吗？", "订单会保留一段时间，请尽快支付",
                "继续支付", "确认离开");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           if (payftype.equals("1")) {
                                               PayDialogFragment.this.dismiss();
                                               Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                                               intent.putExtra("type", "0");//去全部订单界面
                                               getActivity().startActivity(intent);
                                           } else if (payftype.equals("2")) {
                                               PayDialogFragment.this.dismiss();
                                               getActivity().finish();
                                           } else if (payftype.equals("3")) {
                                           /*    PayDialogFragment.this.dismiss();
                                               Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                                               intent.putExtra("type", "0");//去全部订单界面
                                               getActivity().startActivity(intent);*/
                                               PayDialogFragment.this.dismiss();
                                           } else if (payftype.equals("4")) {
                                               PayDialogFragment.this.dismiss();
                                               Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                                               intent.putExtra("type", "0");//去全部订单界面
                                               getActivity().startActivity(intent);
                                           }
                                       }
                                   }
        );
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

    /*余额支付
    *    .params("paymoney", ototalvalue)//订单金额
                .params("uid", uid)//用户id
                .params("oid", oid)//订单id*/
    public void getOrderpay(String paypwd) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ORDERPAY)
                .tag(this)
                .params("paydata", paydata)
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
                            if (payftype.equals("1")) {
                                PayDialogFragment.this.dismiss();
                                Intent intent = new Intent(getActivity(), SuccessfulOperationActivity.class);
                                intent.putExtra("onumber", onumber);//订单编号
                                intent.putExtra("ordertype", "1");//返回状态
                                getActivity().startActivity(intent);
                            } else if (payftype.equals("2")) {
                                PayDialogFragment.this.dismiss();
                            } else if (payftype.equals("3")) {
                                PayDialogFragment.this.dismiss();
                                Intent intent = new Intent(getActivity(), SuccessfulOperationActivity.class);
                                intent.putExtra("onumber", onumber);//订单编号
                                intent.putExtra("ordertype", "2");//返回状态
                                getActivity().startActivity(intent);
                            } else if (payftype.equals("4")) {
                                PayDialogFragment.this.dismiss();
                                Intent intent = new Intent(getActivity(), SuccessfulOperationActivity.class);
                                intent.putExtra("onumber", onumber);//订单编号
                                intent.putExtra("ordertype", "4");//返回状态
                                getActivity().startActivity(intent);
                            }
                        } else if (response.body().code.equals("201")) {
                            editText.setText("");
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("203")) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("507")) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        } else if (response.body().code.equals("506")) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }
}
