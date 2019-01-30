package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GetMainBean;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 推荐新品
 */

public class NewCommendProductAdapter extends BaseQuickAdapter<GetMainBean.RecommendgoodsBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public NewCommendProductAdapter() {
        super(R.layout.new_commendproduct_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetMainBean.RecommendgoodsBean item) {
        helper.setText(R.id.text1, "" + item.getGname());
        helper.setText(R.id.tv_home_new_gmoney, "¥ " + item.getGlatestbid());
        RoundedImageView logoview = helper.getView(R.id.riv_new_commend_image);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        helper.setText(R.id.tv_vitalityvalue, "" + item.getGhot());//火力值
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.addOnClickListener(R.id.ll_new_comment_product);
       /* helper.setOnClickListener(R.id.ll_new_comment_product, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", String.valueOf(item.getGid()));
                Log.e("text", "onClick: " + String.valueOf(item.getGid()));//地址id
                mContext.startActivity(intent);
            }
        });*/
    }
}
