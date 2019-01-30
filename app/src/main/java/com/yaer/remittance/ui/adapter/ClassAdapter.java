package com.yaer.remittance.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.bean.CategoryBean;
import com.yaer.remittance.bean.GoodsClassIFicationModelsBean;
import com.yaer.remittance.ui.home_modular.classification.ClassificationScreeningActivity;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.GridViewForScrollView;

import java.io.Serializable;
import java.util.List;

/**
 * 右侧主界面ListView的适配器
 * 分类右侧适配器
 *
 * @author Administrator
 */
public class ClassAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryBean.ResultBean> foodDatas; // 这里只传了GridView的数据我弄一个试试

    public ClassAdapter(Context context, List<CategoryBean.ResultBean> foodDatas) {
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
        CategoryBean.ResultBean dataBean = foodDatas.get(position);
        final List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> dataList = dataBean.getGoodsClassIFicationInfoModels();
      /*  CategoryBean.DataBean dataBean = foodDatas.get(position); //   这是GridView的标题
        List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> dataList = foodDatas.get(position).getGoodsClassIFicationInfoModels(); // 这是GridView*/
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_class, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        ClassItemAdapter adapter = new ClassItemAdapter(context, dataList);
        viewHold.blank.setText(foodDatas.get(position).getScname());
        final String name = foodDatas.get(position).getScname();
        viewHold.gridView.setAdapter(adapter);
        viewHold.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ToastUtils.showToast("当前点击" + position + id);
//                List<CategoryBean.ResultBean.GoodsClassIFicationInfoModelsBean> goodsclass = foodDatas.get(position).getGoodsClassIFicationInfoModels();
                Intent intent = new Intent();
                intent.setClass(context, ClassificationScreeningActivity.class);
                Bundle bundle = new Bundle();
                // bundle.putSerializable("goodsclassList", (Serializable) dataList);//序列化返回集合
                intent.putExtra("scid", dataList.get(position).getScid());//二级分类查询id
                intent.putExtra("name", name);//分类一级name
                intent.putExtra("gcid", dataList.get(position).getGcid());//返回那id
                intent.putExtras(bundle);//发送数据
                context.startActivity(intent);//启动intent
            }
        });
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }

}
