package com.yaer.remittance.ui.news_modular.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class UserPrivateMassageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserPrivateMassageAdapter() {
        super(R.layout.item_private_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_nickname, item);
    }
}
