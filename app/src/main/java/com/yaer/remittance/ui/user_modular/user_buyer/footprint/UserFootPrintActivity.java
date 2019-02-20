package com.yaer.remittance.ui.user_modular.user_buyer.footprint;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

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
import com.yaer.remittance.bean.FootprIntBean;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ShoppingAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.UserFootPrintAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.SlideRecyclerView;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的足迹
 * Created by ywl on 2016/6/27.
 */
public class UserFootPrintActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    //加载视图
    @BindView(R.id.srv_userfoot_print)
    SlideRecyclerView srv_userfoot_print;
    //标题栏
    @BindView(R.id.ct_history)
    CustomTitlebar ct_history;
    //数据集合
    @BindView(R.id.history_refreshLayout)
    SmartRefreshLayout historyLayout;
    private List<FootprIntBean> FootPrintItemList = new ArrayList<>();
    UserFootPrintAdapter userFootPrintAdapter;
    private int page = 1;
    private int pagesize = 10;
    private int uid;
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
        mImmersionBar.titleBar(R.id.ct_history).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_foot_print;
    }

    @Override
    public void initView() {
        /*历史足迹*/
        uid = AppUtile.getUid(this);
        ct_history.setAction(this);
        srv_userfoot_print.setLayoutManager(new GridLayoutManager(this, 1));
        userFootPrintAdapter = new UserFootPrintAdapter();
        srv_userfoot_print.setAdapter(userFootPrintAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) srv_userfoot_print.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) srv_userfoot_print.getParent(), false);
        GetBrowsingHistory(page, pagesize);
        userFootPrintAdapter.setOnDeleteClickListener(new UserFootPrintAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                FootprIntBean footprIntBeanlist = userFootPrintAdapter.getData().get(position);
                showDelDialog(footprIntBeanlist.getBhid(), position);
            }
        });
        showtext();
    }

    public void initData() {

    }

    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        historyLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                historyLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetBrowsingHistory(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                historyLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetBrowsingHistory(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    /**
     * 获取足迹列表
     */
    private void GetBrowsingHistory(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            userFootPrintAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<FootprIntBean>>>post(AppApi.BASE_URL + AppApi.GETBROWSINGHISTORY)
                    .tag(this)
                    .params("uid", uid)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .execute(new JsonCallback<BaseMode<List<FootprIntBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<FootprIntBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(UserFootPrintActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<FootprIntBean>>> response) {
                            Log.e("text", "足迹列表:: " + response.body().code);
                            Log.e("text", "足迹列表:: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                FootPrintItemList = response.body().result;
                                if (FootPrintItemList.size() == 0) {
                                    userFootPrintAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    userFootPrintAdapter.setNewData(FootPrintItemList);
                                } else if (page > 1 && FootPrintItemList != null && FootPrintItemList.size() > 0) {
                                    userFootPrintAdapter.addData(FootPrintItemList);
                                } else {
                                    historyLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(UserFootPrintActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<FootprIntBean>>> response) {
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

    private void showDelDialog(final int id, final int postion) {
        final UIAlertView delDialog = new UIAlertView(UserFootPrintActivity.this, "温馨提示", "是否删除足迹",
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
                                           deleteBrowsingHistory(id, postion);
                                       }
                                   }
        );
    }

    /**
     * 删除足迹列表
     */
    private void deleteBrowsingHistory(int bhid, final int positon) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEBROWSINGHISTORY)
                .tag(this)
                .params("bhid", bhid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserFootPrintActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "足迹列表: " + response.body().code);
                        Log.e("text", "足迹列表: " + response.body().result);
                        ToastUtils.showShort(UserFootPrintActivity.this, response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            //这里你要把数据也删除啊，Adapter里的数据
                            userFootPrintAdapter.getData().remove(positon);
                            userFootPrintAdapter.notifyItemRemoved(positon);
//                            userFootPrintAdapter.notifyDataSetChanged();
                            srv_userfoot_print.closeMenu();
                            ToastUtils.showShort(UserFootPrintActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(UserFootPrintActivity.this, response.body().msg);
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

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
