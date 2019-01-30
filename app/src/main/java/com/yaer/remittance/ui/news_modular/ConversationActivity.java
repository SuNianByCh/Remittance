package com.yaer.remittance.ui.news_modular;

import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseFragmentActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/*会话界面*/
public class ConversationActivity extends BaseFragmentActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_conversation)
    CustomTitlebar ct_conversation;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_conversation).keyboardEnable(true).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.conversation;
    }

    @Override
    public void initView() {
        ct_conversation.setAction(this);
        ct_conversation.setTilte(getIntent().getData().getQueryParameter("title"));
    }

    @Override
    public void initData() {

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