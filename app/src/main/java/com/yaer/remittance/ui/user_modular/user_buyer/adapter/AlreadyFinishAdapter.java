package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AtAuctionBean;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 结束
 */

public class AlreadyFinishAdapter extends BaseQuickAdapter<AtAuctionBean, BaseViewHolder> {
    ArrayList<String> list;
    String Images;
    List<AtAuctionBean.GoodsInfoModelBean> goodsInfoModelBeanList;
    public AlreadyFinishAdapter() {
        super(R.layout.finish_auction_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, AtAuctionBean item) {
       /* goodsInfoModelBeanList.add(item.getGoodsInfoModel());
        if (goodsInfoModelBeanList.size()==0){
            return;
        }else{
            helper.setText(R.id.at_auction_name, item.getGoodsInfoModel().getGname());
            String money = item.getGoodsInfoModel().getGmoney();
            if (!money.equals("") || !"".equals(money)) {
                helper.setText(R.id.tv_at_auction_money, "结拍价：¥" + item.getGoodsInfoModel().getGmoney());
            } else {
                helper.setText(R.id.tv_at_auction_money, "结拍价：¥ 0.0");
            }
            TextView auctiontime = helper.getView(R.id.tv_finish_auction_time);
            auctiontime.setText("截止时间："+SystemUtil.stampToDateauction(Long.parseLong(item.getGoodsInfoModel().getGstoptime())));//截止时间
            ImageView iv_at_auction = helper.getView(R.id.at_auction_icon);
            list = new ArrayList<>();
            list.add(item.getGoodsInfoModel().getGimg());
            for (int i = 0; i < list.size(); i++) {
                Images = list.get(i);
            }
            String[] arrayStr = new String[]{};// 字符数组
            arrayStr = Images.split(",");// 字符串转字符数组
            Glide.with(mContext).load(arrayStr[0]).fitCenter().into(iv_at_auction);//拍品图片
        }*/
    }
}
