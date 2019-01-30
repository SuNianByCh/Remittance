package com.yaer.remittance.ui.shopping_modular.shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.home_modular.search.SearchCommodityAdapter;
import com.yaer.remittance.ui.home_modular.search.SearchLotAdapter;
import com.yaer.remittance.ui.home_modular.search.SearchLotFragment;
import com.yaer.remittance.utils.ToastUtils;

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
        /*为你优选*/
        rv_search_lot_produc.setLayoutManager(new LinearLayoutManager(getActivity()));
        Getshopinfolistbukey(page, pagesize);
        searchLotAdapter = new SearchLotAdapter();
        rv_search_lot_produc.setAdapter(searchLotAdapter);
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

    /**
     * 获取搜索拍品信息
     */
    private void Getshopinfolistbukey(final int page, int pagesize) {
        mPage = getArguments().getString(ARGS_PAGE);
        OkGo.<BaseMode<List<selectGoodsByNameBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSBYSID)
                .tag(this)
                .params("sid", mPage)
                .params("gisauction", "1")
                .params("gissoldout","0")
                .params("page", page)
                .params("pagesize", pagesize)
                .execute(new JsonCallback<BaseMode<List<selectGoodsByNameBean>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<selectGoodsByNameBean>>> response) {
                        Log.e("text", "店铺拍品信息: " + response.body().code);
                        Log.e("text", "店铺拍品信息: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Quserylist = response.body().result;
                            if (page == 1) {
                                searchLotAdapter.setNewData(Quserylist);
                            } else if (page > 1 && Quserylist != null && Quserylist.size() > 0) {
                                searchLotAdapter.addData(Quserylist);
                            } else {
                                ToastUtils.showToast("数据全部加载完毕");
                                search_lot_refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }
}
