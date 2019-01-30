package com.yaer.remittance.ui.home_modular;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.home_modular.classification.ClassActivity;
import com.yaer.remittance.ui.home_modular.headfragment.GoodshopFragment;
import com.yaer.remittance.ui.home_modular.headfragment.NewproducFragment;
import com.yaer.remittance.ui.home_modular.headfragment.RecommendFragment;
import com.yaer.remittance.ui.home_modular.headfragment.SelectedFragment;
import com.yaer.remittance.ui.home_modular.search.ActivitySearch;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.ShoppingCartActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ClearEditText;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by deng on 2018/5/8
 * 首页
 */

public class HomeFragment extends BaseLazyFragment {
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.bar1)
    NavitationLayout navitationLayout;
    @BindView(R.id.viewpager1)
    ViewPager viewPager1;
    /*分类按钮*/
    @BindView(R.id.ll_class)
    LinearLayout ll_class;
    /*搜索框*/
    @BindView(R.id.ct_home)
    ClearEditText ct_home;
    @BindView(R.id.iv_shopping_cart)
    ImageView iv_shopping_cart;
    private String[] titles1 = new String[]{"推荐", "新品首发", "精选", "优店"};
    private ViewPagerAdapter viewPagerAdapter1;
    private ArrayList<Fragment> fragments1 = new ArrayList<Fragment>();
    private boolean isPrepared = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), ll_home);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .navigationBarColor(R.color.main_tone)
                .init();
    }

    @Override
    protected void initView() {
        fragments1.add(new RecommendFragment());
        fragments1.add(new NewproducFragment());
        fragments1.add(new SelectedFragment());
        fragments1.add(new GoodshopFragment());
        viewPagerAdapter1 = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments1);
        viewPager1.setAdapter(viewPagerAdapter1);
        viewPager1.setOffscreenPageLimit(fragments1.size());
        navitationLayout.setViewPager(getActivity(), titles1, viewPager1, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        navitationLayout.setNavLine(getActivity(), 2, R.color.main_tone, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @OnClick({R.id.ll_class, R.id.ct_home,R.id.iv_shopping_cart})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_class:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                }else{
                    goToActivity(ClassActivity.class);
                }
                break;
            case R.id.ct_home:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                }else{
                    goToActivity(ActivitySearch.class);
                }
                break;
            case R.id.iv_shopping_cart:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(mActivity).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                }else{
                    goToActivity(ShoppingCartActivity.class);
                }
                break;
        }
    }
}