package com.yaer.remittance.ui.user_modular.user_seller.extensioncenter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.ConfirmOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.collect.UserCollectAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ExtensionAdapter;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 服务列表
 */

public class UserExtensionCenterActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_extension_center)
    CustomTitlebar ct_extension_center;
    @BindView(R.id.rv_extension)
    RecyclerView rv_extension;
    ExtensionAdapter extensionAdapter;
    private List<ExtendListBean> extendListBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_extension_center).init();
    }

    @Override
    public void initView() {
        ct_extension_center.setAction(this);
        rv_extension.setLayoutManager(new GridLayoutManager(this, 1));
        getExtendList();
        extensionAdapter = new ExtensionAdapter();
        rv_extension.setAdapter(extensionAdapter);
        rv_extension.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                Bundle bundle = new Bundle();
                ExtendListBean extendListBeandata = extensionAdapter.getData().get(position);
                List<ExtendListBean.ExtendMoneyInfoModelListBean> extendMoneyInfoModelListBeanList = extensionAdapter.getData().get(position).getExtendMoneyInfoModelList();
                switch (itemViewId) {
                    /*查看服务详情*/
                    case R.id.ll_extension_item:
                        bundle.putString("ename", extendListBeandata.getEname());
                        bundle.putString("edesc", extendListBeandata.getEdesc());
                        bundle.putSerializable("extendmoneyinfomode", (Serializable) extendMoneyInfoModelListBeanList);
                        goToActivity(UserExtensionDetailsActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_extension_center;
    }

    @Override
    public void initData() {
    }

    /**
     * 获取推广服务
     */
    private void getExtendList() {
        OkGo.<BaseMode<List<ExtendListBean>>>post(AppApi.BASE_URL + AppApi.GETEXTENDLIST)
                .tag(this)
                .execute(new JsonCallback<BaseMode<List<ExtendListBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<ExtendListBean>>> response) {
                        Log.e("text", "获取推广服务: " + response.body().code);
                        Log.e("text", "获取推广服务: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            extendListBeans = response.body().result;
                            extensionAdapter.setNewData(extendListBeans);
                        } else {
                            ToastUtils.showShort(UserExtensionCenterActivity.this, response.body().msg);
                        }
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
