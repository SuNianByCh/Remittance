package com.yaer.remittance.ui.home_modular.headfragment;

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
import com.yaer.remittance.bean.SelectGoodsBean;
import com.yaer.remittance.bean.selectBannerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.SelectedAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 精选页面
 * Created by ywl on 2019/6/27.
 */
public class SelectedFragment extends BaseLazyFragment {
    //精选数据
    @BindView(R.id.rv_selected)
    RecyclerView rv_selected;
    //精选banner
    @BindView(R.id.selected_banner)
    Banner selected_banner;
    /*刷新控件*/
    @BindView(R.id.select_refreshLayout)
    SmartRefreshLayout select_refreshLayout;
    //获取banner数据
    List<selectBannerBean> mBanner = new ArrayList<>();
    List<String> mImages = new ArrayList<>();
    private List<SelectGoodsBean> SelectedItemList = new ArrayList<>();
    private SelectedAdapter selectedAdapter;
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;

    @Override
    public void onResume() {
        super.onResume();
        select_refreshLayout.autoRefresh();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_selected;
    }

    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_selected.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_selected.getParent(), false);
        rv_selected.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        selectedAdapter = new SelectedAdapter();
        rv_selected.setAdapter(selectedAdapter);
        GetSelectGoods(page, pagesize);
        GetBanner();
        final Bundle bundle = new Bundle();
        rv_selected.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                SelectGoodsBean selectGoodsBean = selectedAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_select_product:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(selectGoodsBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    private void showtext() {
        select_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                select_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetBanner();
                        GetSelectGoods(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                select_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetSelectGoods(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    /**
     * 获取精选
     */
    private void GetSelectGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            selectedAdapter.setEmptyView(errorView);
            selected_banner.setVisibility(View.INVISIBLE);
        } else {
            OkGo.<BaseMode<List<SelectGoodsBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSCHOICE)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("gisauction", 1)
                    .execute(new JsonCallback<BaseMode<List<SelectGoodsBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<SelectGoodsBean>>> response) {
                            Log.e("text", "获取精选: " + response.body().code);
                            Log.e("text", "获取精选: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                SelectedItemList = response.body().result;
                                if (SelectedItemList.size() == 0) {
                                    selectedAdapter.setEmptyView(notDataView);
                                    selected_banner.setVisibility(View.INVISIBLE);
                                }
                                if (page == 1) {
                                    selectedAdapter.setNewData(SelectedItemList);
                                } else if (page > 1 && SelectedItemList != null && SelectedItemList.size() > 0) {
                                    selectedAdapter.addData(SelectedItemList);
                                } else {
                                    select_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
    }

    /**
     * 获取Banner
     */
    private void GetBanner() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            return;
        } else {
            OkGo.<BaseMode<List<selectBannerBean>>>post(AppApi.BASE_URL + AppApi.GETBANNER)
                    .tag(this)
                    .params("bmodel", 3)
                    .execute(new JsonCallback<BaseMode<List<selectBannerBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<selectBannerBean>>> response) {
                            Log.e("text", "获取Banner: " + response.body().code);
                            Log.e("text", "获取Banner: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                mBanner = response.body().result;
                                mImages.clear();
                                if (mBanner != null) {
                                    for (int i = 0; i < mBanner.size(); i++) {
                                        String image = mBanner.get(i).getBimg();
                                        mImages.add(image);
                                    }
                                }
                                selected_banner.stopAutoPlay();
                                if (mImages != null&&mImages.size()>0)
                                    selected_banner.setImages(mImages)
                                            .setImageLoader(new GlideImageLoader())
                                            .setDelayTime(3000)
                                            .start();
                            } else

                            {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
