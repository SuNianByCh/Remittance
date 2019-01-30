package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.WalletListBean;
import com.yaer.remittance.utils.SystemUtil;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class TotalAmountAdapter extends BaseQuickAdapter<WalletListBean, BaseViewHolder> {
    public TotalAmountAdapter() {
        super(R.layout.total_amount_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletListBean item) {
        String wtype = item.getWtype();
        if (wtype.equals("1")) {
            helper.setText(R.id.tv_detailed_recharge_type, "充值");
        } else if (wtype.equals("2")) {
            helper.setText(R.id.tv_detailed_recharge_type, "提现");
        } else if (wtype.equals("3")) {
            helper.setText(R.id.tv_detailed_recharge_type, "消费");
        } else if (wtype.equals("4")) {
            helper.setText(R.id.tv_detailed_recharge_type, "平台收入");
        } else if (wtype.equals("5")) {
            helper.setText(R.id.tv_detailed_recharge_type, "冻结");
        } else if (wtype.equals("6")) {
            helper.setText(R.id.tv_detailed_recharge_type, "退款");
        }

        helper.setText(R.id.tv_detailed_recharge_time, SystemUtil.stampToDatemm(item.getWtime()));
        helper.setText(R.id.tv_detailed_recharge_money, item.getWmoney());
    }
}
