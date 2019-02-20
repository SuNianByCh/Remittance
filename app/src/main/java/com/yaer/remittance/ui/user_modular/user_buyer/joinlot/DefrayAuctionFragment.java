package com.yaer.remittance.ui.user_modular.user_buyer.joinlot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
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
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AtAuctionBean;
import com.yaer.remittance.bean.OrderListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.AllOrderAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.DefrayAuctionAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.logistics.LogisticsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderCommentActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderDetailsActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.imkit.RongIM;

/**
 * 藏品管理待支付
 */
public class DefrayAuctionFragment extends BaseLazyFragment {
    /*刷新控件*/
    @BindView(R.id.at_auction_refreshLayout)
    SmartRefreshLayout at_auction_refreshLayout;
    @BindView(R.id.rv_at_auction)
    RecyclerView rv_at_auction;
    // private DefrayAuctionAdapter defrayAuctionAdapter;
    private AllOrderAdapter mAdapter;
    private List<OrderListBean> mFoodData = new ArrayList<>();
    private int page = 1;
    private int pagesize = 10;
    private int uid;
    private View notDataView;
    private View errorView;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_at_auction;
    }

    @Override
    public void initView() {
        /*拍卖中*/
        uid = AppUtile.getUid(getActivity());
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_at_auction.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_at_auction.getParent(), false);
        rv_at_auction.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new AllOrderAdapter();
        rv_at_auction.setAdapter(mAdapter);
        getorderList();
        rv_at_auction.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                OrderListBean iCartItem = mAdapter.getData().get(position);
                switch (itemViewId) {
                    /*查看订单详情*/
                    case R.id.fl:
                        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                        intent.putExtra("onumber", mFoodData.get(position).getOnumber());//订单编号
                        intent.putExtra("ordertype", "0");//订单跳转状态
                        startActivity(intent);
                        break;
                    /*联系商家*/
                    case R.id.ll_order_contact_merchant:
                        if (!NetworkUtils.isNetworkConnected(getActivity())) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(getActivity()).equals("")) {
                            goToActivity(LoginActivity.class, "type", "5");
                        } else {
                            /**
                             * 这个是启动单聊界面
                             * 启动单聊界面。
                             *
                             * @param context      应用上下文。
                             * @param targetUserId 要与之聊天的用户 Id。
                             * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
                             *                     获取该值, 再手动设置为聊天界面的标题。
                             */
                           /* int sid = mFoodData.get(position).getShoplist().get(position).getSid();
                            String sname = mFoodData.get(position).getShoplist().get(position).getSname();*/
                            int sid = mFoodData.get(position).getShoplist().get(0).getSid();
                            String name = mFoodData.get(position).getShoplist().get(0).getSname();
                            RongIM.getInstance().startPrivateChat(getActivity(), String.valueOf(sid), name);
                        }
                        break;
                    /*取消订单*/
                    case R.id.ll_cancel_order:
                        cancelDelDialog(iCartItem.getOid(), position);
                        break;
                    /*删除订单*/
                    case R.id.ll_order_delete:
                        showDelDialog(iCartItem.getOid(), position);
                        break;
                    /*付款*/
                    case R.id.ll_order_payment:
                        showpayydialog(mFoodData.get(position).getOnumber(), mFoodData.get(position).getOtotalvalue());//mFoodData.get(position).getOnumber()
                        break;
                }
            }
        });
        //  showtext();
    }

    private void showpayydialog(String onumber, double ototalvalue) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("onumber", onumber);//订单编号
        bundle.putDouble("ototalvalue", ototalvalue);//订单金额
        bundle.putString("payftype", "3");//提交订单传的id
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getActivity().getSupportFragmentManager(), "payFragment");
    }

    /*删除订单*/
    private void showDelDialog(final int oid, final int postion) {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "删除订单后，您将无法查看该订单",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           DeleteOrder(oid, postion);
                                       }
                                   }
        );
    }

    /**
     * 删除订单
     */
    private void DeleteOrder(int oid, final int position) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(getActivity()) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(getActivity(), null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                            mFoodData.remove(position);
                            mAdapter.notifyItemRemoved(position);
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        Log.e("http---error", response.getException().toString());
                        ToastUtils.showShort(getActivity(), response.body().msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }

    private void cancelDelDialog(final int oid, final int postion) {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "确定要取消订单吗",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           Cancelorder(oid, postion);
                                       }
                                   }
        );
    }

    /**
     * 取消订单*
     */
    public void Cancelorder(int oid, final int position) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.CANCELORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(getActivity()) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(getActivity(), null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (Constant.SUEECECODE.equals((response.body().code))) {
                            if (mAdapter.getData().isEmpty() || mAdapter.getData().size() == 0) {
                                mAdapter.getData().clear();
                                mAdapter.notifyDataSetChanged();
                                mAdapter.setEmptyView(notDataView);

                            } else {
                                mFoodData.remove(position);
                                mAdapter.notifyItemRemoved(position);
                            }

                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        ToastUtils.showShort(getActivity(), response.body().msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }
   /* private void showtext() {
        at_auction_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                at_auction_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetAtAuctionGoods(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                at_auction_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetAtAuctionGoods(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }*/

    private LoadingDialog2 photoDiloag;

    /**
     * 根据用户id获取订单列表*
     */
    public void getorderList() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            mAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<OrderListBean>>>post(AppApi.BASE_URL + AppApi.GETORDERLIST)
                    .tag(this)
                    .params("uid", AppUtile.getUid(getActivity()))
                    .params("ostatus", 0)
                    .params("gisauction", 1)
                    .execute(new JsonCallback<BaseMode<List<OrderListBean>>>(getActivity()) {
                        @Override
                        public void onStart(Request<BaseMode<List<OrderListBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(getActivity(), "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<OrderListBean>>> response) {
                            stopDialog();
                            if (mFoodData != null) mFoodData.clear();//这里清空掉了
                            mFoodData = response.body().result;//返回的集合数据
                            if (response.body().code.equals(Constant.SUEECECODE)) {//这个地方是返回的code判断
                                Log.e("text", "mFoodDatamFoodData====: " + mFoodData);
                                Log.e("text", "mFoodDatamFoodData====: " + JSON.toJSONString(mFoodData));
                                if (mFoodData.size() == 0) {//JSON.toJSONString(mFoodData).equals("[]")
                                    Log.e("text", "mFoodDatamFoodData111====: ");
                                    mAdapter.notifyDataSetChanged();
                                    mAdapter.isUseEmpty(true);
                                    mAdapter.setEmptyView(notDataView);//没起作用？第一次生效了
                                } else {
                                    if (mFoodData != null && mFoodData.size() > 0) {//这下
                                        mAdapter.setNewData(mFoodData);
                                    } else {
                                        mAdapter.setEmptyView(notDataView);
                                    }
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }

                        }

                        @Override
                        public void onError(Response<BaseMode<List<OrderListBean>>> response) {
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
/*
    private void GetAtAuctionGoods(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            defrayAuctionAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<AtAuctionBean>>>post(AppApi.BASE_URL + AppApi.GETPARYAKEAUCTION)
                    .tag(this)
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("uid", uid)
                    .params("state", 2)
                    .execute(new JsonCallback<BaseMode<List<AtAuctionBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<AtAuctionBean>>> response) {
                            Log.e("text", "获取待支付的拍品: " + response.body().code);
                            Log.e("text", "获取待支付的拍品: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                at_auctionlist = response.body().result;
                                if (at_auctionlist.size() == 0) {
                                    defrayAuctionAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    defrayAuctionAdapter.setNewData(at_auctionlist);
                                } else if (page > 1 && at_auctionlist != null && at_auctionlist.size() > 0) {
                                    defrayAuctionAdapter.addData(at_auctionlist);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    at_auction_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
*/

    @Override
    protected void initData() {

    }
}

