package com.yaer.remittance.ui.user_modular.user_buyer.integral;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ExtensionAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.extensioncenter.UserExtensionCenterActivity;
import com.yaer.remittance.ui.user_modular.user_seller.extensioncenter.UserExtensionDetailsActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 卖家积分
 * Created by ywl on 2016/6/27.
 */
public class UserSellerIntegralActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_integral)
    CustomTitlebar ct_integral;
    @BindView(R.id.civ_integral)
    CircleImageView civ_integral;
    /*商家*/
    @BindView(R.id.tv_integral_name)
    TextView tv_integral_name;
    /* @BindView(R.id.tv_integral_lable)
     TextView tv_integral_lable;*/
    @BindView(R.id.tv_user_uintegral)
    TextView tv_user_uintegral;
    @BindView(R.id.rv_extension)
    RecyclerView rv_extension;
    ExtensionAdapter extensionAdapter;
    private List<ExtendListBean> extendListBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private String uname;

    @Override
    public void initView() {
        ct_integral.setAction(this);
        String uintegral = (String) SharedPreferencesUtils.getData(this, "uintegral", "");
        uname = (String) SharedPreferencesUtils.getData(this, "uname", "");
        shopicon = (String) SharedPreferencesUtils.getData(this, "uicon", "");
        String uphone = (String) SharedPreferencesUtils.getData(this, "uphone", "");
        tv_user_uintegral.setText(uintegral);
        if (uname.equals("")) {
            tv_integral_name.setText(uphone);//买家手机号
        } else {
            tv_integral_name.setText(uname);//买家名称
        }
        if (shopicon.equals("")) {
            Glide.with(UserSellerIntegralActivity.this).load(R.drawable.user_settings).fitCenter().into(civ_integral);//商品图片
        } else {
            Glide.with(UserSellerIntegralActivity.this).load(shopicon).fitCenter().into(civ_integral);
        }
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
                        goToActivity(UserSellerExtensionDetailsActivity.class, bundle);
                        break;
                }
            }
        });
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
                            ToastUtils.showShort(UserSellerIntegralActivity.this, response.body().msg);
                        }
                    }
                });
    }


    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_integral).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_seller_user_integral;
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                goToActivity(UserIntegralRuleActivity.class);
                break;
        }
    }

    private String shopicon;

    //获取店铺信息
    public void getShopInfoBySid() {
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<ShopInfoByidBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            tv_integral_name.setText(response.body().result.getShopname());//卖家名称
                            shopicon = response.body().result.getShopimg();
                            Glide.with(UserSellerIntegralActivity.this).load(shopicon).fitCenter().into(civ_integral);
                            Gson gson = new Gson();
                            //tv_integral_lable.setText(gson.toJson(response.body().result.getShoplabel()));
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }
                });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
