package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 余额明细
 */
public class SellerFineBalanceActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_fine_balance)
    CustomTitlebar ct_fine_balance;
    @BindView(R.id.nl_fine_balance)
    NavitationLayout nl_fine_balance;
    @BindView(R.id.viewpagerelement)
    ViewPager viewPager1;
    private String[] titles1 = new String[]{"充值", "提现","平台收入","冻结","手续费","保证金"};
    private ViewPagerAdapter viewPagerAdapter1;
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_fine_balance;
    }

    @Override
    public void initView() {
        ct_fine_balance.setAction(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_fine_balance).init();
    }
    /*   private DetailedRechargeFragment;//充值
       private DetailedCashwithdrawalFragment;//提现
       private DetailedConsumptionFragment;//消费
       private  DetailedPlatformRevenueFragment;//平台收入
       private  DetailedFrozenFragment;//冻结
       private  DetailedRefundFragment;//退款*/
    @Override
    public void initData() {
        fragmentList.add(new DetailedRechargeFragment());//充值
        fragmentList.add(new DetailedCashwithdrawalFragment());//提现
        fragmentList.add(new DetailedPlatformRevenueFragment());//平台收入
        fragmentList.add(new DetailedFrozenFragment());//冻结
        fragmentList.add(new DetailedServiceChargeFragment());//手续费
        fragmentList.add(new DetailedBondFragment());//保证金
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager1.setAdapter(viewPagerAdapter1);
        nl_fine_balance.setViewPager(this, titles1, viewPager1, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 5, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        nl_fine_balance.setNavLine(this, 2, R.color.main_tone, 0);
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
