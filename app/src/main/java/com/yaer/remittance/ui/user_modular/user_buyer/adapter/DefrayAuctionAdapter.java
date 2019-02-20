package com.yaer.remittance.ui.user_modular.user_buyer.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AtAuctionBean;
import com.yaer.remittance.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 待支付
 */

public class DefrayAuctionAdapter extends BaseQuickAdapter<AtAuctionBean, BaseSimpleViewHolder> {
    ArrayList<String> list;
    String Images;
    List<AtAuctionBean.GoodsInfoModelBean> goodsInfoModelBeanList;
    public DefrayAuctionAdapter() {
        super(R.layout.defray_auction_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, AtAuctionBean item) {
       /* goodsInfoModelBeanList.add(item.getGoodsInfoModel());
            if (goodsInfoModelBeanList.size()==0){
                return;
            }else{
                helper.setText(R.id.at_auction_name, item.getGoodsInfoModel().getGname());
                String money = item.getGoodsInfoModel().getGmoney();
                if (!money.equals("") || !"".equals(money)) {
                    helper.setText(R.id.tv_at_auction_money, "当前价：¥" + item.getGoodsInfoModel().getGmoney());
                } else {
                    helper.setText(R.id.tv_at_auction_money, "当前价：¥ 0.0");
                }
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
