package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.circleimageview.CircleImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.ZeroElementbeat;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.utils.SystemUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by geyifeng on 2019/6/3.
 */

public class HistoricalAuctionAdapter extends BaseQuickAdapter<ZeroElementbeat, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;
    CircleImageView shopimage;
    public HistoricalAuctionAdapter() {
        super(R.layout.historical_auction_item);
    }
    @Override
    protected void convert(BaseSimpleViewHolder helper, final ZeroElementbeat item) {
        helper.setText(R.id.element_history_name, item.getGname());
        helper.setText(R.id.tv_elementshop_name, item.getShopInfoModel().getSname());
     /*   boolean sfans = item.isFollowStatus();
        if (sfans == false) {
            helper.setText(R.id.tv_element_fans, "+关注");
        } else {
            helper.setText(R.id.tv_element_fans, "已关注");
        }*/
        helper.setText(R.id.tv_Label, item.getShopInfoModel().getSlabel());
        shopimage = helper.getView(R.id.civ_historyshop_image);
        Glide.with(mContext).load(item.getShopInfoModel().getSimg()).fitCenter().into(shopimage);//店铺头像
        helper.setText(R.id.element_history_gmoney, "¥" , item.getGlatestbid(),"--");
       // String money = item.getGmoney();
       /* if (!money.equals("") || !"".equals(money)) {
            helper.setText(R.id.element_history_gmoney, "¥" + item.getGmoney());
        } else {
            helper.setText(R.id.element_history_gmoney, "¥ 0.0");
        }*/
        String starttime = SystemUtil.stampToDateauction(item.getGauctiontime());//开始时间
        String endTime = SystemUtil.stampToDateauction(item.getGstoptime());//结束时间
        String isendTime = SystemUtil.stampToDatemm(item.getGstoptime());//结束时间判断
        String time = SystemUtil.stampToDatemm(System.currentTimeMillis());//系统时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(time.toString());//开始时间
            Date date2 = dateFormat.parse(isendTime);//结  束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            TextView auctiontime=helper.getView(R.id.emelent_history_gtime);
            if (date1.getTime() >= date2.getTime()) {
                auctiontime.setText("拍卖结束");

            } else {
                auctiontime.setText(starttime + "至" + endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RoundedImageView logoview = helper.getView(R.id.riv_elementbackground);
        list = new ArrayList<>();
        list.add(item.getGimg());
        for (int i = 0; i < list.size(); i++) {
            Images = list.get(i);
        }
        String[] arrayStr = new String[]{};// 字符数组
        arrayStr = Images.split(",");// 字符串转字符数组
        Glide.with(mContext).load(arrayStr[0]).fitCenter().into(logoview);//商品图片
        helper.addOnClickListener(R.id.ll_historical_auction);
/*
        helper.setOnClickListener(R.id.ll_historical_auction, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuctionDetailsActivity.class);
                intent.putExtra("gidshopping", String.valueOf(item.getGid()));
                mContext.startActivity(intent);
            }
        });
*/
    }
}
