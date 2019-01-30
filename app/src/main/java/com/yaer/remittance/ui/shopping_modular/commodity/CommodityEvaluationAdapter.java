package com.yaer.remittance.ui.shopping_modular.commodity;

import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.circleimageview.CircleImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.SelectGoodsInfoBean;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.view.StarBar;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class CommodityEvaluationAdapter extends BaseQuickAdapter<SelectGoodsInfoBean.GoodsCommentinfoModelsBean, BaseViewHolder> {

    private RatingBar sb_commodity_score;
    private CircleImageView iv_comment_shop_image;
    private String uicon = "";

    public CommodityEvaluationAdapter() {
        super(R.layout.adapter_commodity_evaluation);

    }

    @Override
    protected void convert(BaseViewHolder helper, SelectGoodsInfoBean.GoodsCommentinfoModelsBean item) {
        helper.setText(R.id.tv_comment_shop_describe, item.getUcdesc());//评论内容
        helper.setText(R.id.tv_comment_shop_name, item.getUname());//评论名称
        helper.setText(R.id.tv_commodity_gtime, SystemUtil.stampToDate(item.getGtime()));
        sb_commodity_score = helper.getView(R.id.sb_commodity_score);//星级评分
        sb_commodity_score.setRating(Float.parseFloat(item.getUcstars()));
       /* sb_commodity_score.setClickable(false);
        sb_commodity_score.setOnStarChangeListener(null);
        sb_commodity_score.setStarMark(Float.parseFloat(item.getUcstars()));*/
        iv_comment_shop_image = helper.getView(R.id.iv_comment_shop_image);//评论人头像
        uicon = item.getUicon();
        if (uicon.equals("")) {
            Glide.with(mContext).load(R.drawable.user_settings).fitCenter().into(iv_comment_shop_image);
        } else {
            Glide.with(mContext).load(item.getUicon()).fitCenter().into(iv_comment_shop_image);
        }
    }
}
