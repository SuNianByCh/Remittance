package com.yaer.remittance.ui.adapter;


import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.utils.AmountUtil;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class ShoppingAdapter extends BaseQuickAdapter<SelectGoodsListBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;

    public ShoppingAdapter() {
        super(R.layout.shopping_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final SelectGoodsListBean item) {
        helper.setText(R.id.tv_shopping_name, item.getGname());
        String money = AmountUtil.priceNum(Double.parseDouble(item.getGmoney()));
        helper.setText(R.id.tv_shopping_gmoney, "¥ " + money);
        RoundedImageView logoview = helper.getView(R.id.iv_shoppingimg);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_shpping_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                mContext.startActivity(intent);
            }
        });
    }
}
