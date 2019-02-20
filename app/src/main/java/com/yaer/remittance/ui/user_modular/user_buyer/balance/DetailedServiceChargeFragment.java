package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.WalletListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.TotalAmountAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 手续费
 */
public class DetailedServiceChargeFragment extends BaseLazyFragment {
    @BindView(R.id.rv_total_amount)
    RecyclerView rv_total_amount;
    private List<WalletListBean> totalAmountItemList = new ArrayList<>();
    private TotalAmountAdapter totalAmountAdapter;
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
    protected int setLayoutId() {
        return R.layout.fragment_total_amount;
    }

    @Override
    public void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_total_amount.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_total_amount.getParent(), false);
        rv_total_amount.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        totalAmountAdapter = new TotalAmountAdapter();
        totalAmountAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv_total_amount.setAdapter(totalAmountAdapter);
        totalAmountAdapter.setPreLoadNumber(1);
        totalAmountAdapter.setNewData(totalAmountItemList);
        getWalletlist();
    }

    /**
     * 获取余额明细*
     */
    public void getWalletlist() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            totalAmountAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<WalletListBean>>>post(AppApi.BASE_URL + AppApi.GETWALLETLIST)
                    .tag(this)
                    .params("uid", AppUtile.getUid(getActivity()))
                    .params("wtype", "7")
                    .execute(new JsonCallback<BaseMode<List<WalletListBean>>>(getActivity()) {
                        @Override
                        public void onStart(Request<BaseMode<List<WalletListBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(getActivity(), "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<WalletListBean>>> response) {
                            Log.e("text", "修改昵称: " + response.body().code);
                            Log.e("text", "修改昵称: " + response.body().result);
                            totalAmountItemList = response.body().result;
                            if (totalAmountItemList.size() == 0) {
                                totalAmountAdapter.setEmptyView(notDataView);
                            }
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                totalAmountAdapter.setNewData(totalAmountItemList);
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
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

    @Override
    protected void initData() {
    }
}
