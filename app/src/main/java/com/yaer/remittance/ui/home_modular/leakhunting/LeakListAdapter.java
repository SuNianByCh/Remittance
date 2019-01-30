package com.yaer.remittance.ui.home_modular.leakhunting;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class LeakListAdapter extends BaseQuickAdapter<LeakHuntingBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public LeakListAdapter() {
        super(R.layout.leak_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final LeakHuntingBean item) {
        helper.setText(R.id.tv_leak_title, item.getGoodsInfoModels().getGname());
        helper.setText(R.id.leak_money, item.getGoodsInfoModels().getGlatestbid());
        helper.setText(R.id.leak_money, "¥ " + item.getGoodsInfoModels().getGlatestbid());
        helper.setText(R.id.tv_end_time, SystemUtil.getDate2String(Long.parseLong(item.getGoodsInfoModels().getGstoptime()), "yyyy-MM-dd HH:mm"));
      //  helper.setText(R.id.tv_person_number, item.getGoodsInfoModels().getGisauction() + "人");
        helper.setText(R.id.tv_remaining_time, SystemUtil.getDate2String(Long.parseLong(item.getGoodsInfoModels().getGtime()), "yyyy-MM-dd HH:mm"));
        ImageView logoview = helper.getView(R.id.iv_elementbeijin);
        list = new ArrayList<>();
        list.add(item.getGoodsInfoModels().getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_leak_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGoodsInfoModels().getGid());
                mContext.startActivity(intent);
            }
        });
    }
}
