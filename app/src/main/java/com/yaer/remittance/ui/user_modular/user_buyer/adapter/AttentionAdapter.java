package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GetFollowShopBean;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class AttentionAdapter extends BaseQuickAdapter<GetFollowShopBean, BaseSimpleViewHolder> {
    public AttentionAdapter() {
        super(R.layout.item_attention);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, GetFollowShopBean item) {
        helper.setText(R.id.tv_shop_nickname, item.getSname());
        helper.setText(R.id.tv_shop_label, item.getSlabel());
        Boolean attention = item.getFollowStatus();
        if (attention == true) {
            helper.setText(R.id.tv_attention_status, "已关注");
        } else {
            helper.setText(R.id.tv_attention_status, "+关注");
        }
        ImageView siconImage = helper.getView(R.id.civ_shop_icon);
        Glide.with(mContext).load(item.getSimg()).fitCenter().into(siconImage);//店铺头像
        helper.addOnClickListener(R.id.tv_attention_status);
        helper.addOnClickListener(R.id.rl_attention);
    }
}
