package com.yaer.remittance.ui.news_modular.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AllMessageBean;
import com.yaer.remittance.utils.SystemUtil;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class UserSystemMassageAdapter extends BaseQuickAdapter<AllMessageBean, BaseSimpleViewHolder> {
    public UserSystemMassageAdapter() {
        super(R.layout.item_system_message);
    }
    @Override
    protected void convert(BaseSimpleViewHolder helper, AllMessageBean item) {
        helper.setText(R.id.tv_mcontent, item.getMcontent());
        helper.setText(R.id.tv_mtime, SystemUtil.stampToDatemm(Long.parseLong(item.getMtime())));
    }
}
