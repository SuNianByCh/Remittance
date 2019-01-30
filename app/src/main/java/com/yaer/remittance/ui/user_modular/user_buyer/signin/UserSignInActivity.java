package com.yaer.remittance.ui.user_modular.user_buyer.signin;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SignInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 签到
 * Created by ywl on 2016/6/27.
 */
public class UserSignInActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_sign_in)
    CustomTitlebar ct_sign_in;
    /*签到天数*/
    @BindView(R.id.sign_in)
    TextView sign_in;
    /*签到获取的积分*/
    @BindView(R.id.tv_cumulative_integral)
    TextView tv_cumulative_integral;
    /*点击签到按钮*/
    @BindView(R.id.ll_obtain_sign_in)
    LinearLayout ll_obtain_sign_in;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        ct_sign_in.setAction(this);
        getsifninf();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_sign_in).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_sign_in;
    }
    /**
     * 获取签到信息*
     */
    public void getsifninf() {
        String uid = String.valueOf(AppUtile.getUid(this));
        OkGo.<BaseMode<SignInfoBean>>post(AppApi.BASE_URL + AppApi.GETSIFNINFO)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<SignInfoBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<SignInfoBean>> response) {
                        Log.e("text", "签到: " + response.body().code);
                        Log.e("text", "签到: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (response.body().result != null) {
                                sign_in.setText("已连续签到" + String.valueOf(response.body().result.getSday()) + "天");
                                tv_cumulative_integral.setText("累积共" + String.valueOf(response.body().result.getUintegral()) + "积分");
                            } else {
                                sign_in.setText("已连续签到" + String.valueOf(response.body().result.getSday()) + "天");
                                tv_cumulative_integral.setText("累积共" + String.valueOf(response.body().result.getUintegral()) + "积分");

                            }
                        } else {
                            ToastUtils.showShort(UserSignInActivity.this, response.body().msg);
                        }
                    }
                });
    }


    @OnClick({R.id.ll_obtain_sign_in})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_obtain_sign_in:
                dosifn();
                break;
        }
    }

    /**
     * 签到*
     */
    public void dosifn() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DOSIFN)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "签到: " + response.body().code);
                        Log.e("text", "签到: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            getsifninf();
                            ToastUtils.showShort(UserSignInActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(UserSignInActivity.this, response.body().msg);
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

        }
    }
}
