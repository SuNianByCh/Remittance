package com.yaer.remittance.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.AuctionSpecialDetailsBean;
import com.yaer.remittance.utils.StringUtils;
/**
 * Created by hj on 2017/6/3.
 */

public class UserSpecialDetailsAdapter extends BaseQuickAdapter<AuctionSpecialDetailsBean, BaseSimpleViewHolder> {
    public UserSpecialDetailsAdapter() {
        super(R.layout.special_auction_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final AuctionSpecialDetailsBean item) {
        if (item.getGoodsInfoModel() == null) {
            return;
        }
        StringUtils.setText((TextView) helper.getView(R.id.tv_special_list_name), item.getGoodsInfoModel().getGname(),"--");//拍品名称
        StringUtils.setText((TextView) helper.getView(R.id.tv_special_list_money), item.getGoodsInfoModel().getGlatestbid(), "--");//拍品金额
        StringUtils.setTextTimeDefault((TextView) helper.getView(R.id.tv_user_special_list_time),item.getGoodsInfoModel().getGauctiontime(),"--");//拍品时间
        ImageView specialimage = helper.getView(R.id.iv_special_list);
        if (item.getGoodsInfoModel().getGimg() != null && item.getGoodsInfoModel().getGimg().contains(",")) {
            Glide.with(mContext).load(item.getGoodsInfoModel().getGimg().split(",")[0]).fitCenter().into(specialimage);//专场商品图片
        } else {
            Glide.with(mContext).load(R.drawable.secces_defaullt).fitCenter().into(specialimage);
        }
        helper.addOnClickListener(R.id.ll_special_list);

    }
}
