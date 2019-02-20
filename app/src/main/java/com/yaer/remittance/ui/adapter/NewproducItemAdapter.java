package com.yaer.remittance.ui.adapter;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.utils.AmountUtil;

/**
 * Created by geyifeng on 2017/6/3.
 * 新品首发
 */

public class NewproducItemAdapter extends BaseQuickAdapter<NewGoodsBean, BaseSimpleViewHolder> {
    public NewproducItemAdapter() {
        super(R.layout.search_lot_item);
    }
    @Override
    protected void convert(BaseSimpleViewHolder helper, final NewGoodsBean item) {
        helper.setText(R.id.tv_search_lot_name, item.getGname());
        String money = item.getGlatestbid();
        helper.setText(R.id.tv_hot, item.getGhot());
        helper.setTextTime(R.id.tv_auction_time, item.getGstoptime());
        helper.setText(R.id.tv_search_lot_price, "¥ ", AmountUtil.priceNum(Double.parseDouble(money)), "¥0.00");
        RoundedImageView logoview = helper.getView(R.id.riv_search_lot_image);
        if (item.getGimg() != null && item.getGimg().contains(",")) {
            Glide.with(mContext).load(item.getGimg().split(",")[0]).fitCenter().into(logoview);
        } else {
            Glide.with(mContext).load(item.getGimg()).fitCenter().into(logoview);
        }
        helper.addOnClickListener(R.id.ll_search_lot_product);
    }
}
