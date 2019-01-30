package com.yaer.remittance.ui.user_modular.setup;

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
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.UIAlertView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/16.
 * 设置密码
 */
public class SetPswActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_set_pwd)
    CustomTitlebar ct_set_pwd;
    @BindView(R.id.psw_1_)
    EditText psw_1_;
    @BindView(R.id.psw_2_)
    EditText psw_2_;
    private ImageView chang_first_img, chang_two_img;
    private TextView quick_login;
    private boolean firstBoolean = false;
    private boolean twoBoolean = false;
    private String stri_md5 = "";
    /*获取时间戳*/
    private long timeStamp;
    /*绑定token值*/
    private String utoken;


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
    protected int setLayoutId() {
        return R.layout.activity_set_psw;
    }

    @Override
    public void initView() {
        ct_set_pwd.setAction(this);
        utoken =AppUtile.getTicket (this);
    }

    @OnClick({R.id.quick_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quick_login:
                ispwd();
                break;
        }
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

    /*判断密码*/
    private void ispwd() {
        String psw1 = psw_1_.getText().toString().trim();
        String psw2 = psw_2_.getText().toString().trim();
        if (!AppUtile.isEditText(psw_1_)) {
            ToastUtils.showShort(context, "密码不能为空！");
        } else if (!AppUtile.isPsw(psw_1_.getText().toString().trim())) {
            ToastUtils.showShort(context, "密码格式为数字和字母组合");
        } else if (psw_1_.getText().toString().trim().length() < 6 || psw_1_.getText().toString().trim().length() > 20) {
            ToastUtils.showToast("密码长度为6-20位");
        } else if (!AppUtile.isEditText(psw_2_)) {
            ToastUtils.showShort(context, "确认密码不能为空！");
        } else if (!psw1.equals(psw2)) {
            ToastUtils.showShort(context, "两次密码不一致！");
        } else {
            upPassword(psw1);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                showDelDialog();
                break;
        }
    }

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(SetPswActivity.this, "温馨提示", "您确定要返回吗?  返回将无法登陆",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           finish();
                                       }
                                   }
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDelDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
