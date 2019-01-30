package com.yaer.remittance.ui.user_modular.user_buyer.integral;

import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *积分规则
 * Created by ywl on 2016/6/27.
 */
public class UserIntegralRuleActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_integral_rule)
    CustomTitlebar ct_integral_rule;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        ct_integral_rule.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_integral_rule).init();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_integral_rule;
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }
    }
    @Override
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
