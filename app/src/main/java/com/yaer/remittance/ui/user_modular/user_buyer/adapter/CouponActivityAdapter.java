package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.bean.OrderListBean;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.view.FlowLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class CouponActivityAdapter extends BaseQuickAdapter<AllCouponBuyerBean, BaseViewHolder> {
    public CouponActivityAdapter() {
        super(R.layout.item_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllCouponBuyerBean item) {
        helper.setText(R.id.tv_money, "￥" + item.getCbmoney());
        helper.setText(R.id.tv_money_buyer, "满" + item.getCbmoney() + "可使用");
        helper.setText(R.id.coupon_type, item.getCbname());
        helper.setText(R.id.tv_coupon_time, "有效期至：" + SystemUtil.getDate2String(Long.parseLong(item.getCbendtime()), "yyyy-MM-dd HH:mm"));

    }
}
