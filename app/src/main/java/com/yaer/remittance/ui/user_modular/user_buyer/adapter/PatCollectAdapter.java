package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class PatCollectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PatCollectAdapter() {
        super(R.layout.pat_collect_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.name, item);
    }
}
