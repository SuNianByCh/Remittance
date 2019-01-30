package com.yaer.remittance.ui.home_modular.search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.bean.ShopInfoListByKeyBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.NewProductAdapter;
import com.yaer.remittance.ui.home_modular.classification.DataFragment;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 搜索店铺界面
 * Created by ywl on 2016/6/27.
 */
public class SearchShopFragment extends BaseLazyFragment {
    public static final String ARGS_PAGE = "args_page";
    private String mPage;
    @BindView(R.id.rv_search_shop_produc)
    RecyclerView rv_search_shop_produc;
    @BindView(R.id.search_shop_refreshLayout)
    SmartRefreshLayout search_shop_refreshLayout;
    private SearchShopAdapter searchShopAdapter;
    private List<ShopInfoListByKeyBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View LoadingView;
    private View notDataView;
    private View errorView;

    public static SearchShopFragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE, page);
        SearchShopFragment fragment = new SearchShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_searchshop;
    }

    @Override
    protected void initView() {
        LoadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rv_search_shop_produc.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_search_shop_produc.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_search_shop_produc.getParent(), false);
        rv_search_shop_produc.setLayoutManager(new LinearLayoutManager(getActivity()));
        Getshopinfolistbukey(page, pagesize);
        searchShopAdapter = new SearchShopAdapter();
        rv_search_shop_produc.setAdapter(searchShopAdapter);
        showtext();

    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        search_shop_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                search_shop_refreshLayout.getLayout().postDelayed(new Runnable() {
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
                search_shop_refreshLayout.getLayout().postDelayed(new Runnable() {
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
     * 获取搜索店铺信息
     */
    private void Getshopinfolistbukey(final int page, int pagesize) {
        mPage = getArguments().getString(ARGS_PAGE);
        int uid = AppUtile.getUid(getActivity());
       // searchShopAdapter.setEmptyView(LoadingView);
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            searchShopAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<ShopInfoListByKeyBean>>>post(AppApi.BASE_URL + AppApi.GETSHOPINFOLISTBYKEY)
                    .tag(this)
                    .params("key", mPage)
                    .params("uid", uid)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<ShopInfoListByKeyBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<ShopInfoListByKeyBean>>> response) {
                            Log.e("text", "搜索店铺: " + response.body().code);
                            Log.e("text", "搜索店铺: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                Quserylist = response.body().result;
                                if (Quserylist.size() == 0) {
                                    searchShopAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    searchShopAdapter.setNewData(Quserylist);
                                } else if (page > 1 && Quserylist != null && Quserylist.size() > 0) {
                                    searchShopAdapter.addData(Quserylist);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    search_shop_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
