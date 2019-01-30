package com.yaer.remittance.ui.home_modular.auctiondetails;

import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 拍卖规则
 * Created by ywl on 2016/6/27.
 */
public class AuctionRuleActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_auction_rule)
    CustomTitlebar ct_auction_rule;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        ct_auction_rule.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_auction_rule).init();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction_rule;
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
