package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.getMyAuctionBean;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;

/**
 * 在售
 */

public class OnSaleAdapter extends BaseQuickAdapter<getMyAuctionBean, BaseViewHolder> {
    private String money;
    private int gisauction;
    ArrayList<String> list;
    String Images;

    public OnSaleAdapter() {
        super(R.layout.one_sale_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, getMyAuctionBean item) {
        helper.setText(R.id.on_sale_name, item.getGname());
        helper.setText(R.id.tv_one_sale_gnumber, "库存：" + item.getGnumber());
        helper.setText(R.id.tv_one_sale_gtime, SystemUtil.stampToDate1(Long.parseLong(item.getGtime())));
        ImageView icon = helper.getView(R.id.ic_one_saleicon);
        money = item.getGmoney();
        gisauction = item.getGisauction();
        if (gisauction == 0) {
            helper.setText(R.id.tv_one_sale_gisauction, "商品");
        } else {
            helper.setText(R.id.tv_one_sale_gisauction, "拍品");
        }
        if (money.equals("")) {
            helper.setText(R.id.tv_sale_money, "价格：0");
        } else {
            helper.setText(R.id.tv_sale_money, "价格：" + item.getGmoney());
        }
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(icon);//商品或拍品图片
        helper.addOnClickListener(R.id.tv_sale_lower_frame);//下架
        helper.addOnClickListener(R.id.ll_one_sale);//查看商品详情
        helper.addOnClickListener(R.id.tv_sale_edit);//编辑
    }
}
