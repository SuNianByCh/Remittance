package com.yaer.remittance.ui.shopping_modular.shoppingcart.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ocnyang.cartlayout.viewholder.CartViewHolder;
import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.R;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBeanDaoUtil;
import com.yaer.remittance.utils.ToastUtils;

public abstract class ChildViewHolder extends CartViewHolder implements View.OnClickListener {
    public TextView textViewReduce;
    public TextView textView;
    public TextView textViewPrice;
    public TextView textViewNum;
    public TextView textViewAdd;
    public ImageView draw_goods;
    public TextView tv_shop_gnumber;
    private int gnumber;
    private Context context;
    public TextView tv_gpostage;
    private int mOrginPostMoney;//

    public ChildViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        textView = itemView.findViewById(R.id.tv);
        textViewPrice = itemView.findViewById(R.id.tv_price);
        textViewReduce = ((TextView) itemView.findViewById(R.id.tv_reduce));
        textViewNum = itemView.findViewById(R.id.tv_num);
        textViewAdd = itemView.findViewById(R.id.tv_add);
        draw_goods = itemView.findViewById(R.id.draw_goods);
        tv_shop_gnumber = itemView.findViewById(R.id.tv_shop_gnumber);
        tv_shop_gnumber.setText("库存" + gnumber + "件");
        tv_gpostage = itemView.findViewById(R.id.tv_gpostage);//商品邮费
        itemView.setOnClickListener(this);
        textViewReduce.setOnClickListener(this);
        textViewAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOrginPostMoney = Integer.parseInt(((GoodsBean) mICartItem).getGpostage());
        switch (v.getId()) {
            case R.id.item:
                //Toast.makeText(v.getContext(), ((GoodsBean) mICartItem).getGoods_name(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BaseApplication.getInstance(), CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", String.valueOf(((GoodsBean) mICartItem).getGid()));// item.getGid(
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.getInstance().startActivity(intent);
                break;
            case R.id.tv_reduce:
                int intValue = Integer.valueOf(textViewNum.getText().toString()).intValue();
                if (intValue > 1) {
                    intValue--;
                    textViewNum.setText(String.valueOf(intValue));
                    ((GoodsBean) mICartItem).setGoods_amount(intValue);
                    onNeedCalculate();
                    ShopCartBeanDaoUtil.updateShopCardBean(((GoodsBean) mICartItem).getGid(), ((GoodsBean) mICartItem).getGnumber(), intValue);
                }
                //  ShopCartBeanDaoUtil.updateShopCardBean(((GoodsBean) mICartItem).getGid(), intValue);
                break;
            case R.id.tv_add:
                int intValue2 = Integer.valueOf(textViewNum.getText().toString()).intValue();
                gnumber = ((GoodsBean) mICartItem).getGnumber();
                if (intValue2 >= gnumber) {
                    ToastUtils.showToast("库存不足");
                } else {
                    intValue2++;
                    textViewNum.setText(String.valueOf(intValue2));
                    ((GoodsBean) mICartItem).setGoods_amount(intValue2);
                    ShopCartBeanDaoUtil.updateShopCardBean(((GoodsBean) mICartItem).getGid(), ((GoodsBean) mICartItem).getGnumber(), intValue2);
                    //  ShopCartBeanDaoUtil.updateShopCardBean(((GoodsBean) mICartItem).getGid(), intValue2);
                    onNeedCalculate();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 这里因为把 ViewHolder 没有写到 adapter 中作为内部类，所以对事件写了一个回调的抽象方法。
     * 如果不想这样写，你可以在以下方式中选其一：
     * 1. 将 ViewHolder 写到 Adapter 中作为内部类，这样你就可以访问 Adapter 中的一些方法属性了；
     * 2. 或者，你把 ItemView & ItemChildView 的事件放到 Adapter 中的 onBindViewHolder() 方法中设置。
     */
    public abstract void onNeedCalculate();
}
