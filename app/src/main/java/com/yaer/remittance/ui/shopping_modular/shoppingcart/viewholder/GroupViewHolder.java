package com.yaer.remittance.ui.shopping_modular.shoppingcart.viewholder;

import android.view.View;
import android.widget.TextView;

import com.liji.circleimageview.CircleImageView;
import com.ocnyang.cartlayout.viewholder.CartViewHolder;
import com.yaer.remittance.R;

public class GroupViewHolder extends CartViewHolder {
    public TextView textView;
    public CircleImageView iv_shopinfo_image;

    public GroupViewHolder(View itemView, int chekbox_id) {
        super(itemView, chekbox_id);
        textView = itemView.findViewById(R.id.tv);
        iv_shopinfo_image=itemView.findViewById(R.id.iv_shopinfo_image);
    }
}
