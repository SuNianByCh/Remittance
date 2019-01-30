package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AuctionSpecialDetailsBean;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;

/**
 * Created by hj on 2017/6/3.
 */

public class UserSpecialDetailsAdapter extends BaseQuickAdapter<AuctionSpecialDetailsBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public UserSpecialDetailsAdapter() {
        super(R.layout.special_auction_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AuctionSpecialDetailsBean item) {
        helper.setText(R.id.tv_special_list_name, item.getGoodsInfoModel().getGname());//拍品名称
        helper.setText(R.id.tv_special_list_money, item.getGoodsInfoModel().getGlatestbid());//拍品金额
        helper.setText(R.id.tv_special_list_shopname,item.getGoodsInfoModel().getGdesc());//拍品描述

        helper.setText(R.id.tv_user_special_list_time, SystemUtil.getDate2String(Long.parseLong(item.getGoodsInfoModel().getGstoptime()),"yyyy-MM-dd HH:mm"));
  /*      TextView auctiontime = helper.getView(R.id.tv_user_special_list_time);
        auctiontime.setText(SystemUtil.stampToDateauction(item.getGoodsInfoModel().getGstoptime()));//截止时间*/
        ImageView specialimage = helper.getView(R.id.iv_special_list);
        list = new ArrayList<>();
        list.add(item.getGoodsInfoModel().getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(specialimage);//专场商品图片
        helper.setOnClickListener(R.id.ll_special_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", String.valueOf(item.getGoodsInfoModel().getGid()));
                mContext.startActivity(intent);
            }
        });
    }
}
