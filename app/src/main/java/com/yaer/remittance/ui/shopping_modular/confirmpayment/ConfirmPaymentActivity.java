package com.yaer.remittance.ui.shopping_modular.confirmpayment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/*账户余额支付界面*/
public class ConfirmPaymentActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_confirm_paymeent_title)
    CustomTitlebar ct_confirm_paymeent_title;
    private int uid;//用户id
    private String Amountmoney = null;//用户余额
    private String oid;
    private double ototalvalue;//订单价格
    //账户可用余额
    @BindView(R.id.tv_amount_money)
    TextView tv_amount_money;
    /*订单价格*/
    @BindView(R.id.tv_payment_ototalvalue)
    TextView tv_payment_ototalvalue;
    /*订单商品名称*/
    @BindView(R.id.tv_payment_order_name)
    TextView tv_payment_order_name;
    /*订单商品编号*/
    @BindView(R.id.tv_Order_number)
    TextView tv_Order_number;
    /*确认支付*/
    @BindView(R.id.btn_payment_yue)
    Button btn_payment_yue;
    /*收货人名称*/
    @BindView(R.id.tv_payment_order_addressname)
    TextView tv_payment_order_addressname;
    /*收货人联系方式*/
    @BindView(R.id.tv_payment_order_addressphone)
    TextView tv_payment_order_addressphone;
    /*收货人地址*/
    @BindView(R.id.tv_payment_order_address)
    TextView tv_payment_order_address;
    /*合计金额*/
    @BindView(R.id.tv_payment_order_totalmoney)
    TextView tv_payment_order_totalmoney;
    private String addressname;//收件人
    private String addressphone;//收件人联系方式
    private String address;//收货地址
    private String gname;//商品名称
    private String money;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_confirm_paymeent_title).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_payment;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            // 获取Bundle对象中的参数
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                addressname = bundle.getString("addressname");//收件人
                addressphone = bundle.getString("addressphone");//收件人联系方式
                address = bundle.getString("address");//收货地址
                gname = bundle.getString("gname");//商品名称
                oid = bundle.getString("oid");//订单id
                ototalvalue = bundle.getDouble("ototalvalue");//订单价格
                money = AmountUtil.priceNum(ototalvalue);
            }
        }


        tv_payment_order_addressname.setText(addressname);
        tv_payment_order_addressphone.setText(addressphone);
        tv_payment_order_address.setText(address);
        tv_Order_number.setText(oid);
        tv_payment_order_name.setText(gname);
        tv_payment_ototalvalue.setText("￥" + money);
        tv_payment_order_totalmoney.setText("￥" + money);
        uid = AppUtile.getUid(this);
        ct_confirm_paymeent_title.setAction(this);
        getBalace();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_payment_yue})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_payment_yue:
                showdialog();
                break;
        }
    }

    private void showdialog() {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("oid", oid);
        bundle.putDouble("ototalvalue", ototalvalue);
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getSupportFragmentManager(), "payFragment");
    }

    /**
     * 查询余额*
     */
    public void getBalace() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETBALANCE)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Amountmoney = (String) response.body().result;
                            if (Amountmoney != null) {
                                tv_amount_money.setText(Amountmoney);
                            } else {
                                ToastUtils.showToast("余额不足请求充值");
                            }
                        } else {
                            ToastUtils.showShort(ConfirmPaymentActivity.this, response.body().msg);
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
