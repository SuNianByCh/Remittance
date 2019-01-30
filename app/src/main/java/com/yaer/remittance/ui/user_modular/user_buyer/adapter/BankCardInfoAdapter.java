package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.BankCardInfoBean;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.ManageForwardAccountActivity;
import com.yaer.remittance.utils.AppUtile;

import java.util.List;

/**
 * Created by hj on 2017/6/3.
 */

public class BankCardInfoAdapter extends BaseQuickAdapter<BankCardInfoBean, BaseViewHolder> {

    private ManageForwardAccountActivity maa;

    public BankCardInfoAdapter(ManageForwardAccountActivity mms, List<BankCardInfoBean> ManageForwardItemList) {
        super(R.layout.item_manage_forward, ManageForwardItemList);
        maa = mms;
    }

    @Override
    protected void convert(BaseViewHolder helper, final BankCardInfoBean item) {
        helper.setText(R.id.tv_bname, item.getBname());
        helper.setText(R.id.tv_bcardnum, AppUtile.hideCardNo(item.getBcardnum()));//银行卡号
        ImageView logoview = helper.getView(R.id.iv_bimg);
        Glide.with(mContext).load(item.getBimg()).fitCenter().into(logoview);//买家头像
    }
}
