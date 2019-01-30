package com.yaer.remittance.ui.user_modular.user_buyer.collect;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GetFollowGoodsBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 收藏
 */

public class UserCollectAdapter extends BaseQuickAdapter<GetFollowGoodsBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public UserCollectAdapter() {
        super(R.layout.shopping_collect_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetFollowGoodsBean item) {
        helper.setText(R.id.tv_goodsname, item.getGname());
        helper.setText(R.id.tv_goods_price, "¥" + item.getGmoney());
        boolean follw = item.getFollowStatus();
        TextView detils_NumS = helper.getView(R.id.tv_detils_NumS);
        detils_NumS.setText("取消收藏");
        ImageView goods_icon = helper.getView(R.id.goods_icon);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(goods_icon);//商品图片
        helper.addOnClickListener(R.id.tv_detils_NumS);
        helper.setOnClickListener(R.id.rl_collect_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                mContext.startActivity(intent);
            }
        });
    }
}
