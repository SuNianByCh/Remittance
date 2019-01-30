package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GetMainBean;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;

/**
 * Created by geyifeng on 2017/6/3.
 * 推荐商家
 */

public class BusinessAdapter extends BaseQuickAdapter<GetMainBean.ShopInfoModelBean, BaseViewHolder> {

    public BusinessAdapter() {
        super(R.layout.business_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, final GetMainBean.ShopInfoModelBean item) {
        helper.setText(R.id.tv_shangpinname, item.getSname());
        boolean sfans = item.isFollowStatus();
        if (sfans == false) {
            helper.setText(R.id.tv_sfans, "+关注");
        } else {
            helper.setText(R.id.tv_sfans, "已关注");
        }
        helper.setText(R.id.tv_Label, item.getSlabel());
        RoundedImageView businessimage = (RoundedImageView) helper.getView(R.id.iv_business_image);
        ImageView uiconImage = helper.getView(R.id.civ_business_uiconImage);
        Glide.with(mContext).load(item.getSimg()).fitCenter().into(uiconImage);//店铺头像
        Glide.with(mContext).load(item.getSbgimg()).fitCenter().into(businessimage);//店铺加载图
        helper.addOnClickListener(R.id.tv_sfans);
        helper.setOnClickListener(R.id.iv_business_image, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShopActivity.class);
                intent.putExtra("shopinfosid", String.valueOf(item.getSid()));
                mContext.startActivity(intent);
            }
        });
    }
}
