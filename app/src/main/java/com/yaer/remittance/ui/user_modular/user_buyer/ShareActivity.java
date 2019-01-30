package com.yaer.remittance.ui.user_modular.user_buyer;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分享
 */
public class ShareActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener{
    @BindView(R.id.ll_touchzixunshare)
    LinearLayout ll_touchzixunshare;
    @BindView(R.id.tv_zixunsharecancle)
    TextView tv_zixunsharecancle;
    @BindView(R.id.iv_wechat)
    ImageView iv_wechat;
    @BindView(R.id.iv_wechatMoments)
    ImageView iv_wechatMoments;
    @BindView(R.id.iv_QQ)
    ImageView iv_QQ;
    @BindView(R.id.iv_qqZone)
    ImageView iv_qqZone;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_touchzixunshare,R.id.tv_zixunsharecancle,R.id.iv_wechat,R.id.iv_wechatMoments,R.id.iv_QQ,R.id.iv_qqZone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_touchzixunshare:
                finish();
                break;
            case R.id.tv_zixunsharecancle:
                finish();
                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_wechatMoments:
                break;
            case R.id.iv_QQ:
                break;
            case R.id.iv_qqZone:
                break;
        }
    }

    @Override
    public void performAction(View view) {

    }
}
