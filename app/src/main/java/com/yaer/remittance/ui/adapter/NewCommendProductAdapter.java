package com.yaer.remittance.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.GetMainBean;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 推荐新品
 */

public class NewCommendProductAdapter extends BaseQuickAdapter<GetMainBean.RecommendgoodsBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;

    public NewCommendProductAdapter() {
        super(R.layout.new_commendproduct_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final GetMainBean.RecommendgoodsBean item) {
        helper.setText(R.id.text1, item.getGname(), "---");
        helper.setText(R.id.tv_home_new_gmoney, "¥ " + item.getGlatestbid(),"---");
        RoundedImageView logoview = helper.getView(R.id.riv_new_commend_image);
        helper.setText(R.id.tv_vitalityvalue, String.valueOf(item.getGhot()),"---");//火力值
        if (item.getGimg() != null && item.getGimg().contains(",")) {
            Glide.with(mContext).load(item.getGimg().split(",")[0]).fitCenter().into(logoview);//商品图片
        } else {
            Glide.with(mContext).load(item.getGimg()).fitCenter().into(logoview);//商品图片
        }
        helper.addOnClickListener(R.id.ll_new_comment_product);
    }
}
