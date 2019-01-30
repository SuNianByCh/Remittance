package com.yaer.remittance.ui.login_modular;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.bean.RegisterBean;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.ui.user_modular.setup.SetPswActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.CountDownTimerUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证码登录
 */
public class Login_Verification_Activity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_verifiction_pwd)
    LinearLayout ll_verifiction_pwd;
    private Boolean showPassword = true;
    /*叉号关闭当前页*/
    @BindView(R.id.iv_verification_close)
    ImageView iv_verification_close;
    /*获取当前手机号*/
    @BindView(R.id.user_tel)
    MyEditText user_tel;
    /*注册按钮*/
    @BindView(R.id.quick_login)
    TextView quick_login;
    /*获取验证码*/
    @BindView(R.id.tv_yanzhengma)
    TextView tv_yanzhengma;
    /*验证码输入框*/
    @BindView(R.id.et_yanzhengma)
    EditText et_yanzhengma;
    /*获取时间戳*/
    private long timeStamp;
    /*绑定token值*/
    private String token;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    private String type = "2";
    private String types = "";//登录状态传过来的状态描述
    private int ispwd;
    @BindView(R.id.tv_verification_agreement)
    TextView tv_verification_agreement;

    @Override
    public void initView() {
        timeStamp = System.currentTimeMillis();
        toProtocol();
    }

    private void toProtocol() {
        SpannableStringBuilder builder = new SpannableStringBuilder(tv_verification_agreement.getText().toString());
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        UnderlineSpan lineSpan = new UnderlineSpan();
        builder.setSpan(lineSpan, 11, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //下划线
        builder.setSpan(blueSpan, 11, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //字体颜色
        tv_verification_agreement.setText(builder);
    }

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.longin_verification_layout;
    }

    //请求demo
    public void initData() {
        types = getIntent().getStringExtra("type");
        ll_verifiction_pwd.setOnClickListener(this);
        iv_verification_close.setOnClickListener(this);
    }

    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        //反转功能 		public StringBuffer reverse():
        return sb.reverse().toString();
    }

    @OnClick({R.id.ll_verifiction_pwd, R.id.iv_verification_close, R.id.quick_login, R.id.tv_yanzhengma, R.id.tv_verification_agreement})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_verification_agreement:
                Bundle bundle = new Bundle();
                bundle.putString("agreementtitle", "会员协议");
                bundle.putString("agreementurl", "http://www.paiphui.com/h5/doc/userAgreement.html");
                goToActivity(AgreementActivity.class, bundle);
                break;
            case R.id.ll_verifiction_pwd:
                Intent intent1 = new Intent(this, LoginActivity.class);
                intent1.putExtra("type", "4");
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_verification_close:
                finish();
                break;
            /*获取验证码*/
            case R.id.tv_yanzhengma:
                if (!AppUtile.isEditText(user_tel)) {
                    ToastUtils.showShort(context, "手机号不能为空!");
                } else if (!AppUtile.isPhone(user_tel.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_yanzhengma, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(user_tel.getText().toString().trim());
                }
                break;
            /*注册*/
            case R.id.quick_login:
                isInfo();
                break;
        }
    }

    /*快速注册和登陆*/
    private void isInfo() {
        if (AppUtile.isEditText(user_tel)) {
            if (AppUtile.isEditText(et_yanzhengma)) {
                getYANZHENGMA(user_tel.getText().toString().trim());
            } else {
                ToastUtils.showShort(context, "验证码不能为空！");
            }
        } else {
            ToastUtils.showShort(context, "手机号不能为空！");
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
                        ToastUtils.showShort(context, response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string) {
        final String yzm = et_yanzhengma.getText().toString();
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
                            register(string, yzm);
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 验证码登录*
     */
    public void register(String phone, String yzm) {
        OkGo.<BaseMode<RegisterBean>>post(AppApi.BASE_URL + AppApi.YZMLOGIN)
                .tag(this)
                .params("uphone", phone)
                .params("yzm", yzm)
                .params("timestamp", timeStamp)
                .execute(new JsonCallback<BaseMode<RegisterBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<RegisterBean>> response) {
                        Log.e("text", "验证码登录: " + response.body().code);
                        Log.e("text", "验证码登录: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "uToken", response.body().result.getUtoken());
                            ispwd = response.body().result.getIsLogInPwd();
                            if (ispwd == 1) {
                                if (types.equals("4")) {
                                    goToActivity(MainActivity.class, "type", 4);
                                } else if (type.equals("5")) {
                                    goToActivity(CommodityDetailsActivity.class);
                                } else if (type.equals("1")) {
                                    goToActivity(MainActivity.class, "type", 1);
                                } else if (types.equals("3")) {
                                    goToActivity(MainActivity.class, "type", 3);
                                }
                                finish();
                            } else {
                                goToActivity(SetPswActivity.class);
                            }
                            GetUserInfo();
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
    }

    private String utoken;

    /**
     * 获取用户信息
     */
    public void GetUserInfo() {
        utoken = AppUtile.getTicket(this);
        OkGo.<BaseMode<UserBean>>post(AppApi.BASE_URL + AppApi.GETUSERINFO)
                .tag(this)
                .params("utoken", utoken)
                .params("type", "1")
                .execute(new JsonCallback<BaseMode<UserBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<UserBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "uid", response.body().result.getUid());
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "personalauthentication", response.body().result.getPersonalauthentication());//个人认证
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "enterprisecertification", response.body().result.getEnterprisecertification());//企业认证
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "uname", response.body().result.getUname());//用户名称
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "ulevel", response.body().result.getUlevel());//个人认证
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "uicon", response.body().result.getUicon());//个人认证
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "ishaveLoginpassword", response.body().result.getIshaveLoginpassword());//登录密码
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "ishaveUpaypassword", response.body().result.getIshaveUpaypassword());//支付密码
                            getRongcloudtoken();
                        } else {
                            // ToastUtils.showToast(response.body().msg.toString());
                        }
                    }
                });
    }

    public void getRongcloudtoken() {
        int uid = AppUtile.getUid(this);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETRONGCLOUDTOKEN)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取融云token: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(Login_Verification_Activity.this, "rongcloudtoken", response.body().result);//融云token
                        } else {
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
