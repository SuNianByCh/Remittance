package com.yaer.remittance.ui.home_modular.headfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.GetMainBean;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.NewproducItemAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新品首发页面
 * Created by ywl on 2016/6/27.
 */
public class NewproducFragment extends BaseLazyFragment {
    /*刷新控件*/
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_new_produc)
    RecyclerView rv_new_produc;
    private NewproducItemAdapter newproducItemAdapter;
    private List<NewGoodsBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_newproduc;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();
    }
    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_new_produc.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_new_produc.getParent(), false);
        rv_new_produc.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        newproducItemAdapter = new NewproducItemAdapter();
        rv_new_produc.setAdapter(newproducItemAdapter);
        GetNewGoods(page, pagesize);
        final Bundle bundle = new Bundle();
        /*为你优选*/
        rv_new_produc.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                NewGoodsBean newGoodsBean = newproducItemAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_search_lot_product:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(newGoodsBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    private void showtext() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetNewGoods(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetNewGoods(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    /**
     * 获取新品
     */
    private void GetNewGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            newproducItemAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<NewGoodsBean>>>post(AppApi.BASE_URL + AppApi.SELECTNEWGOODS)
                    .tag(this)
                    .params("gisauction","1")
                    .params("uid", AppUtile.getUid(getActivity()))
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<NewGoodsBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<NewGoodsBean>>> response) {
                            Log.e("text", "获取新品: " + response.body().code);
                            Log.e("text", "获取新品: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                Quserylist = response.body().result;
                                if (Quserylist.size() == 0) {
                                    newproducItemAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    newproducItemAdapter.setNewData(Quserylist);
                                } else if (page > 1 && Quserylist != null && Quserylist.size() > 0) {
                                    newproducItemAdapter.addData(Quserylist);
                                } else {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
