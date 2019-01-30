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
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.login_modular.Login_Verification_Activity;
import com.yaer.remittance.ui.user_modular.personal.NickNameActivity;
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
 * 修改手机号
 */

public class UserModifyPhoneActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_modify_phone)
    CustomTitlebar ct_modify_phone;
    /*手机号*/
    @BindView(R.id.met_modify_phone)
    MyEditText met_modify_phone;
    /*验证码*/
    @BindView(R.id.et_modify_phone_yzm)
    EditText et_modify_phone_yzm;
    /*获取验证码*/
    @BindView(R.id.tv_obtain_yzm)
    TextView tv_obtain_yzm;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    /*获取时间戳*/
    private long timeStamp;
    /*绑定token值*/
    private String token;
    private String type = "6";
    private String utoken;
    private int uid;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_modify_phone).init();
    }

    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        //反转功能 		public StringBuffer reverse():
        return sb.reverse().toString();
    }

    @Override
    public void initView() {
        ct_modify_phone.setAction(this);
        timeStamp = System.currentTimeMillis();
        uid = AppUtile.getUid(this);
        utoken = AppUtile.getTicket(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_modify_phone;
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.tv_obtain_yzm})
    public void onClick(View v) {
        switch (v.getId()) {
            /*获取验证码*/
            case R.id.tv_obtain_yzm:
                if (AppUtile.isPhone(met_modify_phone.getText().toString().trim())) {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_obtain_yzm, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(met_modify_phone.getText().toString().trim());
                    //SubmitModifyPhone(met_modify_phone.getText().toString().trim());
                } else {
                    ToastUtils.showShort(context, "手机号为空或手机号不正确，请重新输入!");
                }
                break;
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            /*提交修改密码*/
            case R.id.tv_right:
                isPhoneInfo();
                break;
        }
    }

    /*提交修改手机号*/
    private void isPhoneInfo() {
        if (AppUtile.isEditText(met_modify_phone)) {
            if (AppUtile.isEditText(et_modify_phone_yzm)) {
                getYANZHENGMA(met_modify_phone.getText().toString().trim());
            } else {
                ToastUtils.showShort(context, "验证码不能为空！");
            }
        } else {
            ToastUtils.showShort(context, "手机号不能为空！");
        }
    }

    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string) {
        final String yzm = et_modify_phone_yzm.getText().toString();
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
                            SubmitModifyPhone(string);
                        } else {
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
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

    /*提交修改手机号*/
    private void SubmitModifyPhone(String phone) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUSERINF)
                .tag(this)
                .params("uid", uid)
                .params("uphone", phone)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "提交修改手机号: " + response.body().code);
                        Log.e("text", "提交修改手机号: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserModifyPhoneActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserModifyPhoneActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
