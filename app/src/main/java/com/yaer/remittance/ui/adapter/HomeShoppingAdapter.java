package com.yaer.remittance.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.GetMainBean;

/**
 * Created by geyifeng on 2017/6/3.
 * 为你优选
 */

public class HomeShoppingAdapter extends BaseQuickAdapter<GetMainBean.ChoicegoodsBean, BaseSimpleViewHolder> {
    public HomeShoppingAdapter() {
        super(R.layout.home_commendproduct_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final GetMainBean.ChoicegoodsBean item) {
        helper.setText(R.id.text1, item.getGname(), "---");
        helper.setText(R.id.tv_home_new_gmoney, "¥ " + item.getGlatestbid(), "---");
        RoundedImageView logoview = helper.getView(R.id.riv_new_commend_image);
        if (item.getGimg() != null && item.getGimg().contains(",")) {
            Glide.with(mContext).load(item.getGimg().split(",")[0]).fitCenter().into(logoview);//商品图片
        } else {
            Glide.with(mContext).load(item.getGimg()).fitCenter().into(logoview);//商品图片
        }
        helper.setText(R.id.tv_vitalityvalue, String.valueOf(item.getGhot()), "---");//火力值
        helper.addOnClickListener(R.id.ll_new_comment_product);
    }
}
