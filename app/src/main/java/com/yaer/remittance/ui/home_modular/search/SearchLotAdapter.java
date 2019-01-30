package com.yaer.remittance.ui.home_modular.search;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 新品首发
 */

public class SearchLotAdapter extends BaseQuickAdapter<selectGoodsByNameBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public SearchLotAdapter() {
        super(R.layout.search_lot_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final selectGoodsByNameBean item) {
        helper.setText(R.id.tv_search_lot_name, item.getGname());
        String money = AmountUtil.priceNum(Double.parseDouble(item.getGlatestbid()));//带后两位的出价价格
        try {
            helper.setText(R.id.tv_auction_time, SystemUtil.getDate2String(Long.parseLong(item.getGstoptime()), "yyyy-MM-dd HH:mm")); //结束时间
        }catch (Exception e){
        }

        if (money.equals("")) {
            helper.setText(R.id.tv_search_lot_price, "¥ " + "0.00");
        } else {
            helper.setText(R.id.tv_search_lot_price, "¥ " + money);
        }
        RoundedImageView logoview = helper.getView(R.id.riv_search_lot_image);
        helper.setText(R.id.tv_hot, item.getGhot());
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_search_lot_product, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                mContext.startActivity(intent);
            }
        });
    }
}
