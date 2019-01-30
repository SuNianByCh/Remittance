package com.yaer.remittance.ui.user_modular.user_buyer.coupons;

import android.os.Bundle;
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
import com.yaer.remittance.base.BaseFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.CouponActivityAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的优惠券
 */
public class MycouponFragment extends BaseFragment {
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

    @Override
    protected int setLayoutResouceId() {
        return R.layout.layout_coupon;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_coupon.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_coupon.getParent(), false);
        rv_coupon.setLayoutManager(new GridLayoutManager(mActivity, 1));
        GetgoodsList();
        couponActivityAdapter = new CouponActivityAdapter();
        rv_coupon.setAdapter(couponActivityAdapter);
    }

    /**
     * 获取优惠券列表
     */
    private void GetgoodsList() {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            couponActivityAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AllCouponBuyerBean>>>post(AppApi.BASE_URL + AppApi.GETALLCOUPONBUYERUSER)
                    .tag(this)
                    .params("uid", AppUtile.getUid(mActivity))
                    .execute(new JsonCallback<BaseMode<List<AllCouponBuyerBean>>>(mActivity) {
                        @Override
                        public void onStart(Request<BaseMode<List<AllCouponBuyerBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(mActivity, "加载中...");
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
                                ToastUtils.showShort(mActivity, response.body().msg);
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

}
