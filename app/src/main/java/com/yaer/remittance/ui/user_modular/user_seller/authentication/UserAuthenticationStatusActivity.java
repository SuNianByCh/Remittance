package com.yaer.remittance.ui.user_modular.user_seller.authentication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_seller.enter.UserEnterActivity;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 商家入驻
 */

public class UserAuthenticationStatusActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_authentication_center)
    CustomTitlebar ct_authentication_center;
    //认证状态
    @BindView(R.id.tv_status)
    TextView tv_status;
    //认证描述
    @BindView(R.id.tv_status_title)
    TextView tv_status_title;
    //去认证/去入驻
    @BindView(R.id.btn_dealer_RZsubmit)
    Button btn_dealer_RZsubmit;
    private int personalauthentication;//个人认证状态
    private int enterprisecertification;//企业认证状态
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
            // GetUserInfo();
            isGetData = true;
        }
        super.onResume();
    }

    @Override
    public void initView() {
        ct_authentication_center.setAction(this);
        utoken = (String) SharedPreferencesUtils.getData(this, "uToken", "");
        enterprisecertification = (int) SharedPreferencesUtils.getData(UserAuthenticationStatusActivity.this, "enterprisecertification", 0);
        personalauthentication = (int) SharedPreferencesUtils.getData(UserAuthenticationStatusActivity.this, "personalauthentication", 0);
        if (personalauthentication == 0) {
            if (enterprisecertification == 0) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("对不起，申请商家入驻需要先进行个人或企业认证");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("去认证");
            } else if (enterprisecertification == 3) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("认证失败");
                tv_status_title.setText("对不起，您提交的资料不满足，请重新修改后提交");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("重新认证");
            } else if (enterprisecertification == 2) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("您好，您提交的认证资料正在审核中，请耐心等待");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
            } else if (enterprisecertification == 1) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("审核通过");
                tv_status_title.setText("您好，您已通过企业认证，可进行商家入驻");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("去入驻");
            }
        } else if (personalauthentication == 3) {
            if (enterprisecertification == 3) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("认证失败");
                tv_status_title.setText("对不起，您提交的资料不满足，请重新修改后提交");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("重新认证");
            } else if (enterprisecertification == 0) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("认证失败");
                tv_status_title.setText("对不起，您提交的资料不满足，请重新修改后提交");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("重新认证");
            } else if (enterprisecertification == 2) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("您好，您提交的认证资料正在审核中，请耐心等待");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
            } else if (enterprisecertification == 1) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("审核通过");
                tv_status_title.setText("您好，您已通过企业认证，可进行商家入驻");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("去入驻");
            }
        } else if (personalauthentication == 2) {
            if (enterprisecertification == 2) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("您好，您提交的认证资料正在审核中，请耐心等待");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
            } else if (enterprisecertification == 0) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("您好，您提交的认证资料正在审核中，请耐心等待");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
            } else if (enterprisecertification == 3) {
                tv_status.setVisibility(View.GONE);
                tv_status_title.setText("您好，您提交的认证资料正在审核中，请耐心等待");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
            } else if (enterprisecertification == 1) {
                tv_status.setVisibility(View.VISIBLE);
                tv_status.setText("审核通过");
                tv_status_title.setText("您好，您已通过个人认证，可进行商家入驻");
                btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
                btn_dealer_RZsubmit.setText("去入驻");
            }
        } else if (personalauthentication == 1) {
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setText("审核通过");
            tv_status_title.setText("您好，您已通过个人认证，可进行商家入驻");
            btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
            btn_dealer_RZsubmit.setText("去入驻");
        } else if (enterprisecertification == 1) {
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setText("审核通过");
            tv_status_title.setText("您好，您已通过企业认证，可进行商家入驻");
            btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
            btn_dealer_RZsubmit.setText("去入驻");
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_dealer_RZsubmit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dealer_RZsubmit:
                //入驻
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else if (personalauthentication == 0) {
                    if (enterprisecertification == 0) {
                        goToActivity(UserAuthenticationCenterActivity.class);
                        //goToActivity(UserCompanyAuthenticationActivity.class);//去企业认证
                    } else if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationCenterActivity.class);
                    } else if (enterprisecertification == 2) {
                        ToastUtils.showToast("审核中");
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserEnterActivity.class);
                    }
                } else if (personalauthentication == 1) {
                    goToActivity(UserEnterActivity.class);
                } else if (enterprisecertification == 1) {
                    goToActivity(UserEnterActivity.class);
                } else if (personalauthentication == 2) {
                    if (enterprisecertification == 2) {
                        ToastUtils.showToast("审核中");
                    } else if (enterprisecertification == 0) {
                        ToastUtils.showToast("审核中");
                    } else if (enterprisecertification == 3) {
                        ToastUtils.showToast("审核中");
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserEnterActivity.class);
                    }
                } else if (personalauthentication == 3) {
                    if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationCenterActivity.class);
                    } else if (enterprisecertification == 0) {
                        goToActivity(UserAuthenticationCenterActivity.class);
                    } else if (enterprisecertification == 2) {
                        ToastUtils.showToast("审核中");
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserEnterActivity.class);
                    }
                }
                break;
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_authentication_status;
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
                            SharedPreferencesUtils.saveData(UserAuthenticationStatusActivity.this, "uid", response.body().result.getUid());
                            SharedPreferencesUtils.saveData(UserAuthenticationStatusActivity.this, "enterprisecertification", response.body().result.getEnterprisecertification());//个人认证
                            SharedPreferencesUtils.saveData(UserAuthenticationStatusActivity.this, "personalauthentication", response.body().result.getPersonalauthentication());//个人认证
                        } else {
                            ToastUtils.showShort(UserAuthenticationStatusActivity.this, response.body().msg);
                        }
                    }
                });
    }

}
