package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.BankCardInfoBean;
import com.yaer.remittance.bean.getBondInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayBondDialogFragment;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 保证金
 */
public class BondActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_pay_of_money)
    CustomTitlebar ct_pay_of_money;
    @BindView(R.id.btn_bond_payment_confirm)
    Button btn_bond_payment_confirm;
    @BindView(R.id.tv_bond_money)
    TextView tv_bond_money;
    @BindView(R.id.et_bond_money)
    EditText et_bond_money;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_pay_of_money).init();
    }

    @Override
    public void initView() {
        ct_pay_of_money.setAction(this);
        getBondInfo();
    }

    /**
     * 获取保证金
     */
    private void getBondInfo() {
        OkGo.<BaseMode<getBondInfoBean>>post(AppApi.BASE_URL + AppApi.GETBONDINFO)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<getBondInfoBean>>(this) {
                    @Override
                    public void onSuccess(final Response<BaseMode<getBondInfoBean>> response) {
                        Log.e("text", "获取保证金: " + response.body().code);
                        Log.e("text", "获取保证金: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            tv_bond_money.setText("￥" + response.body().result.getBmoney());
                        } else {
                            tv_bond_money.setText("￥0");
                            ToastUtils.showShort(BondActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bond;
    }

    @OnClick({R.id.btn_bond_payment_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bond_payment_confirm:
                if (!AppUtile.isEditText(et_bond_money)) {
                    ToastUtils.showToast("请输入金额，金额不能为空");
                } else {
                    showdialog(et_bond_money.getText().toString().trim());
                }
                break;
        }
    }

    private void showdialog(String money) {
        PayBondDialogFragment payBondDialogFragment = new PayBondDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("money", money);
        payBondDialogFragment.setArguments(bundle);
        payBondDialogFragment.show(getSupportFragmentManager(), "payFragment");
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

