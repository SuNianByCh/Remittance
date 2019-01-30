package com.yaer.remittance.ui.adapter;

/**
 * Created by Administrator on 2018-09-11.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yaer.remittance.R;

import java.util.List;
import java.util.Map;

/**
 * @author chenzheng_java
 * @description 该类的部分实现模仿了SimpleAdapter
 */
public class HelpAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater minflater;
    private List<Map<String, Object>> mlist;

    public HelpAdapter(Context context, List<Map<String, Object>> list) {
        this.mContext = context;
        minflater = LayoutInflater.from(context);
        mlist = list;
    }

    @Override
    public int getCount() {
        return this.mlist != null ? this.mlist.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewholder holder;
        if (convertView == null) {
            convertView = minflater.inflate(
                    R.layout.item_help,
                    null);
            holder = new viewholder();
            holder.tv_title = (TextView) convertView
                    .findViewById(R.id.title);
            holder.tv_info = (TextView) convertView
                    .findViewById(R.id.tv_info);
          /* holder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast("电话服务还没有开通哦");
                }
            });*/
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        return convertView;
    }

    public class viewholder {
        TextView tv_title, tv_info;
    }
}