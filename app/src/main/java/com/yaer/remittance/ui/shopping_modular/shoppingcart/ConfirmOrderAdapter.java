package com.yaer.remittance.ui.shopping_modular.shoppingcart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.R;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.BuyerBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ComFirmOrderBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopBean;
import com.yaer.remittance.view.MyEditText;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderAdapter extends RecyclerView.Adapter{
    private static final int TYPE_SHOP_HEAD = 1;
    private static final int TYPE_SHOP_GOODS = 2;
    private static final int TYPE_SHOP_FOOT = 3;
    ArrayList<String> list;
    String Images;

    //就用这个数据类型，免得转换来转换去的
    private List<ComFirmOrderBean> comFirmOrderBeanList;
    private List<Object> data = new ArrayList<>();

    public ConfirmOrderAdapter(List<ComFirmOrderBean> comFirmOrderBeanList) {
        this.comFirmOrderBeanList = comFirmOrderBeanList;
        if (comFirmOrderBeanList != null) {
            for (ComFirmOrderBean comFirmOrderBean : comFirmOrderBeanList) {
                data.add(comFirmOrderBean.getShopBean());
                data.addAll(comFirmOrderBean.getGoodsBeanList());
                data.add(comFirmOrderBean.getBuyerBean());
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SHOP_HEAD: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_confirm_item_shop_header, parent, false);
                return new HeadViewHolder(view);
            }

            case TYPE_SHOP_GOODS: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_confirm_item_shop_goods, parent, false);
                return new GoodsViewHolder(view);
            }

            default: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_confirm_item_shop_foot, parent, false);
                return new FootViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //这里绑定数据
        if (viewHolder instanceof HeadViewHolder) {
            //店铺信息
            HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
            ShopBean shopBean = (ShopBean) data.get(position);
            //图片你是怎么显示的呢？glide???,这个就是ok?
            Glide.with(BaseApplication.getInstance()).load(shopBean.getShop_simg()).fitCenter().into(headViewHolder.shopImage);//店铺头像
            headViewHolder.shopName.setText(shopBean.getShop_name());

        } else if (viewHolder instanceof GoodsViewHolder) {
            GoodsViewHolder goodsViewHolder = (GoodsViewHolder) viewHolder;
            GoodsBean goodsBean = (GoodsBean) data.get(position);
            goodsViewHolder.goodsName.setText(goodsBean.getGoods_name());
            goodsViewHolder.goodsPrice.setText(String.valueOf(goodsBean.getGoods_price()));
            goodsViewHolder.goodsNum.setText("x"+String.valueOf(goodsBean.getGoods_amount()));
            list = new ArrayList<>();
            list.add(goodsBean.getGimg());
            for (int i = 0; i < list.size(); i++) {
                Images = list.get(i);
            }
            String[] arrayStr = new String[]{};// 字符数组
            arrayStr = Images.split(",");// 字符串转字符数组
            Glide.with(BaseApplication.getInstance()).load(arrayStr[0]).fitCenter().into(goodsViewHolder.goodsImage);//商品图片
        } else {
            //这里是后面的尾巴，暂时不管
            final BuyerBean buyerBean = (BuyerBean)data.get(position);
            FootViewHolder footViewHolder = (FootViewHolder)viewHolder;
            footViewHolder.leaving.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    buyerBean.setLeaving(s.toString());
                }
            });
            footViewHolder.totalNum.setText(String.format("共%d件商品", buyerBean.getCommodityNum()));
            footViewHolder.totalPrice.setText(String.format(footViewHolder.itemView.getContext().getResources().getString(R.string.rmb_X),buyerBean.getTotalMoney()));
        }
    }

    @Override
    public int getItemCount() {
        if (comFirmOrderBeanList == null) {
            return 0;
        }
        int count = comFirmOrderBeanList.size() * 2;
        for (ComFirmOrderBean comFirmOrderBean : comFirmOrderBeanList) {
            count += comFirmOrderBean.getGoodsBeanList().size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int temp = 0;
        for (ComFirmOrderBean comFirmOrderBean : comFirmOrderBeanList ) {
            temp += 1;//这是头
            if (temp > position) {
                return TYPE_SHOP_HEAD;
            }
            temp += comFirmOrderBean.getGoodsBeanList().size();
            if (temp > position) {
                return TYPE_SHOP_GOODS;
            }
            //这是尾
            temp += 1;
            if (temp > position) {
                return TYPE_SHOP_FOOT;
            }
        }
        return TYPE_SHOP_HEAD;
    }

    //怎么判断是什么类型的呢什么类型，这里面index是0到size-1，没有理解。等我想想，你看到代码就知道了恩
    private

    class HeadViewHolder extends RecyclerView.ViewHolder {
        private ImageView shopImage;
        private TextView shopName;
        //定义控件变量
        HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.iv_confirm_item_shop_image);
            shopName = itemView.findViewById(R.id.tv_confirm_item_shop_name);
        }
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        private ImageView goodsImage;
        private TextView goodsName;
        private TextView goodsPrice;
        private TextView goodsNum;
        //定义控件变量
        GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImage = itemView.findViewById(R.id.iv_confirm_item_goods_image);
            goodsName = itemView.findViewById(R.id.tv_confirm_item_good_name);
            goodsPrice = itemView.findViewById(R.id.tv_confirm_item_good_price);
            goodsNum = itemView.findViewById(R.id.tv_confirm_item_good_num);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        //定义控件变量
        private MyEditText leaving;
        private TextView totalNum;
        private TextView totalPrice;
        FootViewHolder(@NonNull View itemView) {
            super(itemView);
            leaving = itemView.findViewById(R.id.ev_confirm_item_foot_leaving);
            totalNum = itemView.findViewById(R.id.tv_confirm_item_foot_total_num);
            totalPrice = itemView.findViewById(R.id.tv_confirm_item_foot_total_price);
        }
    }
}
