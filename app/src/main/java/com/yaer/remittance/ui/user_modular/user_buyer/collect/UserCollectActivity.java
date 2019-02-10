package com.yaer.remittance.ui.user_modular.user_buyer.collect;

import android.support.v7.widget.GridLayoutManager;
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
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.GetFollowGoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收藏
 */
public class UserCollectActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_collect)
    CustomTitlebar ct_my_collect;
    /*刷新控件*/
    @BindView(R.id.collect_refreshLayout)
    SmartRefreshLayout collect_refreshLayout;
    private UserCollectAdapter collectAdapter;
    private List<GetFollowGoodsBean> collectItemList = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    @BindView(R.id.rv_collect)
    RecyclerView rv_collect;
    private int uid;
    private boolean isfollw;
    private String follow;
    private View notDataView;
    private View errorView;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_collect;
    }

    @Override
    public void initView() {
        ct_my_collect.setAction(this);
        uid = AppUtile.getUid(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_collect.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_collect.getParent(), false);
        rv_collect.setLayoutManager(new GridLayoutManager(this, 1));
        GetFollowGoods(page, pagesize);
        collectAdapter = new UserCollectAdapter();
        rv_collect.setAdapter(collectAdapter);
        rv_collect.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    /*取消收藏*/
                    case R.id.tv_detils_NumS:
                        // GetFollowGoodsBean getFollowGoodslist = collectAdapter.getData().get(position);
                        isfollw = collectItemList.get(position).getFollowStatus();
                        String gid = collectItemList.get(position).getGid();
                        showDelDialog(position, gid);
                        break;
                }
            }
        });
        showtext();
    }

    private void showDelDialog(final int position, final String gid) {
        final UIAlertView delDialog = new UIAlertView(UserCollectActivity.this, "温馨提示", "确认要取消收藏吗",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           follow = "0";
                                           UpdateShopFollow(follow, position, gid);
                                       }
                                   }
        );
    }

    /**
     * 修改
     */
    private void UpdateShopFollow(String follow, final int postion, String gid) {

        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDSTEGOODSFOLLOW)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .params("gid", gid)
                .params("follow", follow)
                .params("token", AppUtile.getTicket(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserCollectActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "商品收藏: " + response.body().code);
                        Log.e("text", "商品收藏: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            collectItemList.remove(postion);
                            collectAdapter.notifyItemRemoved(postion);
                            //collectAdapter.notifyItemChanged(postion);
                            // GetFollowGoods(page, pagesize);
                        } else {
                            ToastUtils.showShort(UserCollectActivity.this, response.body().msg);
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

    private void showtext() {
        collect_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                collect_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetFollowGoods(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                collect_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetFollowGoods(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_my_collect).init();
    }

    /**
     * 获取关注店铺
     */
    private void GetFollowGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            collectAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<GetFollowGoodsBean>>>post(AppApi.BASE_URL + AppApi.GETFOLLOWGOODS)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("uid", uid)
                    .execute(new JsonCallback<BaseMode<List<GetFollowGoodsBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<GetFollowGoodsBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(UserCollectActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<GetFollowGoodsBean>>> response) {
                            Log.e("text", "获取关注店铺: " + response.body().code);
                            Log.e("text", "获取关注店铺: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                collectItemList = response.body().result;
                                if (collectItemList.size() == 0) {
                                    collectAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    collectAdapter.setNewData(collectItemList);
                                } else if (page > 1 && collectItemList != null && collectItemList.size() > 0) {
                                    collectAdapter.addData(collectItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    collect_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(UserCollectActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<GetFollowGoodsBean>>> response) {
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
