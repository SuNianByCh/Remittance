package com.yaer.remittance.ui.home_modular.search;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.selectGoodsByNameBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 搜索商品adapter
 */

public class SearchCommodityAdapter extends BaseQuickAdapter<selectGoodsByNameBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    private String Images, money;

    public SearchCommodityAdapter() {
        super(R.layout.search_commdity_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final selectGoodsByNameBean item) {
        helper.setText(R.id.tv_search_commdity_name, item.getGname());
       /* if (item.getGmoney().equals("") && item.getGmoney().equals(null)) {
            money = AmountUtil.priceNum(Double.parseDouble(item.getGmoney()));
        }*/
        helper.setText(R.id.tv_search_commdity_gmoney, "¥" + item.getGmoney());//AmountUtil.priceNum()
        RoundedImageView logoview = helper.getView(R.id.iv_search_commdityimg);
        helper.setText(R.id.tv_shopping_vitalityvalue, item.getGhot());
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_search_commdity_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                Log.e("text", "onClick: " + item.getGid());//地址id
                mContext.startActivity(intent);
            }
        });
    }
}
