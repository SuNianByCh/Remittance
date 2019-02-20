package com.yaer.remittance.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.SpecialEventBean;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class ActivityAreaAdapter extends BaseQuickAdapter<SpecialEventBean, BaseSimpleViewHolder> {
    ImageView activity_image;

    public ActivityAreaAdapter() {
        super(R.layout.activity_area_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, SpecialEventBean item) {
        helper.setText(R.id.tv_huodong, item.getSename());
        helper.setText(R.id.tv_desc, item.getSedesc());
        activity_image = helper.getView(R.id.iv_paimai);
        Glide.with(mContext).load(item.getSeimg()).fitCenter().into(activity_image);//商品图片
    }
}
