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
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ExtensionAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 我的服务
 */

public class UserMyAuctionActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_extension_center)
    CustomTitlebar ct_extension_center;
    @BindView(R.id.rv_my_auction)
    RecyclerView rv_my_auction;
    ExtendShopAdapter extendShopAdapter;
    private List<ExtendShopBean> extendListBeans = new ArrayList<>();

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
        rv_my_auction.setLayoutManager(new GridLayoutManager(this, 1));
        getExtendList();
        extendShopAdapter = new ExtendShopAdapter();
        rv_my_auction.setAdapter(extendShopAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_my_auction;
    }

    @Override
    public void initData() {
    }

    /**
     * 获取推广服务
     */
    private void getExtendList() {
        OkGo.<BaseMode<List<ExtendShopBean>>>post(AppApi.BASE_URL + AppApi.GETEXTENDSHOP)
                .tag(this)
                .params("sid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<List<ExtendShopBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<ExtendShopBean>>> response) {
                        Log.e("text", "获取推广服务: " + response.body().code);
                        Log.e("text", "获取推广服务: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            extendListBeans = response.body().result;
                            extendShopAdapter.setNewData(extendListBeans);
                        } else {
                            ToastUtils.showShort(UserMyAuctionActivity.this, response.body().msg);
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
            case R.id.tv_right:
                goToActivity(UserExtensionCenterActivity.class);
                break;
        }
    }
}
