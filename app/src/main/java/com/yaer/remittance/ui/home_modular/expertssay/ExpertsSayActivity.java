package com.yaer.remittance.ui.home_modular.expertssay;

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
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SpecialistBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ExpertsSayAdapter;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 专家说
 */
public class ExpertsSayActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    //标题栏
    @BindView(R.id.ct_experts_say)
    CustomTitlebar ct_experts_say;
    //加载视图
    @BindView(R.id.rv_expertssay)
    RecyclerView rv_expertssay;
    /*刷新控件*/
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout expertsLayout;
    //数据集合
    private List<SpecialistBean> ExpertsSayItemList = new ArrayList<>();
    //加载体
    private ExpertsSayAdapter expertsSayAdapter;
    //是否第一次进入
    private static boolean isFirstEnter = true;
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

    /***
     * 初始化标题栏
     */
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_experts_say).init();
    }

    /**
     * 设置页面来源-专家说
     *
     * @return
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_experts_say;
    }

    /**
     * 初始化视图
     */
    @Override
    public void initView() {
        ct_experts_say.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_expertssay.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_expertssay.getParent(), false);
        //第一次进入演示刷新
     /*   if (isFirstEnter) {
            isFirstEnter = false;
            expertsLayout.autoRefresh();
        }*/
        //collect_refreshLayout.setEnableRefresh(false);//下拉
        expertsLayout.setEnableLoadMore(false);//上拉
        rv_expertssay.setLayoutManager(new LinearLayoutManager(this));
        expertsSayAdapter = new ExpertsSayAdapter();
        rv_expertssay.setAdapter(expertsSayAdapter);
        GetSpecialist(page, pagesize);
        showtext();
        rv_expertssay.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                SpecialistBean specialistBean = expertsSayAdapter.getData().get(position);
                switch (itemViewId) {
                    case R.id.ll_expertssay:
                        Bundle bundle = new Bundle();
                        bundle.putInt("esid", specialistBean.getEsid());
                        goToActivity(ExpertsSayDetailsActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    private void showtext() {
        expertsLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                expertsLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetSpecialist(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                expertsLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetSpecialist(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }


    /**
     * 获取专家列表
     */
    private void GetSpecialist(final int page, int pagesize) {
        OkGo.<BaseMode<List<SpecialistBean>>>post(AppApi.BASE_URL + AppApi.GETALLEXPERTSSAY)
                .tag(this)
                .params("page", page)
                .params("pagesize", pagesize)
                .execute(new JsonCallback<BaseMode<List<SpecialistBean>>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<List<SpecialistBean>>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(ExpertsSayActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<List<SpecialistBean>>> response) {
                        Log.e("text", "专家列表: " + response.body().code);
                        Log.e("text", "专家列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ExpertsSayItemList = response.body().result;
                            if (ExpertsSayItemList.size() == 0) {
                                expertsSayAdapter.setEmptyView(notDataView);
                            }
                            if (page == 1) {
                                expertsSayAdapter.setNewData(ExpertsSayItemList);
                            } else if (page > 1 && ExpertsSayItemList != null && ExpertsSayItemList.size() > 0) {
                                expertsSayAdapter.addData(ExpertsSayItemList);
                            } else {
                                expertsLayout.finishLoadMoreWithNoMoreData();
                            }
                        } else {
                            stopDialog();
                            ToastUtils.showShort(ExpertsSayActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<List<SpecialistBean>>> response) {
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

    /***
     * 标题栏点击事件
     * @param view
     */
    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
