package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.SpecialEventBean;
import com.yaer.remittance.ui.home_modular.auctionspecial.AuctionSpecialDetailsActivity;
import com.yaer.remittance.utils.SystemUtil;

import java.text.ParseException;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class AuctionSpecialAdapter extends BaseQuickAdapter<SpecialEventBean, BaseViewHolder> {
    private ImageView iv_paimai;

    public AuctionSpecialAdapter() {
        super(R.layout.auction_pecial_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SpecialEventBean item) {
        helper.setText(R.id.special_name, item.getSename());//专场拍品名称
        helper.setText(R.id.special_auction_description, item.getSedesc());//专场拍品简介
        iv_paimai = helper.getView(R.id.iv_paimai);
        Glide.with(mContext).load(item.getSeimg()).fitCenter().into(iv_paimai);//专场拍品图片
        helper.setText(R.id.special_time, SystemUtil.getDate2String(Long.parseLong(item.getSetime()),"yyyy-MM-dd HH:mm"));
        helper.setOnClickListener(R.id.iv_paimai, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionSpecialDetailsActivity.class);
                intent.putExtra("sename", item.getSename());
                intent.putExtra("seid", String.valueOf(item.getSeid()));
                intent.putExtra("seimg", item.getSeimg());
                mContext.startActivity(intent);
            }
        });
    }
}
