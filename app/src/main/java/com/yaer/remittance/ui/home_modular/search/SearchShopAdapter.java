package com.yaer.remittance.ui.home_modular.search;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.liji.circleimageview.CircleImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.ShopInfoListByKeyBean;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;

/**
 * Created by geyifeng on 2017/6/3.
 * 新品首发
 */

public class SearchShopAdapter extends BaseQuickAdapter<ShopInfoListByKeyBean, BaseSimpleViewHolder> {

    public SearchShopAdapter() {
        super(R.layout.search_shop_item);
    }
    @Override
    protected void convert(BaseSimpleViewHolder helper, final ShopInfoListByKeyBean item) {
        helper.setText(R.id.tv_search_shop_name, item.getShopname());
        helper.setText(R.id.tv_search_shop_num, item.getShopfans());//关注数量
        helper.setText(R.id.tv_search_shop_slabel, item.getShoplabel().toString());
        CircleImageView logoview = helper.getView(R.id.civ_search_shop_tx);
        Glide.with(mContext).load(item.getShopimg()).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_search_shop, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShopActivity.class);
                intent.putExtra("shopinfosid", item.getShopid());
                mContext.startActivity(intent);
            }
        });
    }
}
