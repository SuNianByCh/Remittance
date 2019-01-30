package com.yaer.remittance.payment;

import android.view.View;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.ConfirmationOrderActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.ConfirmOrderActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

public class SuccessfulOperationActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_successful_order)
    CustomTitlebar ct_successful_order;
    @BindView(R.id.tv_confirm_success)
    TextView tv_confirm_success;
    private String ordertype;
    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_successful_order).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_successful_operation;
    }

    @Override
    public void initView() {
        ct_successful_order.setAction(this);
        ordertype = getIntent().getStringExtra("ordertype");
    }

    @OnClick({R.id.tv_confirm_success})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm_success:
                if (ordertype.equals("1")) {
                    ConfirmationOrderActivity.confirmationOrderActivity.finish();
                    finish();
                } else if (ordertype.equals("0")) {
                    finish();
                } else if (ordertype.equals("2")) {
                    finish();
                } else if (ordertype.equals("4")) {
                    ConfirmOrderActivity.confirmOrderActivity.finish();
                    finish();
                }
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                if (ordertype.equals("1")) {
                    ConfirmationOrderActivity.confirmationOrderActivity.finish();
                    finish();
                } else if (ordertype.equals("0")) {
                    finish();
                } else if (ordertype.equals("2")) {
                    finish();
                } else if (ordertype.equals("4")) {
                    ConfirmOrderActivity.confirmOrderActivity.finish();
                    finish();
                }
                break;
        }
    }
}
