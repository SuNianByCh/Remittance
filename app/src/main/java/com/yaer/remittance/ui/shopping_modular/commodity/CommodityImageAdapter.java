package com.yaer.remittance.ui.shopping_modular.commodity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class CommodityImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommodityImageAdapter() {
        super(R.layout.adapter_good_detail_imgs);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView logoview = helper.getView(R.id.iv_adapter_good_detail_img);
        Glide.with(mContext).load(item).fitCenter().into(logoview);//商品图片
    }
}
