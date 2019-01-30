package com.yaer.remittance.ui.user_modular.user_seller.help;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 帮助详情界面
 */

public class UserHelpDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_help_details)
    CustomTitlebar ct_help_details;
    @BindView(R.id.tv_help_details)
    TextView tv_help_details;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_help_details).init();
        ct_help_details.setMinimumWidth(20);
    }

    @Override
    public void initView() {
        ct_help_details.setAction(this);
        ParentEntity.HelpCenterInfoModelsBean helpCenterInfoModelsBean = getIntent().getParcelableExtra("data");
        ct_help_details.setTilte(helpCenterInfoModelsBean.getHctname());
        tv_help_details.setText(helpCenterInfoModelsBean.getHctdesc());
    }


    @Override
    protected int setLayoutId() {
        return R.layout.user_details_help;
    }


    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    public void initData() {
    }

    public static void start(Context mContext, ParentEntity.HelpCenterInfoModelsBean helpCenterInfoModelsBean) {
        if (mContext == null || helpCenterInfoModelsBean == null) {
            return;
        }
        Intent intent = new Intent(mContext, UserHelpDetailsActivity.class);
        intent.putExtra("data", helpCenterInfoModelsBean);
        mContext.startActivity(intent);
    }
}
