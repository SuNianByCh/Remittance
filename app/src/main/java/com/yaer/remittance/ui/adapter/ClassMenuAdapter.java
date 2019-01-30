package com.yaer.remittance.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yaer.remittance.R;

import java.util.List;

/**
 * 分类左侧菜单ListView的适配器
 *
 * @author Administrator
 */
public class ClassMenuAdapter extends BaseAdapter {

    private Context context;
    private int selectItem = 0;
    private List<String> list;

    public ClassMenuAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.item_menu, null);
            holder.tv_name = (TextView) arg1.findViewById(R.id.item_name);
            holder.iv_item_name=arg1.findViewById(R.id.iv_item_name);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        if (arg0 == selectItem) {
            holder.tv_name.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.main_tone));
            holder.iv_item_name.setVisibility(View.VISIBLE);
        } else {
            holder.tv_name.setBackgroundColor(context.getResources().getColor(R.color.background));
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.black));
            holder.iv_item_name.setVisibility(View.GONE);
        }
        holder.tv_name.setText(list.get(arg0));

        return arg1;
    }

    static class ViewHolder {
        private TextView tv_name;
        private ImageView iv_item_name;
    }
}
