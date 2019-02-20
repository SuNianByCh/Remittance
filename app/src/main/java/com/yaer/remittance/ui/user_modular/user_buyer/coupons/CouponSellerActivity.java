package com.yaer.remittance.ui.user_modular.user_buyer.coupons;

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
 * 卖家优惠券
 */
public class CouponSellerActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_coupon)
    CustomTitlebar ct_coupon;
    @BindView(R.id.nl_element)
    NavitationLayout navitationLayout;
    @BindView(R.id.viewpagerelement)
    ViewPager viewPager1;
    private String[] titles1 = new String[]{"我的优惠券", "我发布的"};
    private ViewPagerAdapter viewPagerAdapter1;
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_coupon).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initView() {
        ct_coupon.setAction(this);
        fragmentList.add(new MyAddcouponFragment());
        fragmentList.add(new MycouponFragment());
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager1.setAdapter(viewPagerAdapter1);
        navitationLayout.setViewPager(this, titles1, viewPager1, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        navitationLayout.setNavLine(this, 2, R.color.main_tone, 0);

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
            /*case R.id.tv_right:
                goToActivity(AddCouponActivity.class);
                break;*/
        }
    }
}
