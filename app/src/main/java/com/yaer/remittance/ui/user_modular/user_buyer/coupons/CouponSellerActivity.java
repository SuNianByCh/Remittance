package com.yaer.remittance.ui.user_modular.user_buyer.coupons;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.home_modular.elementbeat.ElementBeatFragment;
import com.yaer.remittance.ui.home_modular.elementbeat.HistoricalAuctionFragment;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.CouponActivityAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
            case R.id.tv_right:
                goToActivity(AddCouponActivity.class);
                break;
        }
    }
}
