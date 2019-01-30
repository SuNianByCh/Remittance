package com.yaer.remittance.ui.home_modular.classification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.CategoryBean;
import com.yaer.remittance.bean.GoodsClassModelsListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.MyPagerAdapter;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类筛选
 */
public class ClassificationScreeningActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, CustomTitlebar.TitleBarOnClickListener {
    private static final String TAG = "";
    @BindView(R.id.ct_classification)
    CustomTitlebar ct_classification;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<String> datas = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter adapter;
    private int gcid;
    private int mSid;
    private String scid;
    public List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> mapList = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_classification_screening;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_classification).init();
    }

    @Override
    public void initView() {
        ct_classification.setAction(this);
        mapList = new ArrayList<>();
        Intent intent = this.getIntent();
        //mapList = (List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean>) intent.getSerializableExtra("goodsclassList");
        Bundle bundle = intent.getExtras();
        // String strContentString = bundle.getString("name");
        String scname = getIntent().getStringExtra("name");
        ct_classification.setTilte(scname);
        String sID = getIntent().getStringExtra("gcid");
        scid = getIntent().getStringExtra("scid");
        mSid = Integer.parseInt(sID);
        getGoodsClassIFicationModels(scid);
    }

    @Override
    public void initData() {

    }

    //获取分类集合
    private List<GoodsClassModelsListBean> goodsification = new ArrayList<>();
    List<String> listname = new ArrayList<>();

    /**
     * 获取商品二级分类
     */
    private void getGoodsClassIFicationModels(final String scid) {
        OkGo.<BaseMode<List<GoodsClassModelsListBean>>>post(AppApi.BASE_URL + AppApi.GETGOODSCLASSMODELS)
                .tag(this)
                .params("scid", scid)
                .execute(new JsonCallback<BaseMode<List<GoodsClassModelsListBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<GoodsClassModelsListBean>>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            GoodsClassModelsListBean goodsClassModelsListBean = new GoodsClassModelsListBean();
                            goodsification = response.body().result;
                            goodsClassModelsListBean.setGcname("全部");
                            goodsClassModelsListBean.setScid(Integer.parseInt(scid));//改了这个参数
                            goodsification.add(0, goodsClassModelsListBean);
                            if (!goodsification.equals("") && goodsification.size() > 0) {
                                /*循环添加标签*/
                                for (int i = 0; i < goodsification.size(); i++) {
                                    Log.e(TAG, "initView: " + goodsification.get(i).getGcname());
                                    datas.add(goodsification.get(i).getGcname());
                                }
                                for (int i = 0; i < goodsification.size(); i++) {
                                    if (mSid == goodsification.get(i).getGcid()) {
                                        gcid = i;
                                    }
                                }
                                //循环注入标签
                                for (int i = 0; i < datas.size(); i++) {
                                    Log.e(TAG, "initView: " + datas.get(i));
                                    tabLayout.addTab(tabLayout.newTab().setText(datas.get(i)));
                                }
                                //设置TabLayout点击事件
                                for (int i = 0; i < datas.size(); i++) {
                                    fragments.add(DataFragment.newInstance(goodsification.get(i).getGcid(),goodsification.get(i).getScid()));
                                }
                                tabLayout.addOnTabSelectedListener(ClassificationScreeningActivity.this);
                                adapter = new MyPagerAdapter(getSupportFragmentManager(), datas, fragments);
                                viewPager.setAdapter(adapter);
                                tabLayout.setupWithViewPager(viewPager);
                                viewPager.setCurrentItem(gcid);
                                tabLayout.getTabAt(gcid).select();
                            }
                        } else {
                            ToastUtils.showShort(ClassificationScreeningActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
