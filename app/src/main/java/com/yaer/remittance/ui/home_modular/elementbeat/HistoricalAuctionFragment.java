package com.yaer.remittance.ui.home_modular.elementbeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ZeroElementbeat;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ElementAdapter;
import com.yaer.remittance.ui.adapter.HistoricalAuctionAdapter;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.home_modular.headfragment.NewproducFragment;
import com.yaer.remittance.ui.home_modular.headfragment.RecommendFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 历史拍卖
 */
public class HistoricalAuctionFragment extends BaseLazyFragment {
    @BindView(R.id.rv_historical_auction)
    RecyclerView rv_historical_auction;
    private List<String> historicalItemList = new ArrayList<>();
    private HistoricalAuctionAdapter historicalAuctionAdapter;
    @BindView(R.id.historical_refreshLayout)
    SmartRefreshLayout historical_refreshLayout;
    private List<ZeroElementbeat> ElementItemList = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_historical_auction;
    }

    @Override
    public void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_historical_auction.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_historical_auction.getParent(), false);
        rv_historical_auction.setLayoutManager(new LinearLayoutManager(getActivity()));
        selectHistoryGoodsZeroPai(page, pagesize);
        historicalAuctionAdapter = new HistoricalAuctionAdapter();
        rv_historical_auction.setAdapter(historicalAuctionAdapter);
        final Bundle bundle = new Bundle();
        rv_historical_auction.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                ZeroElementbeat zeroElementbeat = historicalAuctionAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_historical_auction:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(zeroElementbeat.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    private void showtext() {
        historical_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                historical_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        selectHistoryGoodsZeroPai(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                historical_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        selectHistoryGoodsZeroPai(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void selectHistoryGoodsZeroPai(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            historicalAuctionAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<ZeroElementbeat>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSPAI)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("gissoldout", 1)
                    .params("gisauction", 1)
                    .execute(new JsonCallback<BaseMode<List<ZeroElementbeat>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<ZeroElementbeat>>> response) {
                            Log.e("text", "获取0元拍: " + response.body().code);
                            Log.e("text", "获取0元拍: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                ElementItemList = response.body().result;
                                if (ElementItemList.size() == 0) {
                                    historicalAuctionAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    historicalAuctionAdapter.setNewData(ElementItemList);
                                } else if (page > 1 && ElementItemList != null && ElementItemList.size() > 0) {
                                    historicalAuctionAdapter.addData(ElementItemList);
                                } else {
                                    historical_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }

    @Override
    protected void initData() {

    }
}
