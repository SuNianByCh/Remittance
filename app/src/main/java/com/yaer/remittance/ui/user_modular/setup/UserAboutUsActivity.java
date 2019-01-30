package com.yaer.remittance.ui.user_modular.setup;

import android.view.View;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.bean.QueryCollectAddressBean;
import com.yaer.remittance.ui.adapter.UserAddressAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 关于我们
 */
public class UserAboutUsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_about_us)
    CustomTitlebar ct_about_us;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_about_us).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_about_us;
    }


    @Override
    protected void onResume() {
        super.onResume();
        ct_about_us.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
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
