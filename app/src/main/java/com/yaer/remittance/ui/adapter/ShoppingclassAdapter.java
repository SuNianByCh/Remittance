package com.yaer.remittance.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseSimpleViewHolder;
import com.yaer.remittance.bean.CategoryBean;
import com.yaer.remittance.ui.home_modular.classification.ClassificationScreeningActivity;

import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class ShoppingclassAdapter extends BaseQuickAdapter<CategoryBean.ResultBean, BaseSimpleViewHolder> {

    public ShoppingclassAdapter() {
        super(R.layout.shopping_class_item);
    }

    @Override
    protected void convert(BaseSimpleViewHolder helper, final CategoryBean.ResultBean item) {
        helper.setText(R.id.tv_shopping_class_name, item.getScname());
        RoundedImageView logoview = helper.getView(R.id.civ_shopping_class_image);
        Glide.with(mContext).load(item.getScimg()).fitCenter().into(logoview);//商品图片
        final List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> dataList = item.getGoodsClassIFicationInfoModels();
        helper.setOnClickListener(R.id.ll_shop_class, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ClassificationScreeningActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("gcid", "0");//传0设置二级的状态是全部
                intent.putExtra("name", item.getScname());//一级分类name
                intent.putExtra("scid", item.getScid() + "");//一级分类id
                intent.putExtras(bundle);//发送数据
                mContext.startActivity(intent);//启动intent
            }
        });
    }
}
