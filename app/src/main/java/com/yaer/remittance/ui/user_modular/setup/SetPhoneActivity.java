package com.yaer.remittance.ui.user_modular.setup;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.callback.JsonCallback;
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
 * Created by Administrator on 2017/6/16.
 * 设置手机号
 */
public class SetPhoneActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_set_phone)
    CustomTitlebar ct_set_phone;
    /*手机号输入框*/
    @BindView(R.id.met_set_phone)
    MyEditText met_set_phone;
    /*验证码输入框*/
    @BindView(R.id.et_set_phone_yzm)
    EditText et_set_phone_yzm;
    /*获取验证码*/
    @BindView(R.id.tv_set_yzm)
    TextView tv_set_yzm;
    /*提交设置手机号*/
    @BindView(R.id.submit_set_phone)
    TextView submit_set_phone;
    /*计时器封装方法*/
    private CountDownTimerUtile countDownTimerUtile;
    /*获取时间戳*/
    private long timeStamp;
    /*绑定token值*/
    private String token;
    private String type = "6";
    private String typelogin;
    private int uid;
    private String uthirdpartyid,uthirdpartytype;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_set_phone).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_phone;
    }

    @Override
    public void initView() {
        typelogin = getIntent().getStringExtra("type");//返回状态
        uthirdpartyid = getIntent().getStringExtra("uthirdpartyid");//第三方id
        uthirdpartytype = getIntent().getStringExtra("uthirdpartytype");//第三方登录类型
        ct_set_phone.setAction(this);
        timeStamp = System.currentTimeMillis();
        uid = (int) SharedPreferencesUtils.getData(SetPhoneActivity.this, "dengluuid", 0);
    }

    @Override
    public void initData() {

    }

    /*反转时间戳*/
    public String bufferreverse(String timebuffer) {
        StringBuffer sb = new StringBuffer(timebuffer);
        //反转功能 		public StringBuffer reverse():
        return sb.reverse().toString();
    }

    @OnClick({R.id.tv_set_yzm, R.id.submit_set_phone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_set_phone:
                isPhoneInfo();
                break;
            /*获取验证码*/
            case R.id.tv_set_yzm:
                if (!AppUtile.isEditText(met_set_phone)) {
                    ToastUtils.showShort(context, "手机号不能为空");
                } else if (!AppUtile.isPhone(met_set_phone.getText().toString().trim())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                } else {
                    countDownTimerUtile = new CountDownTimerUtile(context, tv_set_yzm, 60000, 1000);
                    countDownTimerUtile.start();
                    getPhone(met_set_phone.getText().toString().trim());
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

    /*提交修改手机号*/
    private void isPhoneInfo() {
        if (!AppUtile.isEditText(met_set_phone)) {
            ToastUtils.showShort(context, "手机号不能为空");
        } else if (!AppUtile.isPhone(met_set_phone.getText().toString().trim())) {
            ToastUtils.showShort(context, "手机号格式错误");
        } else if (!AppUtile.isEditText(et_set_phone_yzm)) {
            ToastUtils.showShort(context, "验证码不能为空！");
        } else {
            getYANZHENGMA(met_set_phone.getText().toString().trim());
        }
    }

    /**
     * 验证验证码是否正确
     *
     * @param string
     */
    private void getYANZHENGMA(final String string) {
        final String yzm = et_set_phone_yzm.getText().toString();
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
     * 设置手机号
     */
    private void SubmitModifyPhone(String phone) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.LOGINBINDPHONE)
                .tag(this)
                .params("uid", uid)
                .params("uphone", phone)
                .params("uthirdpartytype",uthirdpartytype)
                .params("uthirdpartyid",uthirdpartyid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(SetPhoneActivity.this, "登录中....");
                    }
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "第三方设置手机号: " + response.body().code);
                        Log.e("text", "第三方设置手机号: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (typelogin.equals("4")) {
                                goToActivity(MainActivity.class, "type", 4);
                                SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                            } else if (typelogin.equals("3")) {
                                goToActivity(MainActivity.class, "type", 3);
                            }
                            finish();
                        } else {
                            ToastUtils.showShort(SetPhoneActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        LoadingDialog.hide();
                    }
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
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
