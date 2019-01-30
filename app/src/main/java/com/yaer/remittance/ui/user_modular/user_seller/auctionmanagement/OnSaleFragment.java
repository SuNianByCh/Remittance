package com.yaer.remittance.ui.user_modular.user_seller.auctionmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.imagepicker.bean.ImageItem;
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
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.bean.getMyAuctionBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.PatCollectAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.OnSaleAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在售拍品商品
 */
public class OnSaleFragment extends BaseLazyFragment {
    @BindView(R.id.rv_one_sale)
    RecyclerView rv_one_sale;
    @BindView(R.id.onsale_refreshLayout)
    SmartRefreshLayout onsale_refreshLayout;
    private List<getMyAuctionBean> onSaleItemList = new ArrayList<>();
    private OnSaleAdapter onSaleAdapter;
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;
    private int gid;
    private int gisauction;
    //是否第一次进入
    private static boolean isFirstEnter = true;

    @Override
    public void onResume() {
        super.onResume();
        //第一次进入演示刷新
       /* if (isFirstEnter) {
            isFirstEnter = false;
            onsale_refreshLayout.autoRefresh();
        }*/
        onsale_refreshLayout.autoRefresh();
    }

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_one_sale;
    }

    @Override
    public void initView() {
        getAcution(page, pagesize);
        rv_one_sale.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        onSaleAdapter = new OnSaleAdapter();
        rv_one_sale.setAdapter(onSaleAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_one_sale.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_one_sale.getParent(), false);
        rv_one_sale.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                getMyAuctionBean myAuctionBean = onSaleAdapter.getData().get(position);
                gid = myAuctionBean.getGid();
                gisauction = myAuctionBean.getGisauction();
                Bundle bundle = new Bundle();
                switch (itemViewId) {
                    /*编辑*/
                    case R.id.tv_sale_edit:
                        if (gisauction == 0) {
                            bundle.putString("gid", String.valueOf(myAuctionBean.getGid()));//商品id
                            bundle.putString("gpostage", myAuctionBean.getGpostage());//快递费
                            bundle.putInt("gissoldout", myAuctionBean.getGissoldout());//是否上架下架
                            bundle.putString("gauctiontime", myAuctionBean.getGauctiontime());//拍卖时间
                            bundle.putString("gstoptime", myAuctionBean.getGstoptime());//结束拍卖时间
                            bundle.putString("gaddprice", myAuctionBean.getGaddprice());//加价幅度
                            bundle.putString("gstartingprice", myAuctionBean.getGstartingprice());//起拍价格
                            bundle.putString("gvideo", myAuctionBean.getGvideo());//视频
                            ArrayList<ImageItem> list = myAuctionBean.getImageItemList();
                            bundle.putParcelableArrayList("imageitem", list);//图片
                            bundle.putInt("gcid", myAuctionBean.getGcid());//
                            bundle.putString("gdesc", myAuctionBean.getGdesc());//拍品解决
                            bundle.putInt("gnumber", myAuctionBean.getGnumber());//库存
                            bundle.putString("gmoney", myAuctionBean.getGmoney());//金额
                            bundle.putInt("gisauction", myAuctionBean.getGisauction());//是否是商品还是拍品
                            bundle.putString("gname", myAuctionBean.getGname());//商品名称
                            goToActivity(EditedCommodityActivity.class, bundle);
                        }else{
                            bundle.putString("gid", String.valueOf(myAuctionBean.getGid()));//商品id
                            bundle.putString("gpostage", myAuctionBean.getGpostage());//快递费
                            bundle.putInt("gissoldout", myAuctionBean.getGissoldout());//是否上架下架
                            bundle.putString("gauctiontime", myAuctionBean.getGauctiontime());//拍卖时间
                            bundle.putString("gstoptime", myAuctionBean.getGstoptime());//结束拍卖时间
                            bundle.putString("gaddprice", myAuctionBean.getGaddprice());//加价幅度
                            bundle.putString("gstartingprice", myAuctionBean.getGstartingprice());//起拍价格
                            bundle.putString("gvideo", myAuctionBean.getGvideo());//视频
                            ArrayList<ImageItem> list = myAuctionBean.getImageItemList();
                            bundle.putParcelableArrayList("imageitem", list);//图片
                            bundle.putInt("gcid", myAuctionBean.getGcid());//
                            bundle.putString("gdesc", myAuctionBean.getGdesc());//拍品解决
                            bundle.putInt("gnumber", myAuctionBean.getGnumber());//库存
                            bundle.putString("gmoney", myAuctionBean.getGmoney());//金额
                            bundle.putInt("gisauction", myAuctionBean.getGisauction());//是否是商品还是拍品
                            bundle.putString("gname", myAuctionBean.getGname());//商品名称
                            goToActivity(EditedAuctionActivity.class, bundle);
                        }
                        break;
                    /*查看订单详情*/
                    case R.id.tv_sale_lower_frame:
                        showDelDialog(gid, position);
                        break;
                    case R.id.ll_one_sale:
                        if (gisauction == 0) {
                            Intent intent = new Intent(mActivity, CommodityDetailsActivity.class);
                            intent.putExtra("gidshopping", String.valueOf(gid));
                            mActivity.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mActivity, AuctionDetailsActivity.class);
                            intent.putExtra("gidshopping", String.valueOf(gid));
                            mActivity.startActivity(intent);
                        }
                        break;
                }
            }
        });
        showtext();
    }

    private void showDelDialog(final int gid, final int postion) {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "确认要下架商品，用户您将无法查看商品",
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
                                           UpdateIssoldoyt(gid, postion);
                                       }
                                   }
        );
    }

    /**
     * 修改上架下架商品
     */
    private void UpdateIssoldoyt(int gid, final int position) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEISSOLDOYT)
                .tag(this)
                .params("gid", gid)
                .params("gissoldout", "1")
                .params("utoken", AppUtile.getTicket(mActivity))
                .execute(new JsonCallback<BaseMode>(getActivity()) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(getActivity(), null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            onSaleItemList.remove(position);
                            onSaleAdapter.notifyItemRemoved(position);
                            //getorderList(ostatus);
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        onsale_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                onsale_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getAcution(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                onsale_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getAcution(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    /**
     * 获取商品或拍品未下架
     *
     * @param page
     * @param pagesize
     */
    private void getAcution(final int page, int pagesize) {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            onSaleAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<getMyAuctionBean>>>post(AppApi.BASE_URL + AppApi.GETMYAUCTION)
                    .tag(this)
                    .params("sid", AppUtile.getUid(getActivity()))
                    .params("page", page)
                    .params("pagesize", pagesize)
                    .params("gissoldout", "0")
                    .execute(new JsonCallback<BaseMode<List<getMyAuctionBean>>>(getActivity()) {

                        @Override
                        public void onSuccess(Response<BaseMode<List<getMyAuctionBean>>> response) {
                            Log.e("text", "获取我发布的商品或拍品: " + response.body().code);
                            Log.e("text", "获取我发布的商品或拍品: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                onSaleItemList = response.body().result;
                                if (onSaleItemList.size() == 0) {
                                    onSaleAdapter.setEmptyView(notDataView);
                                }
                                if (page == 1) {
                                    onSaleAdapter.setNewData(onSaleItemList);
                                } else if (page > 1 && onSaleItemList != null && onSaleItemList.size() > 0) {
                                    onSaleAdapter.addData(onSaleItemList);
                                } else {
                                    ToastUtils.showToast("数据全部加载完毕");
                                    onsale_refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }

    @Override
    protected void initData() {

    }
}
