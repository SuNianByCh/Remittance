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
import com.yaer.remittance.ui.news_modular.adapter.UserSystemMassageAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 系统消息页面
 * Created by ywl on 2016/6/27.
 */
public class SystemMessageActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_system)
    CustomTitlebar ct_my_system;
    @BindView(R.id.rv_system)
    RecyclerView rv_system;
    /*刷新控件*/
    @BindView(R.id.collect_refreshLayout)
    SmartRefreshLayout collect_refreshLayout;
    private UserSystemMassageAdapter systemMassageAdapter;
    private List<AllMessageBean> SystemItemList = new ArrayList<>();
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
        mImmersionBar.titleBar(R.id.ct_my_system).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_system_message;
    }

    @Override
    public void initView() {
        ct_my_system.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_system.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_system.getParent(), false);
        rv_system.setLayoutManager(new GridLayoutManager(this, 1));
        GetFollowGoods(page, pagesize);
        systemMassageAdapter = new UserSystemMassageAdapter();
        rv_system.setAdapter(systemMassageAdapter);
        showtext();
    }
    /**
     * 获取后台通知的消息
     */
    private void GetFollowGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            systemMassageAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AllMessageBean>>>post(AppApi.BASE_URL + AppApi.GETALLMESSAGE)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("type", "2")
                    .execute(new JsonCallback<BaseMode<List<AllMessageBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<AllMessageBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(SystemMessageActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<AllMessageBean>>> response) {
                            Log.e("text", "获取关注店铺: " + response.body().code);
                            Log.e("text", "获取关注店铺: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                SystemItemList = response.body().result;
                                if (SystemItemList.size() == 0) {
                                    systemMassageAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    systemMassageAdapter.setNewData(SystemItemList);
                                } else if (page > 1 && SystemItemList != null && SystemItemList.size() > 0) {
                                    systemMassageAdapter.addData(SystemItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    collect_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(SystemMessageActivity.this, response.body().msg);
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
