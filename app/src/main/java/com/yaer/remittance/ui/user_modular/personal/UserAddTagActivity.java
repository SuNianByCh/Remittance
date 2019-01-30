package com.yaer.remittance.ui.user_modular.personal;

import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.FlowLayout;

import butterknife.BindView;

/**
 * Created by zly on 2015/9/19.
 * 设置标签
 */
public class UserAddTagActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_add_tag)
    CustomTitlebar ct_add_tag;
    @BindView(R.id.flow_layout)
    FlowLayout flow_layout;


    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_add_tag).init();
    }

    @Override
    public void initView() {
        ct_add_tag.setAction(this);

    }

    @Override
    public void initData() {

    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_tag;

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

