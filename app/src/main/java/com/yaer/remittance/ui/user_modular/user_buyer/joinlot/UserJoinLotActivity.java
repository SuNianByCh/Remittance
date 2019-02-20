package com.yaer.remittance.ui.user_modular.user_buyer.joinlot;

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
 * 藏品管理
 */
public class UserJoinLotActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_joinlot)
    CustomTitlebar ct_joinlot;
    @BindView(R.id.nl_joinlot)
    NavitationLayout nl_joinlot;
    @BindView(R.id.viewpagerelement)
    ViewPager viewPager1;
    private String[] titles1 = new String[]{"拍卖中", "待支付", "已结束"};
    private ViewPagerAdapter viewPagerAdapter1;
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_join_lot;
    }

    @Override
    public void initView() {
        ct_joinlot.setAction(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_joinlot).init();
    }

    @Override
    public void initData() {
        fragmentList.add(new AtAuctionFragment());
        fragmentList.add(new DefrayAuctionFragment());
        fragmentList.add(new AlreadyFinishFragment());
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager1.setAdapter(viewPagerAdapter1);
        nl_joinlot.setViewPager(this, titles1, viewPager1, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        nl_joinlot.setNavLine(this, 2, R.color.main_tone, 0);
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
