package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.liji.circleimageview.CircleImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.OrderListBean;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.view.FlowLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class AllOrderAdapter extends BaseQuickAdapter<OrderListBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;
    List<OrderListBean.ShoplistBean> shoplistBeanList = new ArrayList<>();
    List<OrderListBean.ShoplistBean.GoodslistBean> goodslistBeans = new ArrayList<>();
    private String osleaveword;//对店铺说的话
    private String gname;//商品名称
    private String gmoney;//商品价格
    private int ognumber;//库存
    private String gimg;//商品图片
    private String sname;//商铺名称
    private String simg;//店铺图片
    private int oststuus;//订单状态
    private LinearLayout ll_order_evaluate;//评价
    private LinearLayout ll_order_view_logistics;//查看物流
    private LinearLayout ll_order_delete;//删除订单
    private LinearLayout ll_cancel_order;//取消订单
    private LinearLayout ll_order_contact_merchant;//联系商家
    private LinearLayout ll_order_confirm_receipt;//确认收货
    private LinearLayout ll_order_payment;//支付订单

    public AllOrderAdapter() {
        super(R.layout.item_allorder);
    }

    /*订单状态--0(待付款)1(代发货)2(待收货)3（待评价）4已取消 5申请退款6已关闭*/
    @Override
    protected void convert(BaseSimpleViewHolder helper, OrderListBean item) {
        helper.setText(R.id.tv_shop_order_name, item.getOtime());
        ImageView logoview = helper.getView(R.id.iv_commodity_order_image);
        TextView order_ostatus = helper.getView(R.id.tv_all_order_ostatus);
        ll_order_view_logistics = helper.getView(R.id.ll_order_view_logistics);//查看物流
        ll_cancel_order = helper.getView(R.id.ll_cancel_order);//取消订单
        ll_order_contact_merchant = helper.getView(R.id.ll_order_contact_merchant);//联系客服
        ll_order_payment = helper.getView(R.id.ll_order_payment);//去支付
        ll_order_confirm_receipt = helper.getView(R.id.ll_order_confirm_receipt);//确认收货
        ll_order_evaluate = helper.getView(R.id.ll_order_evaluate);//评价
        ll_order_delete = helper.getView(R.id.ll_order_delete);//删除订单
        oststuus = item.getOstatus();
        if (oststuus == 0) {
            order_ostatus.setText("等待您付款");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.VISIBLE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.VISIBLE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.GONE);//删除订单
        } else if (oststuus == 1) {
            order_ostatus.setText("买家已付款");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.GONE);//删除订单
        } else if (oststuus == 2) {
            order_ostatus.setText("等待您收货");
            ll_order_view_logistics.setVisibility(View.VISIBLE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.VISIBLE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.GONE);//删除订单
        } else if (oststuus == 3) {
            order_ostatus.setText("等待您评价");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.GONE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.VISIBLE);//评价
            ll_order_delete.setVisibility(View.VISIBLE);//删除订单
        } else if (oststuus == 4) {
            order_ostatus.setText("交易取消");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.GONE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.VISIBLE);//删除订单
        } else if (oststuus == 5) {
            order_ostatus.setText("申请退款中");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.GONE);//删除订单
        } else if (oststuus == 6) {
            order_ostatus.setText("交易关闭");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.VISIBLE);//删除订单
        } else if (oststuus == 7) {
            order_ostatus.setText("交易完成");
            ll_order_view_logistics.setVisibility(View.GONE);//查看物流
            ll_cancel_order.setVisibility(View.GONE);//取消订单
            ll_order_contact_merchant.setVisibility(View.VISIBLE);//联系商家
            ll_order_payment.setVisibility(View.GONE);//支付
            ll_order_confirm_receipt.setVisibility(View.GONE);//确认收货
            ll_order_evaluate.setVisibility(View.GONE);//评价
            ll_order_delete.setVisibility(View.VISIBLE);//删除订单
        }
        List<OrderListBean.ShoplistBean> shoplist=item.getShoplist();
        for (int i = 0; i < shoplist.size(); i++) {
          goodslistBeans=shoplist.get(i).getGoodslist();
        }
        if (item!=null){
            helper.setText(R.id.heji_gmoney,"￥" + AmountUtil.priceNum(item.getOtotalvalue()));//商品合计金额
            helper.setText(R.id.tv_total_number,"共"+goodslistBeans.size()+"件");
        }
        if (item.getShoplist() != null && item.getShoplist().size() > 0)
            addGoodsItem((FlowLayout) helper.getView(R.id.fl), item.getShoplist().get(0).getGoodslist());
        if (item.getShoplist() != null && item.getShoplist().size() > 0) {//设置店铺相关的信息
            OrderListBean.ShoplistBean shoplistBean = item.getShoplist().get(0);
            helper.setText(R.id.tv_shop_order_name, shoplistBean.getSname());
            Glide.with(mContext).load(shoplistBean.getSimg()).fitCenter().into((ImageView) helper.getView(R.id.civ_order_all_image));//店铺头像
        }
        helper.addOnClickListener(R.id.fl);//查看订单详情
        helper.addOnClickListener(R.id.ll_cancel_order);//取消订单
        helper.addOnClickListener(R.id.ll_order_delete);//删除订单
        helper.addOnClickListener(R.id.ll_order_payment);//付款
        helper.addOnClickListener(R.id.ll_order_confirm_receipt);//确认收货
        helper.addOnClickListener(R.id.ll_order_evaluate);//去评论
        helper.addOnClickListener(R.id.ll_order_view_logistics);//查看物流
        helper.addOnClickListener(R.id.ll_order_contact_merchant);//联系商家
    }

    /**
     * 绑定订单信息
     * @param flowLayout
     * @param shoplistBeans----商品信息的集合
     */
    public void addGoodsItem(FlowLayout flowLayout, List<OrderListBean.ShoplistBean.GoodslistBean> shoplistBeans) {
        flowLayout.removeAllViews();
        if (shoplistBeans != null && !shoplistBeans.isEmpty()) {
            for (OrderListBean.ShoplistBean.GoodslistBean goodslistBean : shoplistBeans) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_allorder_goods, null, false);
                setText(view, R.id.tv_allorder_gname, goodslistBean.getGname());
                setText(view, R.id.tvPriceNewmayer, "￥" + goodslistBean.getGmoney());
                setText(view, R.id.shop_danwei, "x" + goodslistBean.getOgnumber());
                ImageView imageView = view.findViewById(R.id.iv_commodity_order_image);
                gimg = goodslistBean.getGimg();
                list = new ArrayList<>();
                list.add(gimg);
                for (int i = 0; i < list.size(); i++) {
                    Images = list.get(i);
                }
                String[] arrayStr = new String[]{};// 字符数组
                arrayStr = Images.split(",");// 字符串转字符数组
                Glide.with(mContext).load(arrayStr[0]).fitCenter().into(imageView);//商品图片
                flowLayout.addView(view, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
    }

    public void setText(View viewBase, @IdRes int viewId, CharSequence value) {
        if (viewBase == null) {
            return;
        }
        TextView view = getView(viewBase, viewId);
        if (view == null) {
            return;
        }
        view.setText(value);
    }
    private TextView getView(View viewBase, int viewId) {
        if (viewBase == null) {
            return null;
        }
        return viewBase.findViewById(viewId);
    }

    @Override
    public void setNewData(@Nullable List<OrderListBean> data) {
        isUseEmpty(false);
        super.setNewData(data);

    }

    @Override
    public void addData(@NonNull Collection<? extends OrderListBean> newData) {
        isUseEmpty(false);
        super.addData(newData);
    }

    @Override
    public void addData(@NonNull OrderListBean data) {
        isUseEmpty(false);
        super.addData(data);
    }
}
