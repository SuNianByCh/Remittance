package com.yaer.remittance.ui.home_modular.headfragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.yaer.remittance.bean.GoodshopBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.GoodshopAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 优店界面
 */
public class GoodshopFragment extends BaseLazyFragment {
    //优店页面视图
    @BindView(R.id.rv_good_shop)
    RecyclerView rv_good_shop;
    //店铺集合
    private List<GoodshopBean> GoodshopItemList = new ArrayList<>();
    @BindView(R.id.good_shop_refreshLayout)
    SmartRefreshLayout good_shop_refreshLayout;
    //数据加载体
    private GoodshopAdapter goodshopAdapter;
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;
    private boolean isfollow;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    /***
     * 设置页面来源-优店
     * @return
     */
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_good_shop;
    }

    /***
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_good_shop.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_good_shop.getParent(), false);
        rv_good_shop.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        goodshopAdapter = new GoodshopAdapter();
        rv_good_shop.setAdapter(goodshopAdapter);
        GoodshopShow(page, pagesize);
        rv_good_shop.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                int itemViewId = view.getId();
                GoodshopBean goodshopBeanitem = goodshopAdapter.getData().get(position);
                switch (itemViewId) {
                    case R.id.ll_goodshop_follow:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            isfollow = goodshopAdapter.getData().get(position).isFollowStatus();
                            String sid = goodshopAdapter.getData().get(position).getSid();
                            UpdateShopFollow(position, sid);
                        }
                        break;
                    case R.id.rl_goodshop_id:
                        Intent intent = new Intent(getActivity(), ShopActivity.class);
                        intent.putExtra("shopinfosid", goodshopBeanitem.getSid());
                        startActivity(intent);
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
        good_shop_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                good_shop_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GoodshopShow(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                good_shop_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GoodshopShow(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    /**
     * 店铺关注 已关注/未关注
     */
    private void UpdateShopFollow(final int postion, String sid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPFOLLOW)
                .tag(this)
                .params("uid", AppUtile.getUid(mActivity))
                .params("sid", sid)
                .params("follow", isfollow ? "0" : "1")
                .params("token", AppUtile.getTicket(mActivity))
                .execute(new JsonCallback<BaseMode>(mActivity) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(getActivity(), "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        GoodshopBean item = goodshopAdapter.getItem(postion);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if(item != null){
                                item.setFollowStatus(!isfollow);
                                goodshopAdapter.notifyItemChanged(postion);
                            }
                            if (isfollow) {
                                ToastUtils.showToast("取消关注");
                            } else {
                                ToastUtils.showToast("关注成功");
                            }
                            stopDialog();
                          /*  if (isfollow == true) {
                                tv_sfans.setText("+关注");
                                ToastUtils.showToast("取消关注");
                            } else {
                                tv_sfans.setText("已关注");
                                ToastUtils.showToast("关注成功");
                            }
                            GoodshopShow(page, pagesize);*/
                        } else {
                            ToastUtils.showShort(mActivity, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
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
    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    /**
     * AppApi.BASE_URL + AppApi.GETSHOPINFOMAXFANS
     * 获取优店
     */
    private void GoodshopShow(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            goodshopAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<GoodshopBean>>>post(AppApi.BASE_URL + AppApi.GETSHOPINFOMAXFANS)
                    .tag(this)
                    .params("uid", AppUtile.getUid(mActivity))
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("goodscount", "6")
                    .execute(new JsonCallback<BaseMode<List<GoodshopBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<GoodshopBean>>> response) {
                            Log.e("text", "获取优店: " + response.body().code);
                            Log.e("text", "获取优店: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                GoodshopItemList = response.body().result;
                                if (GoodshopItemList.size() == 0) {
                                    goodshopAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    goodshopAdapter.setNewData(GoodshopItemList);
                                } else if (page > 1 && GoodshopItemList != null && GoodshopItemList.size() > 0) {
                                    goodshopAdapter.addData(GoodshopItemList);
                                } else {
                                    good_shop_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
