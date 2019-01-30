package com.yaer.remittance.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.CategoryBean;

import java.util.List;

/**
 * author：wangzihang
 * date： 2017/8/8 19:15
 * 分类按钮适配器
 * desctiption：
 * e-mail：wangzihang@xiaohongchun.com
 */

public class ClassItemAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> foodDatas;

    public ClassItemAdapter(Context context, List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean subcategory = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_class_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.item_home_name);
            viewHold.iv_icon = (RoundedImageView) convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_name.setText(subcategory.getGcname());
     /*   Uri uri = Uri.parse(subcategory.getGimg());
        viewHold.iv_icon.setImageURI(uri);*/
        Glide.with(context).load(subcategory.getGimg()).fitCenter().into(viewHold.iv_icon);//商品分类图片
        return convertView;

    }

    private static class ViewHold {
        private TextView tv_name;
        private RoundedImageView iv_icon;
    }

}
