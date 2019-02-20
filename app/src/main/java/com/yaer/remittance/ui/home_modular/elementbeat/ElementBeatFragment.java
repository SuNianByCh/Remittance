package com.yaer.remittance.ui.home_modular.elementbeat;

import android.content.Intent;
import android.os.Bundle;
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
import com.yaer.remittance.bean.GetMainBean;
import com.yaer.remittance.bean.ZeroElementbeat;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ElementAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.attention.UserAttentionActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 0元拍
 */
public class ElementBeatFragment extends BaseLazyFragment {
    @BindView(R.id.rv_element)
    RecyclerView rv_element;
    @BindView(R.id.element_refreshLayout)
    SmartRefreshLayout element_refreshLayout;
    private List<ZeroElementbeat> ElementItemList = new ArrayList<>();
    private ElementAdapter elementAdapter;
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    //设置沉浸式x
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_element_beat;
    }

    @Override
    public void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_element.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_element.getParent(), false);
        rv_element.setLayoutManager(new LinearLayoutManager(getActivity()));
        selectGoods0Pai(page, pagesize);
        elementAdapter = new ElementAdapter();
        rv_element.setAdapter(elementAdapter);
        final Bundle bundle = new Bundle();
        rv_element.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                ZeroElementbeat zeroElementbeat = elementAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_element_item:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(zeroElementbeat.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });

        showtext();
    }

    private void showtext() {
        element_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                element_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        selectGoods0Pai(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                element_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        selectGoods0Pai(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    private void selectGoods0Pai(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            elementAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<ZeroElementbeat>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSPAI)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("gissoldout", 0)
                    .params("gisauction", 1)
                    .execute(new JsonCallback<BaseMode<List<ZeroElementbeat>>>(getActivity()) {
                        @Override
                        public void onStart(Request<BaseMode<List<ZeroElementbeat>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(mActivity, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<ZeroElementbeat>>> response) {
                            Log.e("text", "获取0元拍: " + response.body().code);
                            Log.e("text", "获取0元拍: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                ElementItemList = response.body().result;
                                if (ElementItemList.size() == 0) {
                                    elementAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    elementAdapter.setNewData(ElementItemList);
                                } else if (page > 1 && ElementItemList != null && ElementItemList.size() > 0) {
                                    elementAdapter.addData(ElementItemList);
                                } else {
                                    element_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<ZeroElementbeat>>> response) {
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

    @Override
    protected void initData() {

    }
}
