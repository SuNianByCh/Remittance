package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.SelectGoodsBean;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class SelectedAdapter extends BaseQuickAdapter<SelectGoodsBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public SelectedAdapter() {
        super(R.layout.selected_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectGoodsBean item) {
        helper.setText(R.id.tv_selectname, item.getGname());
        String money = item.getGmoney();
        helper.setText(R.id.tv_vitalityvalue, item.getGhot());
        if (!money.equals("") || !"".equals(money)) {
            helper.setText(R.id.tv_select_price, "¥" + item.getGlatestbid());
        } else {
            helper.setText(R.id.tv_select_price, "¥ 0.0");
        }
        RoundedImageView selectimage = helper.getView(R.id.riv_selectimage);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(selectimage);//商品图片
        helper.setOnClickListener(R.id.ll_select_product, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                mContext.startActivity(intent);
            }
        });
    }
}
