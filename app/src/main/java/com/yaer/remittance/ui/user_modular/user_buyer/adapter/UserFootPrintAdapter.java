package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.FootprIntBean;
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.bean.SpecialistBean;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 清单列表adapter
 * <p>
 * Created by DavidChen on 2018/5/30.
 */

public class UserFootPrintAdapter extends BaseQuickAdapter<FootprIntBean, BaseViewHolder> {
    private OnDeleteClickLister mDeleteClickListener;
    ArrayList<String> list;
    String Images;
    public UserFootPrintAdapter() {
        super(R.layout.user_footprint_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final FootprIntBean item) {
        View view = helper.getView(R.id.tv_delete);
        view.setTag(helper.getAdapterPosition());
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, helper.getAdapterPosition());
                    }
                }
            });
        }
        ((TextView) helper.getView(R.id.tv_user_footprint_name)).setText(item.getGname());
        TextView tv_money = (TextView) helper.getView(R.id.tv_footprint_mony);
        tv_money.setText("¥ " +item.getGmoney());
        ImageView logoview = ((ImageView) helper.getView(R.id.iv_userfoot_print));
       /* TextView tv_time = (TextView) helper.getView(R.id.tv_user_footprint_time);*/
        TextView footprintnum = (TextView) helper.getView(R.id.tv_footprint_num);
        footprintnum.setText(item.getGnumber() + "件");
        //tv_time.setText(bean.getGtime());
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//买家头像
        LinearLayout footprint = (LinearLayout) helper.getView(R.id.ll_footprint);
        footprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping",String.valueOf(item.getGid()));
                mContext.startActivity(intent);
            }
        });
    }
    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}
   /* ArrayList<String> list;
    String Images;
    private OnDeleteClickLister mDeleteClickListener;
    private Context mContext;

    public UserFootPrintAdapter(Context context, List<FootprIntBean> data) {
        super(context, data, R.layout.user_footprint_item);
        mContext = context;
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, final FootprIntBean bean, int position) {
        View view = holder.getView(R.id.tv_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        ((TextView) holder.getView(R.id.tv_user_footprint_name)).setText(bean.getGname());
        TextView tv_money = (TextView) holder.getView(R.id.tv_footprint_mony);
        tv_money.setText("¥ " + AmountUtil.priceNum(bean.getGmoney()));
        ImageView logoview = ((ImageView) holder.getView(R.id.iv_userfoot_print));
        TextView tv_time = (TextView) holder.getView(R.id.tv_user_footprint_time);
        TextView footprintnum = (TextView) holder.getView(R.id.tv_footprint_num);
        footprintnum.setText(bean.getGnumber() + "件");
        //tv_time.setText(bean.getGtime());
        list = new ArrayList<>();
        list.add(bean.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//买家头像
        LinearLayout footprint = (LinearLayout) holder.getView(R.id.ll_footprint);
        footprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                intent.putExtra("gidshopping", bean.getGid());
                Log.e("text", "onClick: " + bean.getGid());//地址id
                mContext.startActivity(intent);
            }
        });
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}*/

/*
public class UserFootPrintAdapter extends BaseQuickAdapter<FootprIntBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;

    public UserFootPrintAdapter() {
        super(R.layout.user_footprint_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootprIntBean item) {
        helper.setText(R.id.tv_user_footprint_name, item.getGname());
        helper.setText(R.id.tv_user_footprint_money, "¥ " + item.getGmoney());
        ImageView logoview = helper.getView(R.id.iv_userfoot_print);
        TextView tv_time = helper.getView(R.id.tv_user_footprint_time);
        Date times = AppUtile.strToDateLong(item.getGtime());
        tv_time.setText(AppUtile.getTime(times));
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//买家头像
    }
}
*/
