package com.yaer.remittance.ui.user_modular.user_seller.authentication;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 认证中心首页
 */

public class UserAuthenticationCenterActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_authentication_center)
    CustomTitlebar ct_authentication_center;
    //企业认证
    @BindView(R.id.ll_company_identification)
    LinearLayout ll_company_identification;
    //个人认证
    @BindView(R.id.ll_personal_identification)
    LinearLayout ll_personal_identification;
    /*//专家认证
    @BindView(R.id.ll_expert_identification)
    LinearLayout ll_expert_identification;*/
    /*用户标识utoken*/
    private String utoken;
    private boolean isGetData = false;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_authentication_center).init();
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void onResume() {
        if (!isGetData) {
            //   这里可以做网络请求或者需要的数据刷新操作
            GetUserInfo();
            isGetData = true;
        }
        super.onResume();
    }

    @Override
    public void initView() {
        ct_authentication_center.setAction(this);
        utoken = (String) SharedPreferencesUtils.getData(this, "uToken", "");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_company_identification, R.id.ll_personal_identification,})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_company_identification:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else {
                    goToActivity(UserCompanyAuthenticationActivity.class);
                }
                break;
            case R.id.ll_personal_identification:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else {
                    goToActivity(UserPersonalAuthenticationActivity.class);
                }
                break;
        /*    case R.id.ll_expert_identification:
                goToActivity(UserExpertAuthenticationActivity.class);
                break;*/
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_authentication_center;
    }

    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    /**
     * 获取用户信息
     */
    public void GetUserInfo() {
        OkGo.<BaseMode<UserBean>>post(AppApi.BASE_URL + AppApi.GETUSERINFO)
                .tag(this)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode<UserBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<UserBean>> response) {
                        Log.e("text", "获取用户信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(UserAuthenticationCenterActivity.this, "uid", response.body().result.getUid());
                            SharedPreferencesUtils.saveData(UserAuthenticationCenterActivity.this, "personalauthentication", response.body().result.getPersonalauthentication());//个人认证
                        } else {
                            ToastUtils.showShort(UserAuthenticationCenterActivity.this, response.body().msg);
                        }
                    }
                });
    }

}
