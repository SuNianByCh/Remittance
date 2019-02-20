package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.bean.getMyAuctionBean;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;

/**
 * 推广中心
 */

public class ExtensionAdapter extends BaseQuickAdapter<ExtendListBean, BaseSimpleViewHolder> {

    public ExtensionAdapter() {
        super(R.layout.extension_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, ExtendListBean item) {
        helper.setText(R.id.tv_extension_item, item.getEname());
        helper.addOnClickListener(R.id.ll_extension_item);//查看服务详情
    }
}
