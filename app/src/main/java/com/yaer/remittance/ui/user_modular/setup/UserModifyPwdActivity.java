package com.yaer.remittance.ui.user_modular.setup;

import android.os.Bundle;
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
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.CountDownTimerUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 修改登录密码
 */

public class UserModifyPwdActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_modify_pwd)
    CustomTitlebar ct_modify_pwd;
    /*手机号*/
    @BindView(R.id.met_set_modifypwd_phone)
    MyEditText met_set_modifypwd_phone;
    /*请输入验证码*/
    @BindView(R.id.et_set_phone_modifypwd_yzm)
    EditText et_set_phone_modifypwd_yzm;
    /*获取验证码*/
    @BindView(R.id.tv_set_modifypwd_yzm)
    TextView tv_set_modifypwd_yzm;
    /*设置新密码*/
    @BindView(R.id.new_modifypwd)
    MyEditText new_modifypwd;
    private String utoken;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    private String type ="5";
    /*绑定token值*/
    private String token;//时间token
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
        mImmersionBar.titleBar(R.id.ct_modify_pwd).init();
    }

    @Override
    public void initView() {
        ct_modify_pwd.setAction(this);
        utoken = AppUtile.getTicket(this);
        timeStamp = System.currentTimeMillis();
    }
    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        //反转功能 		public StringBuffer reverse():
        return sb.reverse().toString();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.user_modify_pwd;
    }
    @OnClick({R.id.tv_set_modifypwd_yzm})
    public void onClick(View v) {
        switch (v.getId()) {
            /*获取验证码*/
            case R.id.tv_set_modifypwd_yzm:
                if (!AppUtile.isEditText(met_set_modifypwd_phone)) {
                    ToastUtils.showShort(context, "手机号不能为空");
                } else if (!AppUtile.isPhone(met_set_modifypwd_phone.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_set_modifypwd_yzm, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(met_set_modifypwd_phone.getText().toString().trim());
                }
                break;
        }
    }
    /**
     * 获取验证码
     */
    private void getPhone(String phone) {
        String paipinhui = AppUtile.string2MD5("拍品汇" + timeStamp);
        token = bufferreverse(paipinhui);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GET_YANZHENGMA)
                .tag(this)
                .params("phone", phone)
                .params("type", type)
                .params("token", token)
                .params("timestamp", timeStamp)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取验证码: " + response.body().code);
                        ToastUtils.showShort(context, response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(context, response.body().msg);
                        }else{
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
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
            case R.id.tv_right:
                //isPwdInfo();
                isPhoneInfo();
                break;
        }
    }

    /*提交修改手机号*/
    private void isPhoneInfo() {
        if (!AppUtile.isEditText(met_set_modifypwd_phone)) {
            ToastUtils.showShort(context, "手机号不能为空");
        } else if (!AppUtile.isPhone(met_set_modifypwd_phone.getText().toString().trim())) {
            ToastUtils.showShort(context, "手机号格式错误");
        } else if (!AppUtile.isEditText(et_set_phone_modifypwd_yzm)) {
            ToastUtils.showShort(context, "验证码不能为空！");
        } else if(!AppUtile.isEditText(new_modifypwd)){
            ToastUtils.showShort(context, "新密码不能为空！");
        }else{
            getYANZHENGMA(met_set_modifypwd_phone.getText().toString().trim());
        }
    }
    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string) {
        final String yzm = et_set_phone_modifypwd_yzm.getText().toString();
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.VERIFICATION_YANZHENGMA)
                .tag(this)
                .params("phone", string)
                .params("type", type)
                .params("yzm", yzm)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Log.e("text", "验证验证码是否正确: " + response.body().code);
                            Submitpwd(new_modifypwd.getText().toString().trim());
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }

                });
    }
   /* *//*提交修改密码*//*
    private void isPwdInfo() {
        String newpwd = new_pwd.getText().toString().trim();
        String confirmnewpwd = confirm_new_pwd.getText().toString().trim();
        if (!AppUtile.isEditText(original_pwd)) {
            ToastUtils.showShort(context, "原密码不能为空！");
        } else if (!AppUtile.isEditText(new_pwd)) {
            ToastUtils.showShort(context, "新密码不能为空！");
        } else if (!AppUtile.isEditText(confirm_new_pwd)) {
            ToastUtils.showShort(context, "再次输入新密码不能为空！");
        } else if (!newpwd.equals(confirmnewpwd)) {
            ToastUtils.showShort(context, "两次密码不一致请从新输入！");
            new_pwd.setText("");
            confirm_new_pwd.setText("");
        } else {
            Submitpwd(confirmnewpwd);
        }
    }*/

    /*修改密码*/
    private void Submitpwd(String pwd) {
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
                            ToastUtils.showShort(UserModifyPwdActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserModifyPwdActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
