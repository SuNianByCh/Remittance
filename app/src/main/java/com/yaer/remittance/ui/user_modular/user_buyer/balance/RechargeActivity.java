package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.PayResult;
import com.yaer.remittance.bean.PayWXBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.wxapi.WXPayUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 账户充值
 */
public class RechargeActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_recharge)
    CustomTitlebar ct_recharge;
    @BindView(R.id.cb_wx)
    CheckBox cb_wx;
    @BindView(R.id.cb_zfb)
    CheckBox cb_zfb;
    /*微信*/
    @BindView(R.id.wx_payment_layout)
    RelativeLayout wx_payment_layout;
    /*支付宝*/
    @BindView(R.id.zfb_payment_layout)
    RelativeLayout zfb_payment_layout;
    /*当前金额*/
    @BindView(R.id.et_rechargemoney)
    EditText et_rechargemoney;
    @BindView(R.id.btn_payment_confirm)
    Button btn_payment_confirm;
    private int typePay = 2;//支付宝：1，微信：2
    IWXAPI api;
    private String prices = "0";
    private double setDownRedMoney = 0;
    private static final int SDK_PAY_FLAG = 1001;
    private int uid;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.e("text", "支付信息返回状态: " + resultInfo);
                    Log.e("text", "支付信息返回状态: " + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_recharge).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        ct_recharge.setAction(this);
        et_rechargemoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int mSelStart = et_rechargemoney.getSelectionStart();
                int mSelEnd = et_rechargemoney.getSelectionEnd();
                if (!TextUtils.isEmpty(s)) {
                    String price = s.toString();
                    if (price.contains(".")) {
                        // // 点开头，添加0
                        if (price.startsWith(".")) {
                            et_rechargemoney.setText(price.replace(".", "0."));
                            et_rechargemoney.setSelection(price.length() + 1);
                            return;
                        }
                        String[] split = price.split("\\.");
                        if (split[0].length() > 7) {
                            ToastUtils.showToast("小数点前最多7位");
                            s.delete(mSelStart - 1, mSelEnd);
                            et_rechargemoney.setTextKeepState(s);
                            return;
                        }
                        if (split.length < 2)
                            return;
                        if (split[1].length() > 2) {
                            ToastUtils.showToast("小数后最多2位");
                            s.delete(mSelStart - 1, mSelEnd);
                            et_rechargemoney.setTextKeepState(s);
                            return;
                        }
                    } else {
                        if (price.length() > 7) {
                            ToastUtils.showToast(" 最多7位");
                            s.delete(mSelStart - 1, mSelEnd);
                            et_rechargemoney.setTextKeepState(s);
                            return;
                        }
                    }
                    prices = price;
                    btn_payment_confirm.setText("支付 ¥" + prices);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge;
    }

    @OnClick({R.id.wx_payment_layout, R.id.zfb_payment_layout, R.id.btn_payment_confirm, R.id.cb_wx, R.id.cb_zfb})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_wx:
                cb_wx.setChecked(true);
                cb_zfb.setChecked(false);
                typePay = 2;
                break;
            case R.id.cb_zfb:
                cb_zfb.setChecked(true);
                cb_wx.setChecked(false);
                typePay = 1;
                break;
            case R.id.wx_payment_layout:
                cb_wx.setChecked(true);
                cb_zfb.setChecked(false);
                typePay = 2;
                break;
            case R.id.zfb_payment_layout:
                cb_zfb.setChecked(true);
                cb_wx.setChecked(false);
                typePay = 1;
                break;
            case R.id.btn_payment_confirm:
                if (prices != null && !prices.equals("") && !prices.equals("0")) {
                    if (typePay == 1) {
                        ToastUtils.showToast("开始支付宝支付");
                        getOrderInfoZFB(prices);
                    } else {
                        String wxmoney = AmountUtil.changeY2F(prices);
                        ToastUtils.showToast("开始微信支付");
                        getOrderInfoWX(wxmoney);
                    }
                } else {
                    ToastUtils.showToast("请填写正确金额");
                }
                break;
        }
    }

    /*微信支付*/
    public void getOrderInfoWX(String prices) {
        OkGo.<PayWXBean>post(AppApi.BASE_URL + AppApi.ORDERPAYWX)
                .tag(this)
                .params("attach", uid)
                .params("type", "APP")
                .params("total_fee", prices)
                .execute(new JsonCallback<PayWXBean>(this) {
                    @Override
                    public void onSuccess(Response<PayWXBean> response) {
                        final String appid = response.body().getAppid();//应用ID
                        final String partnerid = response.body().getPartnerid();//商户号
                        final String prepayid = response.body().getPrepayid();//预支付交易会话ID
                        final String PackageValue = response.body().getPackageX();//扩展字段
                        final String timestamp = response.body().getTimestamp();//时间戳
                        final String noncestr = response.body().getNoncestr();//随机秘钥
                        final String sign = response.body().getSign();//签名
                        if (!response.body().equals("")) {
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                            builder.setAppId(appid)
                                    .setPartnerId(partnerid)
                                    .setPrepayId(prepayid)
                                    .setPackageValue(PackageValue)
                                    .setNonceStr(noncestr)
                                    .setTimeStamp(timestamp)
                                    .setSign(sign)
                                    .build().toWXPayNotSign(RechargeActivity.this);
                        }
                    }
                });
    }

    /*支付宝支付*/
    public void getOrderInfoZFB(String prices) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ORDERPAYALI)
                .tag(this)
                .params("total_fee", prices)
                .params("body", AppUtile.getUid(this))
                .params("title", "拍品汇")
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "查询支付信息: " + response.body().code);
                        Log.e("text", "查询支付信息: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            final String re = response.body().result.toString();
                            ToastUtils.showShort(RechargeActivity.this, response.body().msg);
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(RechargeActivity.this);
                                    Map<String, String> result = alipay.payV2(re, true);
                                    Log.i("msp", result.toString() + alipay.getVersion());
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtils.showShort(RechargeActivity.this, response.body().msg);
                        }
                    }
                });

    }


    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}

