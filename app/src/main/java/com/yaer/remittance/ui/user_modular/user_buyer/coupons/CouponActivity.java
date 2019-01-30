package com.yaer.remittance.ui.user_modular.user_buyer.coupons;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.CouponActivityAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 买家优惠券
 */
public class CouponActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_coupon)
    CustomTitlebar ct_coupon;
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    private CouponActivityAdapter couponActivityAdapter;
    private List<AllCouponBuyerBean> allCouponBuyerBeanArrayList = new ArrayList<>(); // 这个是右边
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
        mImmersionBar.titleBar(R.id.ct_coupon).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initView() {
        ct_coupon.setAction(this);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_coupon.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_coupon.getParent(), false);
        rv_coupon.setLayoutManager(new GridLayoutManager(this, 1));
        GetgoodsList();
        couponActivityAdapter = new CouponActivityAdapter();
        rv_coupon.setAdapter(couponActivityAdapter);

    }
    /**
     * 获取商品列表
     *
     */
    private void GetgoodsList() {
        if (!NetworkUtils.isNetworkConnected(this)) {
            couponActivityAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AllCouponBuyerBean>>>post(AppApi.BASE_URL + AppApi.GETALLCOUPONBUYER)
                    .tag(this)
                    .params("uid", AppUtile.getUid(this))
                    .execute(new JsonCallback<BaseMode<List<AllCouponBuyerBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<AllCouponBuyerBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(CouponActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<AllCouponBuyerBean>>> response) {
                            Log.e("text", "商品列表: " + response.body().code);
                            Log.e("text", "商品列表: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                allCouponBuyerBeanArrayList = response.body().result;
                                if (allCouponBuyerBeanArrayList.size() == 0) {
                                    couponActivityAdapter.setEmptyView(notDataView);
                                }
                                couponActivityAdapter.setNewData(allCouponBuyerBeanArrayList);
                            } else {
                                ToastUtils.showShort(CouponActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            stopDialog();
                        }

                        @Override
                        public void onError(Response<BaseMode<List<AllCouponBuyerBean>>> response) {
                            super.onError(response);
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

    /**
     * 获取关注店铺
     *//*
    private void GetFollowGoods() {
        if (!NetworkUtils.isNetworkConnected(this)) {
            collectAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<GetFollowGoodsBean>>>post(AppApi.BASE_URL + AppApi.GETFOLLOWGOODS)
                    .tag(this)
                    .params("sid", page)
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
    }*/
    @Override
    public void initData() {

    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
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
