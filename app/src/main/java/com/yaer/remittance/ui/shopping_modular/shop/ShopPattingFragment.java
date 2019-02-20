package com.yaer.remittance.ui.shopping_modular.shop;

import android.content.Intent;
import android.os.Bundle;
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
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.home_modular.search.SearchCommodityAdapter;
import com.yaer.remittance.ui.home_modular.search.SearchLotAdapter;
import com.yaer.remittance.ui.home_modular.search.SearchLotFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 店铺拍品
 * Created by ywl on 2016/6/27.
 */
public class ShopPattingFragment extends BaseLazyFragment {
    public static final String ARGS_PAGE = "args_page";
    private String mPage;
    @BindView(R.id.rv_search_lot_produc)
    RecyclerView rv_search_lot_produc;
    @BindView(R.id.search_lot_refreshLayout)
    SmartRefreshLayout search_lot_refreshLayout;
    private SearchLotAdapter searchLotAdapter;
    private List<selectGoodsByNameBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;

    public static ShopPattingFragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE, page);
        ShopPattingFragment fragment = new ShopPattingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_search_lot;
    }

    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_order_view, (ViewGroup) rv_search_lot_produc.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_search_lot_produc.getParent(), false);
        rv_search_lot_produc.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchLotAdapter = new SearchLotAdapter();
        Getshopinfolistbukey(page, pagesize);
        rv_search_lot_produc.setAdapter(searchLotAdapter);
        final Bundle bundle = new Bundle();
        rv_search_lot_produc.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                selectGoodsByNameBean selectGoodsByNameBean = searchLotAdapter.getData().get(position);
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.ll_search_lot_product:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(selectGoodsByNameBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        search_lot_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                search_lot_refreshLayout.getLayout().postDelayed(new Runnable() {
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
                search_lot_refreshLayout.getLayout().postDelayed(new Runnable() {
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
     * 获取搜索拍品信息
     */
    private void Getshopinfolistbukey(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            searchLotAdapter.setEmptyView(errorView);
        } else {
            mPage = getArguments().getString(ARGS_PAGE);
            OkGo.<BaseMode<List<selectGoodsByNameBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSBYSID)
                    .tag(this)
                    .params("sid", mPage)
                    .params("gisauction", "1")
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
                            Log.e("text", "店铺拍品信息: " + response.body().code);
                            Log.e("text", "店铺拍品信息: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                Quserylist = response.body().result;
                                if (Quserylist.size() == 0) {
                                    searchLotAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    searchLotAdapter.setNewData(Quserylist);
                                } else if (page > 1 && Quserylist != null && Quserylist.size() > 0) {
                                    searchLotAdapter.addData(Quserylist);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    search_lot_refreshLayout.finishLoadMoreWithNoMoreData();
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
