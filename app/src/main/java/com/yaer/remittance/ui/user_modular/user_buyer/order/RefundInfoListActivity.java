package com.yaer.remittance.ui.user_modular.user_buyer.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.RefundInfoListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.RefundInfoListAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/3.
 * 售后
 */
public class RefundInfoListActivity extends BaseActivity {
    @BindView(R.id.et_go)
    TextView et_go;//标题
    @BindView(R.id.back)
    ImageView back;//返回按钮
    private String sid;
    private View notDataView;
    private View errorView;
    @BindView(R.id.rv_refundlinfo_list)
    RecyclerView rv_refundlinfo_list;
    private RefundInfoListAdapter refundInfoListAdapter;
    private List<RefundInfoListBean> refundInfoListBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.rl_allorder_title).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.refundlinfo_list_layout;
    }

    @Override
    public void initView() {
        et_go.setText("售后列表");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sid = getIntent().getStringExtra("type");
        }
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_refundlinfo_list.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_refundlinfo_list.getParent(), false);
        rv_refundlinfo_list.setLayoutManager(new GridLayoutManager(this, 1));
        RefundInfolist(sid);
        refundInfoListAdapter = new RefundInfoListAdapter();
        rv_refundlinfo_list.setAdapter(refundInfoListAdapter);
        rv_refundlinfo_list.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.rl_item_refundinfo:
                       /* goToActivity(RefundActivity.class);
                         goToActivity(RefundReturnActivity.class);
                        goToActivity(ReturnGoodsActivity.class);
                        goToActivity(RefundReturnDetailsActivity.class);*/
                        Intent intent = new Intent(RefundInfoListActivity.this, RefundReturnDetailsActivity.class);
                        intent.putExtra("rid", refundInfoListBeans.get(position).getRid());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 根据用户id获取退款列表*
     */
    public void RefundInfolist(String sid) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            refundInfoListAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<RefundInfoListBean>>>post(AppApi.BASE_URL + AppApi.GETREFUNDINFOLIST)
                    .tag(this)
                    .params("uid", AppUtile.getUid(this))
                   // .params("sid",sid)
                    .execute(new JsonCallback<BaseMode<List<RefundInfoListBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<RefundInfoListBean>>, ? extends Request> request) {
                            super.onStart(request);
                            LoadingDialog.showDialogForLoading(RefundInfoListActivity.this, null);
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<RefundInfoListBean>>> response) {
                            refundInfoListBeans = response.body().result;
                            if (refundInfoListBeans.size() == 0) {
                                refundInfoListAdapter.setEmptyView(notDataView);
                            }
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                refundInfoListAdapter.setNewData(refundInfoListBeans);
                            } else {
                                ToastUtils.showShort(RefundInfoListActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<List<RefundInfoListBean>>> response) {
                            super.onError(response);
                            //ToastUtils.showShort(AllOrderActivity.this, response.body().msg);
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            LoadingDialog.hide();
                        }
                    });
        }
    }

    @OnClick({R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
