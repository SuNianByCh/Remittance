package com.yaer.remittance.ui.user_modular.user_buyer.joinlot;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.yaer.remittance.bean.AtAuctionBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.AlreadyFinishAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.AtAuctionAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.DefrayAuctionAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

/**
 * 已结束拍品
 */
public class AlreadyFinishFragment extends BaseLazyFragment {
    /*刷新控件*/
    @BindView(R.id.at_auction_refreshLayout)
    SmartRefreshLayout at_auction_refreshLayout;
    @BindView(R.id.gifview)
    GifImageView gifview;
    @BindView(R.id.rv_at_auction)
    RecyclerView rv_at_auction;
    private AlreadyFinishAdapter finishAuctionAdapter;
    private List<AtAuctionBean> at_auctionlist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private int uid;
    private View notDataView;
    private View errorView;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_at_auction;
    }

    @Override
    public void initView() {
        /*拍卖中*/
        uid = AppUtile.getUid(getActivity());
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_at_auction.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_at_auction.getParent(), false);
        rv_at_auction.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        finishAuctionAdapter = new AlreadyFinishAdapter();
        rv_at_auction.setAdapter(finishAuctionAdapter);
        GetAtAuctionGoods(page, pagesize);
        showtext();
    }

    private void showtext() {
        at_auction_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                at_auction_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetAtAuctionGoods(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                at_auction_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetAtAuctionGoods(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void GetAtAuctionGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            finishAuctionAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AtAuctionBean>>>post(AppApi.BASE_URL + AppApi.GETPARYAKEAUCTION)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("uid", uid)
                    .params("state", 3)
                    .execute(new JsonCallback<BaseMode<List<AtAuctionBean>>>(getActivity()) {

                        @Override
                        public void onSuccess(Response<BaseMode<List<AtAuctionBean>>> response) {
                            Log.e("text", "获取已结束的拍品: " + response.body().code);
                            Log.e("text", "获取已结束的拍品: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                at_auctionlist = response.body().result;
                                if (at_auctionlist.size() == 0) {
                                    finishAuctionAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    finishAuctionAdapter.setNewData(at_auctionlist);
                                } else if (page > 1 && at_auctionlist != null && at_auctionlist.size() > 0) {
                                    finishAuctionAdapter.addData(at_auctionlist);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    at_auction_refreshLayout.finishLoadMoreWithNoMoreData();
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
