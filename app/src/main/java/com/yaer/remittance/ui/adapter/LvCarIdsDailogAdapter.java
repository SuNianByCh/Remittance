package com.yaer.remittance.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GoodsClassIFicationModelsBean;
import com.yaer.remittance.bean.SpecialEventBean;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class LvCarIdsDailogAdapter extends BaseQuickAdapter<GoodsClassIFicationModelsBean, BaseViewHolder> {
    public LvCarIdsDailogAdapter() {
        super(R.layout.lv_carids_dailog_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodsClassIFicationModelsBean item) {
        helper.setText(R.id.tv_dialog_name, item.getGcname());
    }
}
