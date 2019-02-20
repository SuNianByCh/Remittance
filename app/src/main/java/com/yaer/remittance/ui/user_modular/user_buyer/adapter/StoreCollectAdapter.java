package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class StoreCollectAdapter extends BaseQuickAdapter<String, BaseSimpleViewHolder> {
    public StoreCollectAdapter() {
        super(R.layout.store_collect_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_nickname, item);
    }
}
