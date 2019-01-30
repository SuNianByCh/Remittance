package com.yaer.remittance.ui.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.NewGoodsBean;

import java.util.ArrayList;

public class ShopCommodityNewProductAdapter extends BaseQuickAdapter<NewGoodsBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public ShopCommodityNewProductAdapter() {
        super(R.layout.shop_commodity_newproduct_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NewGoodsBean item) {
        helper.setText(R.id.tv_newname, item.getGname());
        helper.setText(R.id.tv_newprice, "¥" + item.getGmoney());
        //helper.setText(R.id.tv_newgnumber, "剩" + item.getGnumber() + "件");
        RoundedImageView logoview = helper.getView(R.id.riv_newimage);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组a
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.addOnClickListener(R.id.ll_newproduct);
     /*   helper.setOnClickListener(R.id.ll_newproduct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", String.valueOf(item.getGid()));
                mContext.startActivity(intent);
            }
        });*/
    }
}