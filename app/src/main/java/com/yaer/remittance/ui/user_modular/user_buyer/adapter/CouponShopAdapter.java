package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.utils.SystemUtil;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class CouponShopAdapter extends BaseQuickAdapter<AllCouponBuyerBean, BaseSimpleViewHolder> {
    public CouponShopAdapter() {
        super(R.layout.item_shop_coupon);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, AllCouponBuyerBean item) {
        helper.setText(R.id.tv_money, "￥" + item.getCbmoney());
        helper.setText(R.id.tv_money_buyer, "满" + item.getCbmaxmoney() + "减" + item.getCbmoney());
        helper.setText(R.id.coupon_type, item.getCbname());
        helper.setText(R.id.tv_coupon_time, SystemUtil.getDate2String(Long.parseLong(item.getCbstartime()), "yyyy-MM-dd HH:mm") + "-" + SystemUtil.getDate2String(Long.parseLong(item.getCbendtime()), "yyyy-MM-dd HH:mm"));
        helper.addOnClickListener(R.id.tv_immediately_receive);
    }
}
