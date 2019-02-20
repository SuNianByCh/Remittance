package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;

/**
 * Created by hj on 2017/6/3.
 */

public class UserjoinLotAdapter extends BaseQuickAdapter<String, BaseSimpleViewHolder> {
    public UserjoinLotAdapter() {
        super(R.layout.user_join_lot_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, String item) {
        helper.setText(R.id.text1, item);
    }
}
