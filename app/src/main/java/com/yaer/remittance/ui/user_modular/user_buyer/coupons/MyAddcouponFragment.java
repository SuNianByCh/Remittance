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
import com.yaer.remittance.bean.AllCouponSellerShopBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.MyAddcouponAdapter;
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
public class MyAddcouponFragment extends BaseFragment {
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    private MyAddcouponAdapter myAddcouponAdapter;
    private List<AllCouponSellerShopBean> allCouponBuyerBeanArrayList = new ArrayList<>(); // 这个是右边
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
        myAddcouponAdapter = new MyAddcouponAdapter();
        rv_coupon.setAdapter(myAddcouponAdapter);
    }

    /**
     * 获取优惠券列表
     */
    private void GetgoodsList() {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            myAddcouponAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AllCouponSellerShopBean>>>post(AppApi.BASE_URL + AppApi.GETALLCOUPONSELLERSHOP)
                    .tag(this)
                    .params("sid", AppUtile.getUid(mActivity))
                    .execute(new JsonCallback<BaseMode<List<AllCouponSellerShopBean>>>(mActivity) {
                        @Override
                        public void onStart(Request<BaseMode<List<AllCouponSellerShopBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(mActivity, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<AllCouponSellerShopBean>>> response) {
                            Log.e("text", "商品列表: " + response.body().code);
                            Log.e("text", "商品列表: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                allCouponBuyerBeanArrayList = response.body().result;
                                if (allCouponBuyerBeanArrayList.size() == 0) {
                                    myAddcouponAdapter.setEmptyView(notDataView);
                                }
                                myAddcouponAdapter.setNewData(allCouponBuyerBeanArrayList);
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
                        public void onError(Response<BaseMode<List<AllCouponSellerShopBean>>> response) {
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