package com.yaer.remittance.ui.shopping_modular.commodity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.liji.circleimageview.CircleImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GetBiddinListBean;
import com.yaer.remittance.bean.SelectGoodsInfoBean;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.view.StarBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class OfferRecordAdapter extends BaseQuickAdapter<GetBiddinListBean, BaseSimpleViewHolder> {
    String money = null;
    private String uicon;
    TextView tv_offer_type;

    public OfferRecordAdapter() {
        super(R.layout.adapter_offer_record);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, GetBiddinListBean item) {
        money = item.getBmoney();
        uicon = item.getUicon();
        tv_offer_type = helper.getView(R.id.tv_offer_type);
        if (mData.indexOf(item) == 0) {
            tv_offer_type.setText("领先");
            tv_offer_type.setBackgroundResource(R.color.colorred);
        } else {
            tv_offer_type.setText("出局");
            tv_offer_type.setBackgroundResource(R.color.view_xiahuaxie);
        }
        helper.setText(R.id.tv_offer_name, item.getUname());//出价名称
        helper.setText(R.id.tv_offer_time, SystemUtil.stampToDateauctionss(item.getBtime()));//出价时间
        helper.setText(R.id.tv_offer_gmoney, "￥" + item.getBmoney());
        Glide.with(mContext).load(item.getUicon()).error(R.drawable.join_logo).centerCrop().into((ImageView) helper.getView(R.id.civ_offer_portrait));

    }
}
