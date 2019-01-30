package com.yaer.remittance.ui.user_modular.user_seller.authentication;

import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 认证中心_专家认证
 */

public class UserExpertAuthenticationActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_expert_authentication)
    CustomTitlebar ct_expert_authentication;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_expert_authentication).init();
    }

    @Override
    public void initView() {
        ct_expert_authentication.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_expert_rz;
    }
    public void performAction(View view) {
        switch (view.getId()){
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
