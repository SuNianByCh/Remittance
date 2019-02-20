package com.yaer.remittance.ui.home_modular.auctionspecial;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AuctionSpecialDetailsBean;
import com.yaer.remittance.bean.ZeroElementbeat;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.UserSpecialDetailsAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 拍卖专场详情
 */
public class AuctionSpecialDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_auction_special_list)
    CustomTitlebar ct_auction_special_list;
    @BindView(R.id.auction_list_refreshLayout)
    SmartRefreshLayout auction_list_refreshLayout;
    @BindView(R.id.iv_paimai)
    ImageView iv_paimai;
    @BindView(R.id.rv_auction_special_list)
    RecyclerView rv_auction_special_list;
    private List<AuctionSpecialDetailsBean> auctionspecialList = new ArrayList<>();
    private UserSpecialDetailsAdapter auctionSpecialListAdapter;
    private String seid;
    private String sename;
    private String seimg;
    private View notDataView;
    private View errorView;
    private int page = 1;
    private int pagesize = 10;
    //拍卖件数
  /*  @BindView(R.id.tv_number_auctions)
     TextView tv_number_auctions;*/
/*    @BindView(R.id.tv_auction_time)
    TextView tv_auction_time;*/

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_auction_special_list).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction_special_details;
    }

    @Override
    public void initView() {
        seid = getIntent().getStringExtra("seid");//拍品分类id
        sename = getIntent().getStringExtra("sename");//拍品专场名称
        seimg = getIntent().getStringExtra("seimg");//拍品专场图片
        ct_auction_special_list.setAction(this);
        ct_auction_special_list.setTilte(sename);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_auction_special_list.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_auction_special_list.getParent(), false);
        rv_auction_special_list.setLayoutManager(new LinearLayoutManager(this));
       // auction_list_refreshLayout.setEnableRefresh(false);
        auction_list_refreshLayout.setEnableLoadMore(false);
        SelectAuctionSpecialList(page, pagesize);
        auctionSpecialListAdapter = new UserSpecialDetailsAdapter();
        rv_auction_special_list.setAdapter(auctionSpecialListAdapter);
        final Bundle bundle = new Bundle();
        rv_auction_special_list.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                AuctionSpecialDetailsBean auctionSpecialDetailsBean = auctionSpecialListAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_special_list:
                        if (!NetworkUtils.isNetworkConnected(AuctionSpecialDetailsActivity.this)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(AuctionSpecialDetailsActivity.this).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(auctionSpecialDetailsBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    @Override
    public void initData() {
    }

    private void showtext() {
        auction_list_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                auction_list_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        SelectAuctionSpecialList(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                auction_list_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        SelectAuctionSpecialList(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void SelectAuctionSpecialList(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            auctionSpecialListAdapter.setEmptyView(errorView);
            iv_paimai.setVisibility(View.GONE);
        } else {
            OkGo.<BaseMode<List<AuctionSpecialDetailsBean>>>post(AppApi.BASE_URL + AppApi.GETSPECISLGOODS)
                    .tag(this)
                    .params("seid", seid)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<AuctionSpecialDetailsBean>>>(this) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<AuctionSpecialDetailsBean>>> response) {
                            Log.e("text", "专场列表: " + response.body().code);
//                            Log.e("text", "专场列表: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                auctionspecialList = response.body().result;
                                //tv_number_auctions.setText(String.valueOf(auctionspecialList.size()));
                                if (auctionspecialList.size() == 0) {
                                    auctionSpecialListAdapter.setEmptyView(notDataView);
                                    iv_paimai.setVisibility(View.INVISIBLE);
                                } else {
                                    iv_paimai.setVisibility(View.VISIBLE);
                                    Glide.with(AuctionSpecialDetailsActivity.this).load(seimg).fitCenter().into(iv_paimai);//商品图片
                                }
                                if (page == 1) {
                                    auctionSpecialListAdapter.setNewData(auctionspecialList);
                                } else if (page > 1 && auctionspecialList != null && auctionspecialList.size() > 0) {
                                    auctionSpecialListAdapter.addData(auctionspecialList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    auction_list_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(AuctionSpecialDetailsActivity.this, response.body().msg);
                            }
                        }
                    });
        }
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
