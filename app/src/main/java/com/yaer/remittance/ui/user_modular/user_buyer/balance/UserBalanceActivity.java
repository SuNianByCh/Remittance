package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.BankCardInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ywl on 2016/6/27.
 * 余额
 */
public class UserBalanceActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_balance)
    CustomTitlebar ct_balance;
    //充值
    @BindView(R.id.btn_recharge)
    Button btn_recharge;
    //提现
    @BindView(R.id.btn_forward)
    Button btn_forward;
    //管理银行卡
    @BindView(R.id.ll_manage_bank)
    LinearLayout ll_manage_bank;
    /* //支付安全
     @BindView(R.id.ll_security_payment)
     LinearLayout ll_security_payment;*/
    //保证金
    @BindView(R.id.ll_bond)
    LinearLayout ll_bond;
    /*用户头像*/
    @BindView(R.id.civ_balance_image)
    CircleImageView civ_balance_image;
    /*用户名称*/
    @BindView(R.id.tv_balance_nickname)
    TextView tv_balance_nickname;
    /*用户等级*/
    @BindView(R.id.tv_balance_lenev)
    TextView tv_balance_lenev;
    @BindView(R.id.tv_balance_amountmoney)
    TextView tv_balance_amountmoney;
    private String Amountmoney;
    private List<BankCardInfoBean> ManageForwardItemList = new ArrayList<>();
    private String buyerbalance;
    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalace();
        GetBankCardInfo();
    }

    @Override
    public void initView() {
        ct_balance.setAction(this);
        String name = (String) SharedPreferencesUtils.getData(this, "uname", "");
        String ulevel = (String) SharedPreferencesUtils.getData(this, "ulevel", "");
        String uicon = (String) SharedPreferencesUtils.getData(this, "uicon", "");
        String uphone = (String) SharedPreferencesUtils.getData(this, "uphone", "");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            buyerbalance = getIntent().getStringExtra("buyerbalance");
        }
        if (name.equals("")) {
            tv_balance_nickname.setText(AppUtile.hideCardNo(uphone));
        } else {
            tv_balance_nickname.setText(name);
        }
        if (ulevel.equals("")) {
            tv_balance_lenev.setText("Lv" + 0);
        } else {
            tv_balance_lenev.setText("Lv" + ulevel);
        }
        if (uicon.equals("")) {
            Glide.with(this).load(R.drawable.user_settings).fitCenter().into(civ_balance_image);//买家头像
        } else {
            Glide.with(this).load(uicon).fitCenter().into(civ_balance_image);//买家头像
        }
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_balance).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_balance;
    }

    @OnClick({R.id.btn_recharge, R.id.btn_forward, R.id.ll_manage_bank, R.id.ll_bond})
    public void onClick(View v) {
        switch (v.getId()) {
            /*充值*/
            case R.id.btn_recharge:
                goToActivity(RechargeActivity.class);
                break;
            /*提现*/
            case R.id.btn_forward:
                if (ManageForwardItemList.size() == 0) {
                    ToastUtils.showToast("您还没有添加银行卡");
                } else {
                    goToActivity(PutForwardActivity.class);
                }
                break;
            /*
             * 管理提现账号*/
            case R.id.ll_manage_bank:
                goToActivity(ManageForwardAccountActivity.class);
                break;
         /*   case R.id.ll_security_payment:
                goToActivity(UserSecurityPaymentActivity.class);
                break;*/
            /*保证金*/
            case R.id.ll_bond:
                goToActivity(BondActivity.class);
                break;
        }
    }

    /**
     * 获取提现账号
     */
    private void GetBankCardInfo() {
        OkGo.<BaseMode<List<BankCardInfoBean>>>post(AppApi.BASE_URL + AppApi.SELECTBANKCRDINFOLIST)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<List<BankCardInfoBean>>>(this) {
                    @Override
                    public void onSuccess(final Response<BaseMode<List<BankCardInfoBean>>> response) {
                        Log.e("text", "获取提现账号: " + response.body().code);
                        Log.e("text", "获取提现账号: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ManageForwardItemList = response.body().result;
                        } else {
                            ToastUtils.showShort(UserBalanceActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 查询余额*
     */
    public void getBalace() {
        int uid = AppUtile.getUid(this);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETBALANCE)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Amountmoney = (String) response.body().result;
                            if (Amountmoney != null) {
                                tv_balance_amountmoney.setText(Amountmoney);
                            } else {
                                ToastUtils.showToast("余额不足请求充值");
                            }
                        } else {
                            ToastUtils.showShort(UserBalanceActivity.this, response.body().msg);
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
            /*获取余额明细*/
            case R.id.tv_right:
                if (buyerbalance.equals("1")){
                    goToActivity(FineBalanceActivity.class);
                }else{
                    goToActivity(SellerFineBalanceActivity.class);
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
