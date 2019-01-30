package com.yaer.remittance.ui.user_modular.setup;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.CountDownTimerUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class UserForgetPwdActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_forget_pwd)
    CustomTitlebar ct_forget_pwd;
    /*获取手机号*/
    @BindView(R.id.et_forget_phone)
    MyEditText et_forget_phone;
    /*获取验证码*/
    @BindView(R.id.et_forget_pwd)
    EditText et_forget_pwd;
    /*新密码*/
    @BindView(R.id.et_new_pwd)
    MyEditText et_new_pwd;
    /*再次输入新密码*/
    @BindView(R.id.et_forget_newpwd)
    MyEditText et_forget_newpwd;
    /*获取验证码按钮*/
    @BindView(R.id.tv_forget_yzm)
    TextView tv_forget_yzm;
    private String utoken;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    /*绑定token值*/
    private String token;
    private String type = "6";
    /*获取时间戳*/
    private long timeStamp;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_forget_pwd).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.longin_forget_pwd_layout;
    }

    @Override
    public void initView() {
        ct_forget_pwd.setAction(this);
        timeStamp = System.currentTimeMillis();
    }

    //请求demo
    public void initData() {
    }

    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        return sb.reverse().toString(); //反转功能
    }

    @OnClick({R.id.tv_forget_yzm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_yzm:
                if (!AppUtile.isEditText(et_forget_phone)) {
                    ToastUtils.showShort(context, "手机号不能为空!");
                } else if (!AppUtile.isPhone(et_forget_phone.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_forget_yzm, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(et_forget_phone.getText().toString().trim());
                }
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getPhone(String string) {
        String paipinhui = AppUtile.string2MD5("拍品汇" + timeStamp);
        token = bufferreverse(paipinhui);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GET_YANZHENGMA)
                .tag(this)
                .params("phone", string)
                .params("type", type)
                .params("token", token)
                .params("timestamp", timeStamp)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取验证码: " + response.body().code);
                        Log.e("text", "获取验证码: " + response.body().result);
                        utoken = response.body().result.toString();
                        ToastUtils.showShort(context, response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(context, response.body().msg);
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
            case R.id.tv_right:
                String newpwd = et_new_pwd.getText().toString().trim();
                String confirmnewpwd = et_forget_newpwd.getText().toString().trim();
                if (!AppUtile.isEditText(et_forget_phone)) {
                    ToastUtils.showShort(context, "手机号不能为空！");
                } else if (!AppUtile.isPhone(et_forget_phone.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else if (!AppUtile.isEditText(et_forget_pwd)) {
                    ToastUtils.showShort(context, "验证码不能为空！");
                } else if (!AppUtile.isEditText(et_new_pwd)) {
                    ToastUtils.showShort(context, "新密码不能为空！");
                } else if (!AppUtile.isEditText(et_forget_newpwd)) {
                    ToastUtils.showShort(context, "再次输入新密码不能为空！");
                } else if (!AppUtile.isPsw(et_forget_newpwd.getText().toString().trim())) {
                    ToastUtils.showShort(context, "密码格式为数字和字母组合,密码长度为6-20位");
                } else if (!newpwd.equals(confirmnewpwd)) {
                    ToastUtils.showShort(context, "两次密码不一致请从新输入！");
                    et_new_pwd.setText("");
                    et_forget_newpwd.setText("");
                } else {
                    getYANZHENGMA(et_forget_phone.getText().toString().trim(), confirmnewpwd);
                }
                break;
        }
    }

    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string, final String confirmnewpwd) {
        final String yzm = et_forget_pwd.getText().toString();
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.VERIFICATION_YANZHENGMA)
                .tag(this)
                .params("phone", string)
                .params("type", type)
                .params("yzm", yzm)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Log.e("text", "验证验证码是否正确: " + response.body().result);
                            ToastUtils.showShort(context, response.body().msg);
                            SubmitForgetpwd(confirmnewpwd);
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
    }

    /*忘记密码*/
    private void SubmitForgetpwd(String pwd) {
        LoadingDialog.showDialogForLoading(UserForgetPwdActivity.this, "加载中....");
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATELOGPWD)
                .tag(this)
                .params("utoken", utoken)
                .params("loginpwd", pwd)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "修改密码: " + response.body().code);
                        Log.e("text", "修改密码: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserForgetPwdActivity.this, response.body().msg);
                            LoadingDialog.hide();
                            finish();
                        } else {
                            LoadingDialog.hide();
                            ToastUtils.showShort(UserForgetPwdActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
