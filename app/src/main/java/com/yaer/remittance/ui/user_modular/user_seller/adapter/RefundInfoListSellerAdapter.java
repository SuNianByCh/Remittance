package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.RefundInfoListBean;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class RefundInfoListSellerAdapter extends BaseQuickAdapter<RefundInfoListBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public RefundInfoListSellerAdapter() {
        super(R.layout.item_refundinfo);
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundInfoListBean item) {
        helper.setText(R.id.tv_allorder_gname, item.getGname());
        helper.setText(R.id.shop_danwei, "数量：" + item.getGnumber());
        TextView tv_refundinfo_type = helper.getView(R.id.tv_refundinfo_type);
        int restatus = item.getRstatus();
        helper.setText(R.id.shop_refundinfo_money, "￥" + item.getGmoney());
        if (restatus == 0) {
            tv_refundinfo_type.setText("正在等待审核");
        }else if(restatus==1){
            tv_refundinfo_type.setText("通过审核");
        }else if(restatus==2){
            tv_refundinfo_type.setText("拒绝退款");
        }
        ImageView logoview = helper.getView(R.id.iv_commodity_order_image);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.addOnClickListener(R.id.rl_item_refundinfo);
    }
}
