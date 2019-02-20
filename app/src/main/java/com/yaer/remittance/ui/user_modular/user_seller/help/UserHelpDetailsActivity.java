package com.yaer.remittance.ui.user_modular.user_seller.help;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 帮助详情界面
 */

public class UserHelpDetailsActivity extends BaseActivity  {
    /*
        @BindView(R.id.ct_help_details)
        CustomTitlebar ct_help_details;
    */
    @BindView(R.id.tv_userdetails_help)
    TextView tv_userdetails_help;
    @BindView(R.id.ll_help_detai)
    RelativeLayout ll_help_detai;
    @BindView(R.id.iv_help_detai_back)
    ImageView iv_help_detai_back;
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
        mImmersionBar.titleBar(R.id.ll_help_detai).init();
    }

    @Override
    public void initView() {
        ParentEntity.HelpCenterInfoModelsBean helpCenterInfoModelsBean = getIntent().getParcelableExtra("data");
        tv_userdetails_help.setText(helpCenterInfoModelsBean.getHctname());
        tv_help_details.setText(helpCenterInfoModelsBean.getHctdesc());
    }


    @Override
    protected int setLayoutId() {
        return R.layout.user_details_help;
    }


    @OnClick({R.id.iv_help_detai_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_help_detai_back:
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
