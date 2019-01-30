package com.yaer.remittance.ui.login_modular;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.X5WebView;

import butterknife.BindView;

/*封装协议*/
public class AgreementActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_agreement)
    CustomTitlebar ct_agreement;
    @BindView(R.id.xwebview_agreement)
    X5WebView xwebview_agreement;
    private String url;
    private String title;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_agreement).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_agreement;
    }

    @Override
    public void initView() {
        ct_agreement.setAction(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = getIntent().getStringExtra("agreementurl");
            title = getIntent().getStringExtra("agreementtitle");
        }
        ct_agreement.setTilte(title);
        xwebview_agreement.loadUrl(url);
    }

    /**
     * 返回键监听
     *
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (xwebview_agreement != null && xwebview_agreement.canGoBack()) {
                xwebview_agreement.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        //释放资源
        if (xwebview_agreement != null)
            xwebview_agreement.destroy();
        super.onDestroy();
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
