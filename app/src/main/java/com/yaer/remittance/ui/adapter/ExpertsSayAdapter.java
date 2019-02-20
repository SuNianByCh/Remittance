package com.yaer.remittance.ui.adapter;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.SpecialistBean;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class ExpertsSayAdapter extends BaseQuickAdapter<SpecialistBean, BaseSimpleViewHolder> {
    private ImageView iv_expert_icon;

    public ExpertsSayAdapter() {
        super(R.layout.expertssay_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, SpecialistBean item) {
        helper.setText(R.id.tv_expert_name, item.getEsname());
        TextView usignature= helper.getView(R.id.tv_usignature);
        usignature.setText(Html.fromHtml(item.getEsdesc()));
        iv_expert_icon = helper.getView(R.id.iv_expert_icon);
        Glide.with(mContext).load(item.getEsimg()).error(R.drawable.zhuanjia).fitCenter().into(iv_expert_icon);//商品图片
        helper.addOnClickListener(R.id.ll_expertssay);
    }
}
