package com.yaer.remittance.ui.user_modular.user_seller.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
import com.yaer.remittance.bean.OrderListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.DeliverGoodsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.AllOrderAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.logistics.LogisticsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderCommentActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderDetailsActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.AllSellerOrderAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/7/3.
 * 订单列表展示
 */
public class SellerAllOrderActivity extends BaseActivity {
    //title
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shop)
    ImageView shop;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.et_go)
    TextView et_go;//标题
    /*全部*/
    @BindView(R.id.rb_all)
    RadioButton rb_all;
    /*代付款*/
    @BindView(R.id.rb_daifukuan)
    RadioButton rb_daifukuan;
    /*代发货*/
    @BindView(R.id.rb_daifahuo)
    RadioButton rb_daifahuo;
    /*待收货*/
    @BindView(R.id.rb_daishouhuo)
    RadioButton rb_daishouhuo;
    /*待评价*/
    @BindView(R.id.rb_yishouhuo)
    RadioButton rb_yishouhuo;
    /*   *//*退款售后*//*
    @BindView(R.id.rb_refund_after_sale)
    RadioButton rb_refund_after_sale;*/
    @BindView(R.id.rl_no_nonews)
    RelativeLayout rl_no_nonews;//暂无数据
    @BindView(R.id.rv_all_order)
    RecyclerView rv_all_order;
    @BindView(R.id.rl_allorder_title)
    RelativeLayout rl_allorder_title;
    private AllSellerOrderAdapter mAdapter;
    private String type = "0";
    List<OrderListBean> mFoodData;
    private int uid;
    private String ostatus = null;//订单状态，不传是全部
    private View LoadingView;
    private View notDataView;
    private View errorView;
    List<OrderListBean.ShoplistBean> shoplistBeanList = new ArrayList<>();
    List<OrderListBean.ShoplistBean.GoodslistBean> goodslistBeans = new ArrayList<>();
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
        mImmersionBar.titleBar(R.id.rl_allorder_title).init();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.all_seller_order_layout;
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        initAdapter();
        et_go.setText("全部订单");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = getIntent().getStringExtra("type");
        } else {
            type = "0";
        }
        if (type.equals("0")) {
            //全部
            rb_all.setChecked(true);
            ostatus = "";
        } else if (type.equals("1")) {
            //待付款
            rb_daifukuan.setChecked(true);
            //getorderList("0");
            ostatus = "0";
        } else if (type.equals("2")) {
            //代发货
            rb_daifahuo.setChecked(true);
            ostatus = "1";
        } else if (type.equals("3")) {
            //待收货
            rb_daishouhuo.setChecked(true);
            ostatus = "2";
        } else if (type.equals("4")) {
            //待评价
            rb_yishouhuo.setChecked(true);
            ostatus = "3";
        }
       /* initAdapter();
        et_go.setText("全部订单");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = getIntent().getStringExtra("type");
        } else {
            type = "0";
        }
        if (type.equals("0")) {
            //全部
            rb_all.setChecked(true);
            ostatus = "";
        } else if (type.equals("1")) {
            //待付款
            rb_daifukuan.setChecked(true);
            ostatus = "0";
        } else if (type.equals("2")) {
            //代发货
            rb_daifahuo.setChecked(true);
            ostatus = "1";
        } else if (type.equals("3")) {
            //待收货
            rb_daishouhuo.setChecked(true);
            ostatus = "2";
        } else if (type.equals("4")) {
            //待评价
            rb_yishouhuo.setChecked(true);
            ostatus = "3";
        } */
    }

    /**
     * 设置RecyclerView属性
     */
    private void initAdapter() {
        rv_all_order.setLayoutManager(new GridLayoutManager(this, 1));
        mAdapter = new AllSellerOrderAdapter();
        rv_all_order.setAdapter(mAdapter);//设置adapter
        LoadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rv_all_order.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_order_view, (ViewGroup) rv_all_order.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_all_order.getParent(), false);
        rv_all_order.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                OrderListBean iCartItem = mAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                switch (itemViewId) {
                    /*查看订单详情*/
                    case R.id.fl:
                        Intent intent = new Intent(SellerAllOrderActivity.this, OrderSellerDetailsActivity.class);
                        intent.putExtra("onumber", iCartItem.getOnumber());//订单编号
                        intent.putExtra("ordertype", "0");//订单编号
                        startActivity(intent);
                        break;
                    /*查看物流*/
                    case R.id.ll_order_view_logistics:
                        String Otrackingname = iCartItem.getOtrackingname();//mFoodData.get(position).getOtrackingname();//快递名称
                        String Otrackingnumber = iCartItem.getOtrackingnumber();// mFoodData.get(position).getOtrackingnumber();//快递单号
                        bundle.putString("Otrackingname", Otrackingname);
                        bundle.putString("Otrackingnumber", Otrackingnumber);
                        goToActivity(LogisticsActivity.class, bundle);
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
                        // showpayydialog(mFoodData.get(position).getOnumber());
                        break;
                    /*确认收货*/
                    case R.id.ll_order_confirm_receipt:
                        //UpdateOrderStatus(mFoodData.get(position).getOid());
                        break;
                    /*去评论*/
                    case R.id.ll_order_evaluate:
                       /* shoplistBeanList = mFoodData.get(position).getShoplist();
                        for (int i = 0; i < shoplistBeanList.size(); i++) {
                            //获取时候商品信息
                            goodslistBeans = shoplistBeanList.get(i).getGoodslist();
                            gname = goodslistBeans.get(i).getGname();
                            gimg = goodslistBeans.get(i).getGimg();
                            gid = goodslistBeans.get(i).getGid();
                        }
                        Intent intent1 = new Intent(SellerAllOrderActivity.this, OrderCommentActivity.class);
                        intent1.putExtra("gname", gname);//商品名称
                        intent1.putExtra("gid", gid);//商品名称
                        intent1.putExtra("gimg", gimg);//商品图片
                        intent1.putExtra("oid", mFoodData.get(position).getOid());//订单id
                        startActivity(intent1);*/
                        break;
                    /*去发货*/
                    case R.id.ll_order_deliver_goods:

                        bundle.putString("oid", String.valueOf(mFoodData.get(position).getOid()));
                        goToActivity(DeliverGoodsActivity.class, bundle);
                        // UpdateOrderDeliverGoods(mFoodData.get(position).getOid());
                        break;
                    case R.id.ll_order_contact_merchant:
                        if (!NetworkUtils.isNetworkConnected(SellerAllOrderActivity.this)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(SellerAllOrderActivity.this).equals("")) {
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
                            int sid = mFoodData.get(position).getBuyers().getUid();
                            String name = mFoodData.get(position).getBuyers().getUname();
                            RongIM.getInstance().startPrivateChat(SellerAllOrderActivity.this, String.valueOf(sid), name);
                        }
                        break;
                }
            }
        });
    }

    private void cancelDelDialog(final int oid, final int postion) {
        final UIAlertView delDialog = new UIAlertView(SellerAllOrderActivity.this, "温馨提示", "确定要取消订单吗",
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

    private String gname;
    private String gimg;
    private int gid;

    private void showpayydialog(String onumber) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("onumber", onumber);//订单编号
        bundle.putString("payftype", "3");//提交订单传的id
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getSupportFragmentManager(), "payFragment");
    }

    private void showDelDialog(final int oid, final int postion) {
        final UIAlertView delDialog = new UIAlertView(SellerAllOrderActivity.this, "温馨提示", "删除订单后，您将无法查看该订单",
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
     * 确认发货*
     */
    public void UpdateOrderDeliverGoods(int oid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEORDERSTATUS)
                .tag(this)
                .params("oid", oid)
                .params("ostatus", "2")
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("确认发货成功");
                            getorderList(ostatus);
                        } else {
                            ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 确认收货*
     */
    public void UpdateOrderStatus(int oid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEORDERSTATUS)
                .tag(this)
                .params("oid", oid)
                .params("ostatus", "3")
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("确认收货成功");
                            getorderList(ostatus);
                        } else {
                            ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 删除订单
     */
    private void DeleteOrder(int oid, final int position) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(SellerAllOrderActivity.this, null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                            mFoodData.remove(position);
                            mAdapter.notifyItemRemoved(position);
                        } else {
                            ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        Log.e("http---error", response.getException().toString());
                        ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }

    /**
     * 取消订单*
     */
    public void Cancelorder(int oid, final int position) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.CANCELORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            mAdapter.notifyItemChanged(position);
                            getorderList(ostatus);
                        } else {
                            ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (ostatus != null && !"".equalsIgnoreCase(ostatus.trim())) {
            getorderList(ostatus);
        } else if (mAdapter != null) {
            mAdapter.setEmptyView(notDataView);
        }
    }
    @Override
    public void initData() {

    }

    @OnClick({R.id.back, R.id.rb_all, R.id.rb_daifukuan, R.id.rb_daifahuo, R.id.rb_daishouhuo, R.id.rb_yishouhuo})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            //订单全部
            case R.id.rb_all:
                ostatus = "";
                // getorderList(ostatus);
                break;
            //订单dai付款
            case R.id.rb_daifukuan:
                ostatus = "0";
                // getorderList(ostatus);
                break;
            //订单dai发货
            case R.id.rb_daifahuo:
                ostatus = "1";
                //  getorderList(ostatus);
                break;
            //订单dai收货
            case R.id.rb_daishouhuo:
                ostatus = "2";
                // getorderList(ostatus);
                break;
            //订单已收货
            case R.id.rb_yishouhuo:
                ostatus = "3";
                //  getorderList(ostatus);
                break;
           /* case R.id.rb_refund_after_sale:
                ostatus = "5";
                getorderList(ostatus);
                break;*/
        }
        getorderList(ostatus);
    }

    /**
     * 根据用户id获取订单列表*
     */
    public void getorderList(String ostatus) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            mAdapter.setEmptyView(errorView);
        } else {
            OkGo.<BaseMode<List<OrderListBean>>>post(AppApi.BASE_URL + AppApi.GETORDERLIST)
                    .tag(this)
                    .params("ostatus", ostatus)
                    .params("sid", uid)
                    .execute(new JsonCallback<BaseMode<List<OrderListBean>>>(this) {
                        @Override
                        public void onStart(Request<BaseMode<List<OrderListBean>>, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(SellerAllOrderActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<List<OrderListBean>>> response) {
                         /*   stopDialog();
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
                                stopDialog();
                            } else {
                                ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
                            }*/
                            mFoodData = response.body().result;//返回的集合数据
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                if (mFoodData.size() == 0) {
                                    mAdapter.setEmptyView(notDataView);
                                }else{
                                    mAdapter.setNewData(mFoodData);
                                }
                                stopDialog();
                            } else {
                                stopDialog();
                                ToastUtils.showShort(SellerAllOrderActivity.this, response.body().msg);
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
}
