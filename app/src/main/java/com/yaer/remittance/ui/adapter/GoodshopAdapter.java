package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.liji.circleimageview.CircleImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.GoodshopBean;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class GoodshopAdapter extends BaseQuickAdapter<GoodshopBean, BaseSimpleViewHolder> {
    List<GoodshopBean.GoodsListModelsBean> goodslist = new ArrayList<>();
    ArrayList<String> list;
    CircleImageView goodshopimage;
    RecyclerView mGridRecyclerView;
    MyGridAdapter mGrideAdapter;

    public GoodshopAdapter() {
        super(R.layout.goodshop_item);
    }

    @Override
    public void onBindViewHolder(BaseSimpleViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (mGridRecyclerView == null) {
            return;
        } else {
            mGridRecyclerView.setHasFixedSize(true);
            LinearLayoutManager ms = new LinearLayoutManager(mContext);
            ms.setOrientation(LinearLayoutManager.HORIZONTAL);
            mGridRecyclerView.setLayoutManager(ms);
            //mGridRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            mGrideAdapter = new MyGridAdapter(R.layout.adapter_grid_receicer, goodslist);
            mGridRecyclerView.setAdapter(mGrideAdapter);
        }
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final GoodshopBean item) {
        helper.setText(R.id.tv_shangpinname, item.getSname());
        helper.setText(R.id.tv_goodshop_slabel, item.getSlabel());

        TextView tv_goodshop_follow = helper.getView(R.id.tv_goodshop_follow);
        boolean isfollow = item.isFollowStatus();
        if (isfollow == false) {
            tv_goodshop_follow.setText("+关注");
        } else {
            tv_goodshop_follow.setText("已关注");
        }
        mGridRecyclerView = helper.getView(R.id.mGridRecyclerView);
        goodslist = item.getGoodsListModels();
        goodshopimage = helper.getView(R.id.civ_goodshop_tx);
        Glide.with(mContext).load(item.getSimg()).fitCenter().into(goodshopimage);//店铺头像
        helper.addOnClickListener(R.id.rl_goodshop_id);//点击进入店铺
        helper.addOnClickListener(R.id.ll_goodshop_follow);//关注
    }
}

class MyGridAdapter extends BaseQuickAdapter<Object,BaseSimpleViewHolder> {
    private RoundedImageView image;
    ArrayList<String> list;
    String Images;

    public MyGridAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final Object item) {
        final GoodshopBean.GoodsListModelsBean bean = (GoodshopBean.GoodsListModelsBean) item;
        helper.setText(R.id.tv_grid_name, bean.getGname());
        TextView auction_identification = helper.getView(R.id.tv_auction_identification);
        helper.setText(R.id.tv_vitalityvalue, bean.getGhot());
        String money = bean.getGmoney();
        if (!money.equals("") || !"".equals(money)) {
            helper.setText(R.id.tv_grid_gmoney, "¥" + bean.getGmoney());
        } else {
            helper.setText(R.id.tv_grid_gmoney, "¥ 0.0");
        }
        final String gisauction = bean.getGisauction();
        if (gisauction.equals("0")) {
            auction_identification.setVisibility(View.GONE);
        } else {
            auction_identification.setVisibility(View.VISIBLE);
        }
        helper.setOnClickListener(R.id.ll_grid_receicer, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gisauction.equals("0")) {
                    Intent intent = new Intent(mContext, CommodityDetailsActivity.class);
                    intent.putExtra("gidshopping", bean.getGid());
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                    intent.putExtra("gidshopping", bean.getGid());
                    mContext.startActivity(intent);
                }

            }
        });
        RoundedImageView logoview = helper.getView(R.id.iv_grid_image);
        list = new ArrayList<>();
        list.add(((GoodshopBean.GoodsListModelsBean) item).getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
    }

    @Override
    public void onBindViewHolder(BaseSimpleViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }


}
