package com.yaer.remittance.ui.adapter;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.SelectGoodsBean;

import java.util.ArrayList;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class SelectedAdapter extends BaseQuickAdapter<SelectGoodsBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images = null;

    public SelectedAdapter() {
        super(R.layout.selected_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final SelectGoodsBean item) {
        helper.setText(R.id.tv_selectname, item.getGname());
        helper.setText(R.id.tv_vitalityvalue, item.getGhot());
        helper.setText(R.id.tv_select_price, "¥" + item.getGlatestbid());
        RoundedImageView selectimage = helper.getView(R.id.riv_selectimage);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        if (Images != null) {
            String[] arrayStr = null;// 字符数组这个地方报空指针了
            arrayStr = Images.split(",");// 字符串转字符数组
            Glide.with(mContext).load(arrayStr[0]).fitCenter().into(selectimage);//商品图片
        }
        helper.addOnClickListener(R.id.ll_select_product);
      /*  helper.setOnClickListener(R.id.ll_select_product, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", item.getGid());
                mContext.startActivity(intent);
            }
        });*/
    }
}
