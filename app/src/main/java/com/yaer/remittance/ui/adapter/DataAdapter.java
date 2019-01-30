package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class DataAdapter extends BaseQuickAdapter<SelectGoodsListBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public DataAdapter() {
        super(R.layout.data_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectGoodsListBean item) {
        helper.setText(R.id.tv_data_name, item.getGname());
        helper.setText(R.id.tv_data_gmoney, "¥ " + item.getGmoney());
        RoundedImageView logoview = helper.getView(R.id.riv_data_image);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_data_intent, new View.OnClickListener() {
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
