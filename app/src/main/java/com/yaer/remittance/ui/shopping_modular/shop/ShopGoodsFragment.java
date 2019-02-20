package com.yaer.remittance.ui.shopping_modular.shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.home_modular.search.SearchCommodityAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 店铺商品
 * Created by ywl on 2016/6/27.
 */
public class ShopGoodsFragment extends BaseLazyFragment {
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
    private View notDataView;
    private View errorView;


    public static ShopGoodsFragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE, page);
        ShopGoodsFragment fragment = new ShopGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_search_commodity;
    }

    @Override
    protected void initView() {
        /*为你优选*/
        notDataView = getLayoutInflater().inflate(R.layout.empty_order_view, (ViewGroup) rv_search_commodity_produc.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_search_commodity_produc.getParent(), false);
        rv_search_commodity_produc.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        searchCommodityAdapter = new SearchCommodityAdapter();
        rv_search_commodity_produc.setAdapter(searchCommodityAdapter);
        Getshopinfolistbukey(page, pagesize);
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

    private LoadingDialog2 photoDiloag;

    /**
     * 获取搜索商品信息
     */
    private void Getshopinfolistbukey(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            searchCommodityAdapter.setEmptyView(errorView);
        } else {
            mPage = getArguments().getString(ARGS_PAGE);
            OkGo.<BaseMode<List<selectGoodsByNameBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSBYSID)
                    .tag(this)
                    .params("sid", mPage)
                    .params("gisauction", "0")
                    .params("gissoldout", "0")
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<selectGoodsByNameBean>>>(getActivity()) {
                        @Override
                        public void onStart(Request<BaseMode<List<selectGoodsByNameBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(getActivity(), "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<selectGoodsByNameBean>>> response) {
                            Log.e("text", "店铺商品信息: " + response.body().code);
                            Log.e("text", "店铺商品信息: " + response.body().result);
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
                                    search_commodity_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                                stopDialog();
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                                stopDialog();
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<selectGoodsByNameBean>>> response) {
                            super.onError(response);
                            stopDialog();
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            stopDialog();
                        }
                    });
        }
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }
}
