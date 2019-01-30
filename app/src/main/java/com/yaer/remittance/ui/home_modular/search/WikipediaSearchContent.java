package com.yaer.remittance.ui.home_modular.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.home_modular.leakhunting.CommonTabPagerAdapter;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索内容
 */
public class WikipediaSearchContent extends BaseActivity {
    //private List<String> datas = new ArrayList<>();
    private CommonTabPagerAdapter adapter;
    @BindView(R.id.rl_search_wikipedia_title)
    LinearLayout rl_search_wikipedia_title;
    @BindView(R.id.wikipedia_viewPager)
    ViewPager viewPager;
    @BindView(R.id.wikipedia_tabLayout)
    NavitationLayout tabLayout;
    @BindView(R.id.et_search_input)
    EditText et_search_input;
    @BindView(R.id.tv_search_cancel)
    TextView tv_search_cancel;
    private String[] titles1 = new String[]{"商品", "拍品", "店铺"};
    private ViewPagerAdapter viewPagerAdapter1;
    private ArrayList<Fragment> fragments1 = new ArrayList<Fragment>();//用于存放Fragment的集合

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.rl_search_wikipedia_title).init();
    }

    @Override
    public void initParms(Bundle parms) {
        /* MobclickAgent.onEvent(this, "SearchEncyclopediaActivity");*/
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wikipedia_search;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String searchcount = intent.getStringExtra("title");
        et_search_input.setText(searchcount);
        fragments1.add(SearchCommodityFragment.newInstance(searchcount));//商品
        fragments1.add(SearchLotFragment.newInstance(searchcount));//拍品
        fragments1.add(SearchShopFragment.newInstance(searchcount));//店铺
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragments1);
        viewPager.setAdapter(viewPagerAdapter1);
        tabLayout.setViewPager(this, titles1, viewPager, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        tabLayout.setNavLine(this, 2, R.color.main_tone, 0);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_search_cancel, R.id.et_search_input})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_cancel:
                finish();
                break;
            case R.id.et_search_input:
                finish();
                break;
        }
    }
}
