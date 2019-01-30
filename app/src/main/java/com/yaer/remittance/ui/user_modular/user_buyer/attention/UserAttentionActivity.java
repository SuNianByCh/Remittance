package com.yaer.remittance.ui.user_modular.user_buyer.attention;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.GetFollowGoodsBean;
import com.yaer.remittance.bean.GetFollowShopBean;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.adapter.NewProductAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.AttentionAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.collect.UserCollectActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.logistics.LogisticsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderCommentActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderDetailsActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import pl.droidsonroids.gif.GifImageView;

/**
 * 我的关注页面
 * Created by ywl on 2016/6/27.
 */
public class UserAttentionActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_attention)
    CustomTitlebar ct_my_attention;
    /*刷新控件*/
    @BindView(R.id.attention_refreshLayout)
    SmartRefreshLayout attention_refreshLayout;
    @BindView(R.id.gifview)
    GifImageView gifview;
    private AttentionAdapter attentionAdapter;
    private List<GetFollowShopBean> AttentionItemList = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    @BindView(R.id.rv_attention)
    RecyclerView rv_attention;
    private int uid;
    private boolean isfollw;
    private String follow;
    private TextView tv_attention_status;//关注按钮
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_my_attention).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_attention;
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        ct_my_attention.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_attention.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_attention.getParent(), false);
        rv_attention.setLayoutManager(new GridLayoutManager(this, 1));
        GetFollowshop(page, pagesize);
        attentionAdapter = new AttentionAdapter();
        rv_attention.setAdapter(attentionAdapter);
        rv_attention.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                tv_attention_status = (TextView) view.findViewById(R.id.tv_attention_status);
                switch (itemViewId) {
                    case R.id.rl_attention:
                        Intent intent = new Intent(UserAttentionActivity.this, ShopActivity.class);
                        intent.putExtra("shopinfosid", AttentionItemList.get(position).getSid());
                        UserAttentionActivity.this.startActivity(intent);
                        break;
                    case R.id.tv_attention_status:
                        String sid = AttentionItemList.get(position).getSid();
                        follow = "0";
                        showDelDialog(follow, position, sid);
                        break;
                }
            }
        });
        showtext();
    }

    private void showDelDialog(final String follow, final int position, final String sid) {
        final UIAlertView delDialog = new UIAlertView(
                UserAttentionActivity.this, "温馨提示", "取消关注，将会查看不到关注店铺的信息",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           UpdateShopFollow(follow, position, sid);
                                           delDialog.dismiss();
                                       }
                                   }
        );
    }

    /**
     * 店铺关注 已关注/未关注
     */
    private void UpdateShopFollow(String follow, final int postion, String sid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPFOLLOW)
                .tag(this)
                .params("uid", uid)
                .params("sid", sid)
                .params("follow", follow)
                .params("token", AppUtile.getTicket(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserAttentionActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            AttentionItemList.remove(postion);
                            attentionAdapter.notifyItemRemoved(postion);
                        } else {
                            ToastUtils.showShort(UserAttentionActivity.this, response.body().msg);
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

    @Override
    public void initData() {

    }

    private void showtext() {
        attention_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                attention_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetFollowshop(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                attention_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetFollowshop(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    /**
     * 获取关注店铺
     */
    private void GetFollowshop(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            attentionAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<GetFollowShopBean>>>post(AppApi.BASE_URL + AppApi.GETFOLLOWSHOP)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("uid", uid)
                    .execute(new JsonCallback<BaseMode<List<GetFollowShopBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<GetFollowShopBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(UserAttentionActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<GetFollowShopBean>>> response) {
                            Log.e("text", "获取关注店铺: " + response.body().code);
                            Log.e("text", "获取关注店铺: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                AttentionItemList = response.body().result;
                                if (AttentionItemList.size() == 0) {
                                    attentionAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    attentionAdapter.setNewData(AttentionItemList);
                                } else if (page > 1 && AttentionItemList != null && AttentionItemList.size() > 0) {
                                    attentionAdapter.addData(AttentionItemList);
                                } else {
                                    attention_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(UserAttentionActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<GetFollowShopBean>>> response) {
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
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

}
