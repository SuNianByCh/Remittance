package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;

/**
 * Created by hj on 2017/6/3.
 */

public class UserjoinLotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserjoinLotAdapter() {
        super(R.layout.user_join_lot_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text1, item);
    }
}
