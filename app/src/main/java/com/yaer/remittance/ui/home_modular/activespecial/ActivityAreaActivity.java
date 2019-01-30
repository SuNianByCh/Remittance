package com.yaer.remittance.ui.home_modular.activespecial;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SpecialEventBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ActivityAreaAdapter;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 活动专区
 */
public class ActivityAreaActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    //标题栏
    @BindView(R.id.ct_activity_area)
    CustomTitlebar ct_activity_area;
    //刷新控件
    @BindView(R.id.area_refreshLayout)
    SmartRefreshLayout area_refreshLayout;
    //数据视图
    @BindView(R.id.rv_activity_area)
    RecyclerView rv_activity_area;
    //数据加载体
    private ActivityAreaAdapter activityAreaAdapter;
    //数据集合
    private List<SpecialEventBean> ActivityareaItemList = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /***
     * 页面来源-活动
     * @return
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    public void initView() {
        ct_activity_area.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_activity_area.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_activity_area.getParent(), false);
        rv_activity_area.setLayoutManager(new LinearLayoutManager(this));
        GetAllSpecialEvent(page, pagesize);
        activityAreaAdapter = new ActivityAreaAdapter();
        rv_activity_area.setAdapter(activityAreaAdapter);
        showtext();

    }

    private void showtext() {
        area_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                area_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetAllSpecialEvent(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                area_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetAllSpecialEvent(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_activity_area).init();
    }

    /**
     * 获取活动专区列表
     *
     * @param page
     * @param pagesize
     */
    private void GetAllSpecialEvent(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            activityAreaAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<SpecialEventBean>>>post(AppApi.BASE_URL + AppApi.ALLSPECIALEVENT)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("setype", "2")
                    .execute(new JsonCallback<BaseMode<List<SpecialEventBean>>>(this) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<SpecialEventBean>>> response) {
                            Log.e("text", "专家列表: " + response.body().code);
                            Log.e("text", "专家列表: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                ActivityareaItemList = response.body().result;
                                if (ActivityareaItemList.size() == 0) {
                                    activityAreaAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    activityAreaAdapter.setNewData(ActivityareaItemList);
                                } else if (page > 1 && ActivityareaItemList != null && ActivityareaItemList.size() > 0) {
                                    activityAreaAdapter.addData(ActivityareaItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    area_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(ActivityAreaActivity.this, response.body().msg);
                            }
                        }
                    });
        }
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
