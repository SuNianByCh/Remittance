package com.yaer.remittance.ui.user_modular.setup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.PasswordInputView;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 支付安全输入密码
 */

public class UserSetPwdActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener, PasswordInputView.OnFinishListener {
    @BindView(R.id.ct_set_pwd)
    CustomTitlebar ct_set_pwd;
    @BindView(R.id.password_view)
    PasswordInputView passwordView;
    private String utoken;
    private int uid;
    private String type;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_set_pwd).init();
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = getIntent().getStringExtra("type");
        }
        if (type.equals("0")) {
            ct_set_pwd.setTilte("设置密码");
        } else {
            ct_set_pwd.setTilte("修改密码");
        }
        uid = AppUtile.getUid(this);
        utoken = AppUtile.getTicket(this);
        ct_set_pwd.setAction(this);
        passwordView.setOnFinishListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_set_pwd;
    }

    @Override
    public void initData() {
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    /**
     * 设置密码*
     */
    public void SetPwd(final String passwor) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUSERINF)
                .tag(this)
                .params("uid", uid)
                .params("upaypassword", passwor)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "设置支付密码: " + response.body().code);
                        Log.e("text", "设置支付密码: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserSetPwdActivity.this, response.body().msg);
                            SharedPreferencesUtils.saveData(UserSetPwdActivity.this, "ishaveUpaypassword", "1");//支付密码
                            finish();
                        } else {
                            ToastUtils.showShort(UserSetPwdActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @Override
    public void setOnPasswordFinished() {
        if (passwordView.getOriginText().length() == passwordView.getMaxPasswordLength()) {
            SetPwd(passwordView.getOriginText());
        }
    }
}
