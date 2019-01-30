package com.yaer.remittance.ui.home_modular.elementbeat;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.home_modular.headfragment.NewproducFragment;
import com.yaer.remittance.ui.home_modular.headfragment.RecommendFragment;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 0元拍
 */
public class ElementBeatActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_element)
    CustomTitlebar ct_element;
    @BindView(R.id.nl_element)
    NavitationLayout navitationLayout;
    @BindView(R.id.viewpagerelement)
    ViewPager viewPager1;
    private String[] titles1 = new String[]{"0元拍", "历史拍卖"};
    private ViewPagerAdapter viewPagerAdapter1;
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_element_beat;
    }

    @Override
    public void initView() {
        ct_element.setAction(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_element).init();
    }

    @Override
    public void initData() {
        fragmentList.add(new ElementBeatFragment());
        fragmentList.add(new HistoricalAuctionFragment());
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager1.setAdapter(viewPagerAdapter1);
        navitationLayout.setViewPager(this, titles1, viewPager1, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        navitationLayout.setNavLine(this, 2, R.color.main_tone, 0);
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
