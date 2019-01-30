package com.yaer.remittance.ui.user_modular.user_seller.auctionmanagement;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.eventmessage.TypeMessage;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.utils.WsbPopUtile;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.bar.NavitationLayout;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AddCollectionActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 藏品管理
 */
public class AuctionActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_auction)
    CustomTitlebar ct_auction;
    @BindView(R.id.nl_auction)
    NavitationLayout navitationLayout;
    @BindView(R.id.viewpagerelement)
    ViewPager viewPager1;
    private String[] titles1 = new String[]{"在售", "下架"};
    private ViewPagerAdapter viewPagerAdapter1;
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    @BindView(R.id.ll_commodity_auction)
    LinearLayout ll_commodity_auction;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction;
    }

    @Override
    public void initView() {
        ct_auction.setAction(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_auction).init();
    }

    @Override
    public void initData() {
        fragmentList.add(new OnSaleFragment());
        fragmentList.add(new LowerFrameFragment());
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
            case R.id.tv_right:
                WsbPopUtile.getInstence(this).showTalkCommAuction(ll_commodity_auction);
                //goToActivity(AddCollectionActivity.class);
                break;
        }
    }
}
