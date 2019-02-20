package com.yaer.remittance.ui.user_modular.user_seller.extensioncenter;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.utils.SystemUtil;



/**
 * 推广中心
 */

public class ExtendShopAdapter extends BaseQuickAdapter<ExtendShopBean, BaseSimpleViewHolder> {

    public ExtendShopAdapter() {
        super(R.layout.extendshop_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, ExtendShopBean item) {
        helper.setText(R.id.tv_extenshop_item, item.getExtendMoneyInfoModels().getEname());
       // helper.setText(R.id.expanded_money, item.getExtendMoneyInfoModels().getEmoney());
        helper.setTextTime(R.id.expanded_time, item.getEstime());
        helper.addOnClickListener(R.id.ll_extension_item);//查看服务详情
    }
}
