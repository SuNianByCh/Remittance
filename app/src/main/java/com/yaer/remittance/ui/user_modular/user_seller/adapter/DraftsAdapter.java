package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.AddCollectionBean;

import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class DraftsAdapter extends BaseQuickAdapter<AddCollectionBean, BaseSimpleViewHolder> {
    public DraftsAdapter() {
        super(R.layout.item_drafts);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, AddCollectionBean addCollectionBean) {
        if (addCollectionBean.getSourceType() == AddCollectionBean.TYPE_PAI_PING) {
            paiPin(helper, addCollectionBean);
        } else {
            shangpin(helper, addCollectionBean);
        }
        helper.addOnClickListener(R.id.tv_drafts_edit);//编辑
        helper.addOnClickListener(R.id.tv_drafts_detele);//删除
    }

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_drafts_money)
    TextView tv_drafts_money;//商品金额
    @BindView(R.id.tv_auction_commodity_number)
    TextView tv_auction_commodity_number;//商品库存
    @BindView(R.id.tv_auction_commodity_type)
    TextView tv_auction_commodity_type;//商品拍品状态

    private void paiPin(BaseSimpleViewHolder helper, AddCollectionBean addCollectionBean) {
        helper.setText(R.id.name, addCollectionBean.getGoodsName());//商品名称
        helper.setText(R.id.tv_drafts_money, addCollectionBean.getGoodsPrice());//商品价格
        helper.setText(R.id.tv_auction_commodity_number, "库存 x" + addCollectionBean.getGoodsNumber());//商品库存
        if (addCollectionBean.getSourceType() == AddCollectionBean.TYPE_PAI_PING) {
            helper.setText(R.id.tv_auction_commodity_type, "拍品");
        } else {
            helper.setText(R.id.tv_auction_commodity_type, "商品");
        }
        List<String> imgesPathList = addCollectionBean.getImgesPath();
        if (imgesPathList != null && imgesPathList.size() > 0) {
            String s = imgesPathList.get(0);
            Glide.with(mContext).load(s).into((ImageView) helper.getView(R.id.icon));//商品图片
        }
    }

    private void shangpin(BaseSimpleViewHolder helper, AddCollectionBean addCollectionBean) {
        helper.setText(R.id.name, addCollectionBean.getGoodsName());
        helper.setText(R.id.tv_drafts_money, addCollectionBean.getGoodsPrice());//拍品起拍价格
        helper.setText(R.id.tv_auction_commodity_number, "库存 x" + addCollectionBean.getGoodsNumber());//商品库存
        if (addCollectionBean.getSourceType() == AddCollectionBean.TYPE_PAI_PING) {
            helper.setText(R.id.tv_auction_commodity_type, "拍品");
        } else {
            helper.setText(R.id.tv_auction_commodity_type, "商品");
        }
        List<String> imgesPathList = addCollectionBean.getImgesPath();
        if (imgesPathList != null && imgesPathList.size() > 0) {
            String s = imgesPathList.get(0);
            Glide.with(mContext).load(s).into((ImageView) helper.getView(R.id.icon));
        }
    }
}
