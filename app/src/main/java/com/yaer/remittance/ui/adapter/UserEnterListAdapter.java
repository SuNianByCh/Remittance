package com.yaer.remittance.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.GoodsClassIFicationModelsBean;
import com.yaer.remittance.bean.LinkLabelBean;
import com.yaer.remittance.bean.SpecialEventBean;
import com.yaer.remittance.utils.ToastUtils;

import java.util.List;

/**
 * Created by geyifeng on 2017/6/3.
 */

public class UserEnterListAdapter extends RecyclerView.Adapter {

    private Context context;
    private final int CONTENT = 100;
    private int Zeng = 0;
    private List<LinkLabelBean> list;

    public UserEnterListAdapter(Context context, List<LinkLabelBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new LabelHolder(LayoutInflater.from(context).inflate(R.layout.user_enter_list, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LabelHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return CONTENT;
    }

    private class LabelHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private final LinearLayout llContent;

        public LabelHolder(View inflate) {
            super(inflate);
            textView = (TextView) inflate.findViewById(R.id.textViewContent);
            llContent = (LinearLayout) inflate.findViewById(R.id.llContent);
        }

        public void setData(final int position) {
            textView.setText(list.get(position).getStr());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).isSelect()) {
                        Zeng--;
                    }
                    if (Zeng < 3) {
                        if (textView.isSelected()) {
                            textView.setSelected(false);
                            list.get(position).setSelect(false);
                        } else {
                            ++Zeng;
                            textView.setSelected(true);
                            list.get(position).setSelect(true);
                        }
                    } else {
                        ToastUtils.showToast("最多只能选择3个标签");
                    }
                }
            });
        }
    }
}