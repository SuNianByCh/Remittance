package com.yaer.remittance.ui.home_modular.search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ShopInfoListByKeyBean;
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.SelectedAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.SpaceItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 搜索商品
 * Created by ywl on 2016/6/27.
 */
public class SearchCommodityFragment extends BaseLazyFragment {
    public static final String ARGS_PAGE = "args_page";
    private String mPage;
    @BindView(R.id.rv_search_commodity_produc)
    RecyclerView rv_search_commodity_produc;
    @BindView(R.id.search_commodity_refreshLayout)
    SmartRefreshLayout search_commodity_refreshLayout;
    private SearchCommodityAdapter searchCommodityAdapter;
    private List<selectGoodsByNameBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View LoadingView;
    private View notDataView;
    private View errorView;

    public static SearchCommodityFragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE, page);
        SearchCommodityFragment fragment = new SearchCommodityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_search_commodity;
    }

    @Override
    protected void initView() {
        LoadingView = getActivity().getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rv_search_commodity_produc.getParent(), false);
        notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_search_commodity_produc.getParent(), false);
        errorView = getActivity().getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_search_commodity_produc.getParent(), false);
        /*搜索商品*/
        rv_search_commodity_produc.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Getshopinfolistbukey(page, pagesize);
        searchCommodityAdapter = new SearchCommodityAdapter();
        rv_search_commodity_produc.setAdapter(searchCommodityAdapter);
        showtext();
    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        search_commodity_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                search_commodity_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        Getshopinfolistbukey(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                search_commodity_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Getshopinfolistbukey(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initData() {
    }

    /**
     * 获取搜索商品信息
     */
    private void Getshopinfolistbukey(final int page, int pagesize) {
        //searchCommodityAdapter.setEmptyView(LoadingView);
        mPage = getArguments().getString(ARGS_PAGE);
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            searchCommodityAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<selectGoodsByNameBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSBYNAME)
                    .tag(this)
                    .params("gname", mPage)
                    .params("gisauction", "0")
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<selectGoodsByNameBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<selectGoodsByNameBean>>> response) {
                            Log.e("text", "搜索商品信息: " + response.body().code);
                            Log.e("text", "搜索商品信息: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                Quserylist = response.body().result;
                                if (Quserylist.size() == 0) {
                                    searchCommodityAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    searchCommodityAdapter.setNewData(Quserylist);
                                } else if (page > 1 && Quserylist != null && Quserylist.size() > 0) {
                                    searchCommodityAdapter.addData(Quserylist);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    search_commodity_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
