package com.yaer.remittance.ui.adapter;


import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 * 新品首发
 */

public class NewProductAdapter extends BaseQuickAdapter<NewGoodsBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;

    public NewProductAdapter() {
        super(R.layout.newproduct_item);
    }//newproduct_item

    @Override
    protected void convert(BaseSimpleViewHolder helper, final NewGoodsBean item) {
        helper.setText(R.id.tv_newname, item.getGname().trim());
        helper.setText(R.id.tv_newprice, "¥" + item.getGmoney());
        //helper.setText(R.id.tv_newgnumber, "剩" + item.getGnumber() + "件");
        RoundedImageView logoview = helper.getView(R.id.riv_newimage);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.setOnClickListener(R.id.ll_newproduct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
               // Log.e("text", "onClick: " + item.getGid());//地址id
                mContext.startActivity(intent);
            }
        });
    }
}
