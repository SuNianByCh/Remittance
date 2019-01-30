package com.yaer.remittance.ui.user_modular.setup;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.login_modular.Login_Verification_Activity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 支付安全添加身份信息
 */

public class UserSecurityPaymentActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener, View.OnClickListener {
    @BindView(R.id.ct_security_payment)
    CustomTitlebar ct_security_payment;
    //设置密码布局
    @BindView(R.id.lin_set_password)
    LinearLayout lin_set_password;
    //设置身份信息布局
    @BindView(R.id.lin_set_security)
    LinearLayout lin_set_security;
    //修改密码
    @BindView(R.id.lin_update_password)
    LinearLayout lin_update_password;
    //忘记密码
    @BindView(R.id.lin_forget_password)
    LinearLayout lin_forget_password;
    //真实姓名
    @BindView(R.id.et_realName)
    MyEditText et_realName;
    //身份证号
    @BindView(R.id.et_idCard)
    MyEditText et_idCard;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_security_payment).init();
    }

    @Override
    public void initView() {
        ct_security_payment.setAction(this);
        String password = (String) SharedPreferencesUtils.getData(this, "ishaveUpaypassword", "");//支付密码
        if (password.equals("1")) {
            lin_set_password.setVisibility(View.GONE);
            lin_set_security.setVisibility(View.VISIBLE);
            ct_security_payment.setTilte("添加身份信息");
            ct_security_payment.setShow_right_button(true);
        } else {
            lin_set_password.setVisibility(View.VISIBLE);
            lin_set_security.setVisibility(View.GONE);
            ct_security_payment.setTilte("设置支付密码");
            ct_security_payment.setShow_right_button(false);

        }
    }

    @Override
    public void initData() {
        lin_update_password.setOnClickListener(this);
        lin_forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_forget_password:
                goToActivity(UserForgetPwdActivity.class);
                break;
            case R.id.lin_update_password:
                goToActivity(UserModifyPwdActivity.class);

                break;
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_security_payment;
    }


    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                if (!AppUtile.isEditText(et_realName)) {
                    ToastUtils.showToast("真实名字不能为空，请重新填写！");
                } else if (!AppUtile.isEditText(et_idCard)) {
                    ToastUtils.showToast("证件号码不能为空，请重新填写！");
                } else {
                    goToActivity(UserSetPwdActivity.class);
                }
                break;
        }
    }
}
