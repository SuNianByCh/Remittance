package com.yaer.remittance.ui.shopping_modular.commodity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class CommodityImageAdapter extends BaseQuickAdapter<String, BaseSimpleViewHolder> {

    public CommodityImageAdapter() {
        super(R.layout.adapter_good_detail_imgs);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, String item) {
        ImageView logoview = helper.getView(R.id.iv_adapter_good_detail_img);
        Glide.with(mContext).load(item).fitCenter().into(logoview);//商品图片
    }
}
