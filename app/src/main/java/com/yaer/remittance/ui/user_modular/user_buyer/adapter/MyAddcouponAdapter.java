package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.bean.AllCouponBuyerUser;
import com.yaer.remittance.bean.AllCouponSellerShopBean;
import com.yaer.remittance.utils.SystemUtil;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class MyAddcouponAdapter extends BaseQuickAdapter<AllCouponSellerShopBean, BaseViewHolder> {
    public MyAddcouponAdapter() {
        super(R.layout.item_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllCouponSellerShopBean item) {
        helper.setText(R.id.tv_money, "免手续费");
        helper.setText(R.id.tv_money_buyer, "免手续费一次");
        helper.setText(R.id.coupon_type, item.getCsname());
        helper.setText(R.id.tv_coupon_time, "有效期至：" + SystemUtil.getDate2String(Long.parseLong(item.getCsendtime()), "yyyy-MM-dd HH:mm"));
    }
}
