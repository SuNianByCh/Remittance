package com.yaer.remittance.ui.user_modular.user_seller.help;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yaer.remittance.R;

import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(R.layout.item_help_parent, R.layout.item_help_parent);
        addItemType(R.layout.item_help_child, R.layout.item_help_child);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case R.layout.item_help_parent:
             final    ParentEntity parentEntity = (ParentEntity) item;
                helper.setText(R.id.tv_title, parentEntity.getHcname());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = helper.getLayoutPosition();
                        if (parentEntity.isExpanded()) {
                            collapse(layoutPosition);
                            helper.setImageResource(R.id.iv_expand,R.drawable.arrow_top);
                        } else {
                            helper.setImageResource(R.id.iv_expand,R.drawable.arrow_bouuton);
                            expand(layoutPosition);
                        }
                    }
                });
                break;
            case R.layout.item_help_child:
                final ParentEntity.HelpCenterInfoModelsBean helpCenterInfoModelsBean = (ParentEntity.HelpCenterInfoModelsBean) item;
                helper.setText(R.id.tv_content,helpCenterInfoModelsBean.getHctname());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserHelpDetailsActivity.start(mContext,helpCenterInfoModelsBean);
                    }
                });
                break;
        }


    }
}
