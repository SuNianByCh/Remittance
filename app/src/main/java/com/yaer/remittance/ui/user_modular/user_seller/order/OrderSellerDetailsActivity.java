package com.yaer.remittance.ui.user_modular.user_seller.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SelectOrderInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.ConfirmationOrderActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.ConfirmOrderActivity;
import com.yaer.remittance.ui.user_modular.DeliverGoodsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.logistics.LogisticsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.RefundActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.RefundReturnActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.UIAlertView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017/7/3.
 * 卖家订单详情展示
 */
public class OrderSellerDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_order_details)
    CustomTitlebar ct_order_details;
    private String onumber;//订单编号
    /*订单收货人名称*/
    @BindView(R.id.tv_order_details_name)
    TextView tv_order_details_name;
    /*订单收货人手机号*/
    @BindView(R.id.tv_order_details_phone)
    TextView tv_order_details_phone;
    /*订单收货人地址*/
    @BindView(R.id.tv_order_details_address)
    TextView tv_order_details_address;
    /*订单店铺名称*/
    @BindView(R.id.tvShopNameGroup)
    TextView tvShopNameGroup;
    /*订单店铺图片*/
    @BindView(R.id.iv_shop_image)
    ImageView iv_shop_image;
    /*    *//*订单商品名称*//*
    @BindView(R.id.tv_detils_ItemChild)
    TextView tv_detils_ItemChild;
    *//*订单商品图片*//*
    @BindView(R.id.iv_shop_detils_goods)
    ImageView iv_shop_detils_goods;
    *//*订单商品价格*//*
    @BindView(R.id.tv_detils_PriceNew)
    TextView tv_detils_PriceNew;*/
    /*订单商品数量*/
  /*  @BindView(R.id.tv_detils_NumS)
    TextView tv_detils_NumS;*/
    /*订单商品价格*/
    @BindView(R.id.tv_shop_detils_price)
    TextView tv_shop_detils_price;
    /*订单商品运费*/
    @BindView(R.id.tv_server_detils_price)
    TextView tv_server_detils_price;
    /*订单编号*/
    @BindView(R.id.dd_detiles_order_hao)
    TextView dd_detiles_order_hao;
    /*下单时间*/
    @BindView(R.id.tv_order_time)
    TextView tv_order_time;
    /*订单金额*/
    @BindView(R.id.yf_price)
    TextView yf_price;
    /*订单商品合计金额*/
    @BindView(R.id.tv_detils_total_money)
    TextView tv_detils_total_money;
    /*立即付款按钮*/
