package com.yaer.remittance.ui.shopping_modular.shoppingcart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ocnyang.cartlayout.CartAdapter;
import com.ocnyang.cartlayout.viewholder.CartViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.viewholder.ChildViewHolder;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.viewholder.GroupViewHolder;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.viewholder.NormalViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends CartAdapter<CartViewHolder> {
    /*商品图片集合*/
    ArrayList<String> list;
    String Images;

    public ShoppingCartAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected CartViewHolder getNormalViewHolder(View itemView) {
        return new NormalViewHolder(itemView, -1);
    }

    @Override
    protected CartViewHolder getGroupViewHolder(View itemView) {
        return (CartViewHolder) new GroupViewHolder(itemView, R.id.checkbox);
    }

    @Override
    protected CartViewHolder getChildViewHolder(View itemView) {
        return (CartViewHolder) (new ChildViewHolder(itemView, R.id.checkbox) {
            @Override
            public void onNeedCalculate() {
                if (onCheckChangeListener != null) {
                    onCheckChangeListener.onCalculateChanged(null);
                }
            }
        });
    }

    @Override
    protected int getChildItemLayout() {
        return R.layout.shopping_cart_item_child;
    }

    @Override
    protected int getGroupItemLayout() {
        return R.layout.shopping_cart_item_group;
    }

    @Override
    protected int getNormalItemLayout() {
        return R.layout.shopping_cart_item_normal;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ChildViewHolder) {
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            childViewHolder.textView.setText(((GoodsBean) mDatas.get(position)).getGoods_name());
            childViewHolder.textViewPrice.setText(
                    mContext.getString(R.string.rmb_X, ((GoodsBean) mDatas.get(position)).getGoods_price()));
            //childViewHolder.tv_gpostage.setText("邮费" + ((GoodsBean) mDatas.get(position)).getGpostage());//邮费
            childViewHolder.textViewNum.setText(String.valueOf(((GoodsBean) mDatas.get(position)).getGoods_amount()));
            list = new ArrayList<>();
            list.add(((GoodsBean) mDatas.get(position)).getGimg());
            for (int i = 0; i < list.size(); i++) {
                Images = list.get(i);
            }
            String[] arrayStr = new String[]{};// 字符数组
            arrayStr = Images.split(",");// 字符串转字符数组
            Glide.with(mContext).load(arrayStr[0]).fitCenter().into(childViewHolder.draw_goods);//商品图片
            childViewHolder.tv_shop_gnumber.setText("库存" + ((GoodsBean) mDatas.get(position)).getGnumber() + "件");
        } else if (holder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
            groupViewHolder.textView.setText(((ShopBean) mDatas.get(position)).getShop_name());
            Glide.with(mContext).load(((ShopBean) mDatas.get(position)).getShop_simg()).fitCenter().into(groupViewHolder.iv_shopinfo_image);//店铺头像
        }
    }
}
