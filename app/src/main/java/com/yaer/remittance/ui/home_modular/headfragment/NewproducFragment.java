package com.yaer.remittance.ui.home_modular.headfragment;

import android.support.v7.widget.GridLayoutManager;
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
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.NewproducItemAdapter;
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
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_new_produc.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_new_produc.getParent(), false);
        rv_new_produc.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        newproducItemAdapter = new NewproducItemAdapter();
        rv_new_produc.setAdapter(newproducItemAdapter);
        GetNewGoods(page, pagesize);
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
                }, 2000);
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
                }, 1000);
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
                                    ToastUtils.showToast("数据全部加载完毕");
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
