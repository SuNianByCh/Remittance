package com.yaer.remittance.ui.home_modular.auctionspecial;

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
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SpecialEventBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ActivityAreaAdapter;
import com.yaer.remittance.ui.adapter.AuctionSpecialAdapter;
import com.yaer.remittance.ui.adapter.ExpertsSayAdapter;
import com.yaer.remittance.ui.home_modular.activespecial.ActivityAreaActivity;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 拍卖专场
 */
public class AuctionSpecialActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_auction_special)
    CustomTitlebar ct_auction_special;
    @BindView(R.id.auction_special_refreshLayout)
    SmartRefreshLayout auction_special_refreshLayout;
    @BindView(R.id.rv_auction_special)
    RecyclerView rv_auction_special;
    private List<SpecialEventBean> auctionItemList = new ArrayList<>();
    private AuctionSpecialAdapter auctionSpecialAdapter;
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_auction_special).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction_special;
    }

    @Override
    public void initView() {
        ct_auction_special.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_auction_special.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_auction_special.getParent(), false);
        rv_auction_special.setLayoutManager(new LinearLayoutManager(this));
        SelectAuctionSpecial(page, pagesize);
        auctionSpecialAdapter = new AuctionSpecialAdapter();
        rv_auction_special.setAdapter(auctionSpecialAdapter);
        showtext();
    }

    private void showtext() {
        auction_special_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                auction_special_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        SelectAuctionSpecial(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                auction_special_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        SelectAuctionSpecial(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void SelectAuctionSpecial(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            auctionSpecialAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<SpecialEventBean>>>post(AppApi.BASE_URL + AppApi.ALLSPECIALEVENT)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("setype", "1")
                    .execute(new JsonCallback<BaseMode<List<SpecialEventBean>>>(this) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<SpecialEventBean>>> response) {
                            Log.e("text", "专家列表: " + response.body().code);
                            Log.e("text", "专家列表: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                auctionItemList = response.body().result;
                                if (auctionItemList.size() == 0) {
                                    auctionSpecialAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    auctionSpecialAdapter.setNewData(auctionItemList);
                                } else if (page > 1 && auctionItemList != null && auctionItemList.size() > 0) {
                                    auctionSpecialAdapter.addData(auctionItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    auction_special_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(AuctionSpecialActivity.this, response.body().msg);
                            }
                        }
                    });
        }
    }


    @Override
    public void initData() {
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
