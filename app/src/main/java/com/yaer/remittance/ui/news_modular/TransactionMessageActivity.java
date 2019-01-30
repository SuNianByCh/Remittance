package com.yaer.remittance.ui.news_modular;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yaer.remittance.bean.AllMessageBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.news_modular.adapter.UserTransactionMassageAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 交易消息页面
 * Created by ywl on 2016/6/27.
 */
public class TransactionMessageActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_transaction)
    CustomTitlebar ct_my_transaction;
    @BindView(R.id.rl_user_transaction_message)
    RecyclerView user_transaction_message;
    /*刷新控件*/
    @BindView(R.id.collect_refreshLayout)
    SmartRefreshLayout collect_refreshLayout;
    private UserTransactionMassageAdapter transactionMassageAdapter;
    private List<AllMessageBean> TransactionItemList = new ArrayList<>();
    private View notDataView;
    private View errorView;
    private int page = 1;
    private int pagesize = 10;
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
        mImmersionBar.titleBar(R.id.ct_my_transaction).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_transaction_message;
    }

    @Override
    public void initView() {
        ct_my_transaction.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) user_transaction_message.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) user_transaction_message.getParent(), false);
        user_transaction_message.setLayoutManager(new GridLayoutManager(this, 1));
        GetFollowGoods(page, pagesize);
        transactionMassageAdapter = new UserTransactionMassageAdapter();
        user_transaction_message.setAdapter(transactionMassageAdapter);
        showtext();
    }

    /**
     * 获取后台通知的消息
     */
    private void GetFollowGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            transactionMassageAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AllMessageBean>>>post(AppApi.BASE_URL + AppApi.GETALLMESSAGE)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("type", "1")
                    .params("uid", AppUtile.getUid(this))
                    .execute(new JsonCallback<BaseMode<List<AllMessageBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<AllMessageBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(TransactionMessageActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<AllMessageBean>>> response) {
                            Log.e("text", "获取关注店铺: " + response.body().code);
                            Log.e("text", "获取关注店铺: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                TransactionItemList = response.body().result;
                                if (TransactionItemList.size() == 0) {
                                    transactionMassageAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    transactionMassageAdapter.setNewData(TransactionItemList);
                                } else if (page > 1 && TransactionItemList != null && TransactionItemList.size() > 0) {
                                    transactionMassageAdapter.addData(TransactionItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    collect_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(TransactionMessageActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<AllMessageBean>>> response) {
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