/*    @BindView(R.id.tv_immediately_payment)
    TextView tv_immediately_payment;*/
    /*去评价*/
    @BindView(R.id.ll_order_detils_evaluate)
    LinearLayout ll_order_detils_evaluate;
    /*删除订单*/
    @BindView(R.id.ll_order_detils_delete)
    LinearLayout ll_order_detils_delete;
    /*取消订单*/
    @BindView(R.id.ll_cancel_detils_order)
    LinearLayout ll_cancel_detils_order;
    /*联系商家*/
    @BindView(R.id.ll_order_contact_detils_merchant)
    LinearLayout ll_order_contact_detils_merchant;
    /*确认收货*/
    @BindView(R.id.ll_order_confirm_detils_receipt)
    LinearLayout ll_order_confirm_detils_receipt;
    /*查看物流*/
    @BindView(R.id.ll_order_view_detils_logistics)
    LinearLayout ll_order_view_detils_logistics;
    /*去发货*/
    @BindView(R.id.ll_order_confirm_detils_deliver_goods)
    LinearLayout ll_order_confirm_detils_deliver_goods;
    private String ordertype = "";
    /*订单状态*/
    @BindView(R.id.tv_ostatus_type)
    TextView tv_ostatus_type;
    /*申请退款*/
 /*   @BindView(R.id.ll_order_apply_refund)
    LinearLayout ll_order_apply_refund;*/
    @BindView(R.id.rv_shop_detils)
    RecyclerView rv_shop_detils;
    private OrderDetailsAdapter orderDetailsAdapter;
    private int oststuus;
    List<SelectOrderInfoBean.ShoplistBean.GoodslistBean> goodslistBeanList = null;//商品信息
    private int shopid;//店铺id
    private int oid;//订单id
    /*订单状态描述*/
    @BindView(R.id.jiaoyi_info)
    TextView jiaoyi_info;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_order_details).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.order_seller_details_layout;
    }

    @Override
    public void initView() {
        ct_order_details.setAction(this);
        onumber = getIntent().getStringExtra("onumber");//订单编号
        ordertype = getIntent().getStringExtra("ordertype");
        Selectorderinfo(onumber);
        orderDetailsAdapter = new OrderDetailsAdapter();
        rv_shop_detils.setLayoutManager(new GridLayoutManager(this, 1));
        rv_shop_detils.setNestedScrollingEnabled(false);//禁止滑动
        rv_shop_detils.setAdapter(orderDetailsAdapter);//设置adapter
        rv_shop_detils.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                SelectOrderInfoBean.ShoplistBean.GoodslistBean goodslistBeanlist = orderDetailsAdapter.getData().get(position);
                switch (itemViewId) {
                    /*退款*/
                    case R.id.tv_refund_return:
                        if (oststuus == 1) {
                            Intent intent = new Intent(OrderSellerDetailsActivity.this, RefundActivity.class);
                            intent.putExtra("gid", goodslistBeanlist.getGid());//商品id
                            intent.putExtra("shopid", shopid);//店铺id
                            intent.putExtra("gname", goodslistBeanlist.getGname());//商品名称
                            intent.putExtra("gimg", goodslistBeanlist.getGimg());//商品图片
                            intent.putExtra("gmoney", goodslistBeanlist.getGmoney());//商品价格
                            intent.putExtra("ognumber", goodslistBeanlist.getOgnumber());//商品库存
                            intent.putExtra("Ototalvalue", Ototalvalue);//合计退款金额
                            intent.putExtra("oid", oid);//订单id
                            startActivity(intent);
                        } else if (oststuus == 2) {
                            Intent intent = new Intent(OrderSellerDetailsActivity.this, RefundReturnActivity.class);
                            intent.putExtra("gid", goodslistBeanlist.getGid());//商品id
                            intent.putExtra("shopid", shopid);//店铺id
                            intent.putExtra("gname", goodslistBeanlist.getGname());//商品名称
                            intent.putExtra("gimg", goodslistBeanlist.getGimg());//商品图片
                            intent.putExtra("gmoney", goodslistBeanlist.getGmoney());//商品价格
                            intent.putExtra("ognumber", goodslistBeanlist.getOgnumber());//商品库存
                            intent.putExtra("Ototalvalue", Ototalvalue);//合计退款金额
                            intent.putExtra("oid", oid);//订单id
                            startActivity(intent);
                        }
                        break;
                    /*审核中*/
                    case R.id.tv_refund_auditing:
                 /*       Intent intent = new Intent(RefundInfoListActivity.this, RefundReturnDetailsActivity.class);
                        intent.putExtra("rid", goodslistBeanlist.getRid());
                        startActivity(intent);
                        ToastUtils.showToast("审核中。。。");*/
                        break;
                    /*通过*/
                    case R.id.tv_refund_return_adopt:
                        //ToastUtils.showToast("通过。。。");
                        break;
                    /*拒绝*/
                    case R.id.tv_refund_return_refuse:
                        //ToastUtils.showToast("拒绝。。。");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    private String sname;//店铺名称
    private String gname;//商品名称
    private double gmoney;//商品价格
    private int ognumber;//商品购买数量
    private String simg;//店铺图片
    private String gimg;//商品图片
    private String Ototalvalue;//合计金额
    private int sid;//用户id
    private String sunmae;//用户名称
    private String Otrackingname;//快递名称
    private String Otrackingnumber;//快递单号

    public void Selectorderinfo(String ordernumber) {
        OkGo.<BaseMode<SelectOrderInfoBean>>post(AppApi.BASE_URL + AppApi.SELECTORDERINFO)
                .tag(this)
                .params("ordernumber", ordernumber)
                .execute(new JsonCallback<BaseMode<SelectOrderInfoBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<SelectOrderInfoBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            /*订单收货地址*/
                            tv_order_details_name.setText(response.body().result.getAddressInfoModel().getAname().toString());
                            tv_order_details_phone.setText(response.body().result.getAddressInfoModel().getAphone().toString());
                            tv_order_details_address.setText(response.body().result.getAddressInfoModel().getAcity().toString() + response.body().result.getAddressInfoModel().getAdesc().toString());
                            /*订单信息*/
                            dd_detiles_order_hao.setText(response.body().result.getOnumber().toString());//订单编号
                            tv_order_time.setText(SystemUtil.stampToDate(response.body().result.getOtime().toString()));//下单时间
                            Ototalvalue = AmountUtil.priceNum(response.body().result.getOtotalvalue());
                            yf_price.setText("￥" + Ototalvalue);
                            Otrackingname = response.body().result.getOtrackingname();
                            Otrackingnumber = response.body().result.getOtrackingnumber();
                            /*商品信息店铺信息*/
                            List<SelectOrderInfoBean.ShoplistBean> shoplist = response.body().result.getShoplist();
                            for (int i = 0; i < shoplist.size(); i++) {
                                sname = response.body().result.getShoplist().get(i).getSname().toString();//店铺名称
                                shopid = response.body().result.getShoplist().get(i).getSid();//店铺id
                                simg = response.body().result.getShoplist().get(i).getSimg();//店铺图片
                                oid = response.body().result.getShoplist().get(i).getOid();//订单id
                                sid = response.body().result.getShoplist().get(i).getSid(); //mFoodData.get(position).getShoplist().get(0).getSid();
                                sunmae = response.body().result.getShoplist().get(i).getSuname();//mFoodData.get(position).getShoplist().get(0).getSuname();
                              /*  gname = response.body().result.getShoplist().get(i).getGoodslist().get(i).getGname().toString();//商品名称
                                gmoney = response.body().result.getShoplist().get(i).getGoodslist().get(i).getGmoney();//商品金额
                                ognumber = response.body().result.getShoplist().get(i).getGoodslist().get(i).getOgnumber();//商品购买数量
                                gimg = response.body().result.getShoplist().get(i).getGoodslist().get(i).getGimg();//商品图片*/
                                goodslistBeanList = response.body().result.getShoplist().get(i).getGoodslist();
                            }
                            orderDetailsAdapter.setNewData(goodslistBeanList);
                            //用逗号将字符串分开，得到字符串数组
                            Glide.with(OrderSellerDetailsActivity.this).load(simg).fitCenter().into(iv_shop_image);//订单店铺图片
                            tvShopNameGroup.setText(sname);
                           /* tv_detils_ItemChild.setText(gname);
                            tv_detils_PriceNew.setText("￥" + Ototalvalue);*/
                            // tv_detils_NumS.setText("x" + ognumber);
                            tv_shop_detils_price.setText("￥" + AmountUtil.priceNum(gmoney));
                            tv_detils_total_money.setText("￥" + Ototalvalue);
                            oststuus = response.body().result.getOstatus();
                            if (oststuus == 0) {
                                tv_ostatus_type.setText("等待您付款");
                                jiaoyi_info.setText("买家两小时未付款将自动退款");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.VISIBLE);//取消订单
                                ll_order_contact_detils_merchant.setVisibility(View.GONE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.GONE);//删除订单
                            } else if (oststuus == 1) {
                                tv_ostatus_type.setText("买家已付款");
                                jiaoyi_info.setText("买家已支付成功，请尽快发货");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.VISIBLE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.VISIBLE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.GONE);//删除订单
                                //ll_order_apply_refund.setVisibility(View.VISIBLE);//退款、退货
                            } else if (oststuus == 2) {
                                tv_ostatus_type.setText("等待买家收货");
                                jiaoyi_info.setText("已发货成功，请等待买家收货");
                                ll_order_view_detils_logistics.setVisibility(View.VISIBLE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.VISIBLE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.GONE);//删除订单
                                //  ll_order_apply_refund.setVisibility(View.VISIBLE);//退款、退货
                            } else if (oststuus == 3) {
                                tv_ostatus_type.setText("等待买家评价");
                                jiaoyi_info.setText("买家已收货成功，请等待买家评价");
                                ll_order_view_detils_logistics.setVisibility(View.VISIBLE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_contact_detils_merchant.setVisibility(View.VISIBLE);//联系商家
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.VISIBLE);//删除订单
                            } else if (oststuus == 4) {
                                tv_ostatus_type.setText("交易取消");
                                jiaoyi_info.setText("交易已取消");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.GONE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.VISIBLE);//删除订单
                            } else if (oststuus == 5) {
                                tv_ostatus_type.setText("申请退款中");
                                jiaoyi_info.setText("请确保已和对方协商一致");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.GONE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.GONE);//删除订单
                            } else if (oststuus == 6) {
                                tv_ostatus_type.setText("交易关闭");
                                jiaoyi_info.setText("交易已关闭");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.GONE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.VISIBLE);//删除订单
                            } else if (oststuus == 7) {
                                tv_ostatus_type.setText("交易完成");
                                jiaoyi_info.setText("交易已完成");
                                ll_order_view_detils_logistics.setVisibility(View.GONE);//查看物流
                                ll_cancel_detils_order.setVisibility(View.GONE);//取消订单
                                ll_order_confirm_detils_deliver_goods.setVisibility(View.GONE);//去发货
                                ll_order_contact_detils_merchant.setVisibility(View.GONE);//联系商家
                                ll_order_confirm_detils_receipt.setVisibility(View.GONE);//确认收货
                                ll_order_detils_evaluate.setVisibility(View.GONE);//评价
                                ll_order_detils_delete.setVisibility(View.VISIBLE);//删除订单
                            }
                        } else {
                        }
                    }
                });
    }

    @OnClick({R.id.ll_order_view_detils_logistics, R.id.ll_order_detils_delete, R.id.ll_cancel_detils_order, R.id.ll_order_contact_detils_merchant, R.id.ll_order_confirm_detils_deliver_goods})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            /*删除订单*/
            case R.id.ll_order_detils_delete:
                showDelDialog(oid);
                break;
            /*去发货*/
            case R.id.ll_order_confirm_detils_deliver_goods:
                bundle.putString("oid", String.valueOf(oid));
                goToActivity(DeliverGoodsActivity.class, bundle);
                break;
            /*联系买家*/
            case R.id.ll_order_contact_detils_merchant:
                if (!NetworkUtils.isNetworkConnected(OrderSellerDetailsActivity.this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(OrderSellerDetailsActivity.this).equals("")) {
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

                    RongIM.getInstance().startPrivateChat(OrderSellerDetailsActivity.this, String.valueOf(sid), sunmae);
                }
                break;
            /*查看物流*/
            case R.id.ll_order_view_detils_logistics:
                bundle.putString("Otrackingname", Otrackingname);
                bundle.putString("Otrackingnumber", Otrackingnumber);
                goToActivity(LogisticsActivity.class, bundle);
                break;
            /*取消订单*/
            case R.id.ll_cancel_detils_order:
                cancelDelDialog();
                break;
        }
    }

    private void cancelDelDialog() {
        final UIAlertView delDialog = new UIAlertView(OrderSellerDetailsActivity.this, "温馨提示", "确定要取消订单吗",
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
                                           Cancelorder();
                                       }
                                   }
        );
    }

    private void showDelDialog(final int oid) {
        final UIAlertView delDialog = new UIAlertView(OrderSellerDetailsActivity.this, "温馨提示", "删除订单后，您将无法查看该订单",
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
                                           DeleteOrder(oid);
                                       }
                                   }
        );
    }

    /**
     * 删除订单
     */
    private void DeleteOrder(int oid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(OrderSellerDetailsActivity.this, null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(OrderSellerDetailsActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(OrderSellerDetailsActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        Log.e("http---error", response.getException().toString());
                        ToastUtils.showShort(OrderSellerDetailsActivity.this, response.body().msg);
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
    public void Cancelorder() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.CANCELORDER)
                .tag(this)
                .params("oid", oid)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(OrderSellerDetailsActivity.this, null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            finish();
                        } else {
                            ToastUtils.showShort(OrderSellerDetailsActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        ToastUtils.showShort(OrderSellerDetailsActivity.this, response.body().msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }
/*

    private void showpayydialog(String onumber) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("onumber", onumber);//订单编号
        bundle.putString("payftype", "2");//提交订单传的id
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getSupportFragmentManager(), "payFragment");
    }
*/

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                if (ordertype.equals("1")) {
                    ConfirmationOrderActivity.confirmationOrderActivity.finish();
                    finish();
                } else if (ordertype.equals("0")) {
                    finish();
                } else if (ordertype.equals("2")) {
                    finish();
                } else if (ordertype.equals("4")) {
                    ConfirmOrderActivity.confirmOrderActivity.finish();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ordertype.equals("1")) {
                ConfirmationOrderActivity.confirmationOrderActivity.finish();
                finish();
            } else if (ordertype.equals("0")) {
                finish();
            } else if (ordertype.equals("2")) {
                finish();
            } else if (ordertype.equals("4")) {
                ConfirmOrderActivity.confirmOrderActivity.finish();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class OrderDetailsAdapter extends BaseQuickAdapter<SelectOrderInfoBean.ShoplistBean.GoodslistBean, BaseSimpleViewHolder> {
        public OrderDetailsAdapter() {
            super(R.layout.item_order_details);
        }

        /*订单状态--0(待付款)1(代发货)2(待收货)3（待评价）4已取消 5申请退款6已关闭*/
        @Override
        protected void convert(BaseSimpleViewHolder helper, SelectOrderInfoBean.ShoplistBean.GoodslistBean item) {
            SelectOrderInfoBean selectOrderInfoBean = new SelectOrderInfoBean();
            helper.setText(R.id.tv_allorder_gname, item.getGname());
            helper.setText(R.id.tvPriceNewmayer, "￥" + AmountUtil.priceNum(item.getGmoney()));
            helper.setText(R.id.shop_danwei, "x" + item.getOgnumber());
            ImageView logoview = helper.getView(R.id.iv_commodity_order_image);
            TextView tv_refund_return = helper.getView(R.id.tv_refund_return);//退款/退货
            TextView tv_refund_auditing = helper.getView(R.id.tv_refund_auditing);//退款审核中
            TextView tv_refund_return_adopt = helper.getView(R.id.tv_refund_return_adopt);//退款通过
            TextView tv_refund_return_refuse = helper.getView(R.id.tv_refund_return_refuse);//拒绝退款
            int grefundstatus = item.getGrefundstatus();
           /* if (oststuus == 0) {
                tv_refund_return.setVisibility(View.GONE);//退款
                tv_refund_auditing.setVisibility(View.GONE);//审核中
                tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
            } else if (oststuus == 1) {
                if (grefundstatus == 0) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.VISIBLE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                } else if (grefundstatus == 1) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.VISIBLE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                } else if (grefundstatus == 2) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.VISIBLE);//拒绝退款
                } else if (grefundstatus == 3) {
                    tv_refund_return.setText("退款");
                    tv_refund_return.setVisibility(View.VISIBLE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                }
            } else if (oststuus == 2) {
                if (grefundstatus == 0) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.VISIBLE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                } else if (grefundstatus == 1) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.VISIBLE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                } else if (grefundstatus == 2) {
                    tv_refund_return.setVisibility(View.GONE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.VISIBLE);//拒绝退款
                } else if (grefundstatus == 3) {
                    tv_refund_return.setText("退款/退货");
                    tv_refund_return.setVisibility(View.VISIBLE);//退款
                    tv_refund_auditing.setVisibility(View.GONE);//审核中
                    tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                    tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
                }
            } else if (oststuus == 3) {
                tv_refund_return.setVisibility(View.GONE);//退款
                tv_refund_auditing.setVisibility(View.GONE);//审核中
                tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
            } else if (oststuus == 4) {
                tv_refund_return.setVisibility(View.GONE);//退款
                tv_refund_auditing.setVisibility(View.GONE);//审核中
                tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
            } else if (oststuus == 5) {
                tv_refund_return.setVisibility(View.GONE);//退款
                tv_refund_auditing.setVisibility(View.GONE);//审核中
                tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
            } else if (oststuus == 6) {
                tv_refund_return.setVisibility(View.GONE);//退款
                tv_refund_auditing.setVisibility(View.GONE);//审核中
                tv_refund_return_adopt.setVisibility(View.GONE);//退款通过
                tv_refund_return_refuse.setVisibility(View.GONE);//拒绝退款
            }*/
            //用逗号将字符串分开，得到字符串数组
            String[] strs = item.getGimg().split(",");
            //将字符串数组转换成集合list
            Glide.with(mContext).load(strs[0]).fitCenter().into(logoview);//订单商品图片*/
            helper.addOnClickListener(R.id.tv_refund_return);//退款退货
            helper.addOnClickListener(R.id.tv_refund_auditing);//审核中。。
            helper.addOnClickListener(R.id.tv_refund_return_adopt);//通过
            helper.addOnClickListener(R.id.tv_refund_return_refuse);// 拒绝
        }
    }
}

