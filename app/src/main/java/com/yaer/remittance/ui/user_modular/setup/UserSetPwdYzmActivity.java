package com.yaer.remittance.ui.user_modular.setup;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.CountDownTimerUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.UIAlertView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/16.
 * 设置密码获取验证码
 */
public class UserSetPwdYzmActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_set_pay_pwd)
    CustomTitlebar ct_set_pay_pwd;
    /*请输入手机号*/
    @BindView(R.id.met_set_paypwd_phone)
    MyEditText met_set_paypwd_phone;
    /*请输入验证码*/
    @BindView(R.id.et_set_phone_paypwd_yzm)
    EditText et_set_phone_paypwd_yzm;
    /*获取验证码*/
    @BindView(R.id.tv_set_paypwd_yzm)
    TextView tv_set_paypwd_yzm;
    /*下一步设置支付密码*/
    @BindView(R.id.submit_set_phone_pay)
    TextView submit_set_phone_pay;
    /*获取时间戳*/
    private long timeStamp;
    private String type = "6";
    /*绑定token值*/
    private String utoken;
    private String token;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    private String upaypassword;
    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_set_pay_pwd).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_psw_yzm;
    }

    @Override
    public void initView() {
        ct_set_pay_pwd.setAction(this);
        utoken = AppUtile.getTicket(this);
        upaypassword= (String) SharedPreferencesUtils.getData(this,"ishaveUpaypassword","");
        timeStamp = System.currentTimeMillis();
    }
    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        //反转功能 		public StringBuffer reverse():
        return sb.reverse().toString();
    }
    @OnClick({R.id.submit_set_phone_pay,R.id.tv_set_paypwd_yzm})
    public void onClick(View v) {
        switch (v.getId()) {
            /*获取验证码*/
            case R.id.tv_set_paypwd_yzm:
                if (!AppUtile.isEditText(met_set_paypwd_phone)) {
                    ToastUtils.showShort(context, "手机号不能为空");
                } else if (!AppUtile.isPhone(met_set_paypwd_phone.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_set_paypwd_yzm, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(met_set_paypwd_phone.getText().toString().trim());
                }
                break;
                /*下一步设置支付密码*/
            case R.id.submit_set_phone_pay:
                isPhoneInfo();
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

    /*提交修改手机号*/
    private void isPhoneInfo() {
        if (!AppUtile.isEditText(met_set_paypwd_phone)) {
            ToastUtils.showShort(context, "手机号不能为空");
        } else if (!AppUtile.isPhone(met_set_paypwd_phone.getText().toString().trim())) {
            ToastUtils.showShort(context, "手机号格式错误");
        } else if (!AppUtile.isEditText(et_set_phone_paypwd_yzm)) {
            ToastUtils.showShort(context, "验证码不能为空！");
        } else {
            getYANZHENGMA(met_set_paypwd_phone.getText().toString().trim());
        }
    }
    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string) {
        final String yzm = et_set_phone_paypwd_yzm.getText().toString();
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
                            Bundle bundle=new Bundle();
                            if (upaypassword.equals("0")){
                                bundle.putString("type","0");//设置密码
                            }else{
                                bundle.putString("type","1");//修改密码
                            }
                            goToActivity(UserSetPwdActivity.class,bundle);
                            finish();
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }

                });
    }

    /**
     * 设置密码*
     */
    public void upPassword(String loginpwd) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATELOGPWD)
                .tag(this)
                .params("utoken", utoken)
                .params("loginpwd", loginpwd)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "设置密码: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            goToActivity(MainActivity.class);
                        } else if (response.body().code.equals(Constant.REGUISTER_FAIL)) {
                            ToastUtils.showShort(context, response.body().msg);
                        } else if (response.body().code.equals(Constant.YZM_ERROR)) {
                            ToastUtils.showShort(context, response.body().msg);
                        } else {
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
        }
    }
}
