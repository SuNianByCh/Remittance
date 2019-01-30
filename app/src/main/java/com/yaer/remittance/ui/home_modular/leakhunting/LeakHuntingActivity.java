package com.yaer.remittance.ui.home_modular.leakhunting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.selectBannerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.StringUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 捡漏
 */
public class LeakHuntingActivity extends BaseActivity implements ILeakHuntingView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private LeakHuntingPesenter mLeakHuntingPesenter;
    private LinkedHashMap<String, List<LeakHuntingBean>> reallyData;
    @BindView(R.id.banner_leak)
    Banner banner_leak;
    List<selectBannerBean> mBanner = new ArrayList<>();
    List<String> mImages = new ArrayList<>();
    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_leak_hunting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetBanner();
        mLeakHuntingPesenter = new LeakHuntingPesenter(this);
        mLeakHuntingPesenter.getLeakData();

    }
    /**
     * 获取Banner
     */
    private void GetBanner() {
        OkGo.<BaseMode<List<selectBannerBean>>>post(AppApi.BASE_URL + AppApi.GETBANNER)
                .tag(this)
                .params("bmodel", "1")
                .execute(new JsonCallback<BaseMode<List<selectBannerBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<selectBannerBean>>> response) {
                        Log.e("text", "获取Banner: " + response.body().code);
                        Log.e("text", "获取Banner: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            mBanner = response.body().result;
                            mImages.clear();
                            for (int i = 0; i < mBanner.size(); i++) {
                                String image = mBanner.get(i).getBimg();
                                mImages.add(image);
                            }
                            banner_leak.setImages(mImages)
                                    .setImageLoader(new GlideImageLoader())
                                    .setDelayTime(3000)
                                    .start();
                        } else {
                            ToastUtils.showShort(LeakHuntingActivity.this, response.body().msg);
                        }
                    }
                });
    }
    @Override
    public void initView() {
    }

    @OnClick({R.id.toolbar})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void resultRealListDate(LinkedHashMap<String, List<LeakHuntingBean>> hashMap, ArrayList<String> headerNameList) {
        if (hashMap == null || hashMap.isEmpty() || headerNameList == null || headerNameList.isEmpty()) {
            return;
        }
        List<String> nameList = new ArrayList<>();
        List<LeakFragment> leakFragmentList = new ArrayList<>();
        this.reallyData = hashMap;
        for (String headerName : headerNameList) {
            tabLayout.addTab(tabLayout.newTab());
            nameList.add(time2ReallyName(headerName));
            leakFragmentList.add(LeakFragment.newInstance(headerName));
        }
        CommonTabPagerAdapter adapter = new CommonTabPagerAdapter(getSupportFragmentManager()
                , nameList, leakFragmentList, this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    public List<LeakHuntingBean> getString(String timeName) {
        if (timeName == null || StringUtils.isEmpty(timeName) || reallyData == null) {
            return null;
        } else {
            return reallyData.get(timeName);
        }
    }

    private String time2ReallyName(String time) {
        if (time != null && time.contains(":")) {
            String[] split = time.split(":");

            int hour = Calendar.getInstance().get(Calendar.HOUR);
            if (split[0].equals(("" + hour))) {
                return time + "\n抢购中";
            } else {
                return time + "\n抢购中";
            }
        }
        return null;
    }

    @Deprecated
    private List<String> mapToName(List<String> nameLiset) {
        if (nameLiset != null && !nameLiset.isEmpty()) {
            ArrayList<String> reallyName = new ArrayList<>();
            for (String name : nameLiset) {
                if (!StringUtils.isEmpty(name)) {
                    reallyName.add(time2ReallyName(name));
                }
            }
        }
        return null;
    }

    @Override
    public void showLoading() {
        LoadingDialog.showDialogForLoading(this, null);
    }

    @Override
    public void showMian() {
        LoadingDialog.hide();
    }

    @Override
    protected void onDestroy() {
        LoadingDialog.hide();
        if (mLeakHuntingPesenter != null) {
            mLeakHuntingPesenter.destory();
        }
        super.onDestroy();
    }
}
