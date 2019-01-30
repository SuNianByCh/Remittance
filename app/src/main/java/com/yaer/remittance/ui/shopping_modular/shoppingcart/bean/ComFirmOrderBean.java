package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComFirmOrderBean implements Serializable{
    //这个表示店铺
    private ShopBean shopBean = null;
    //这个表示商品
    private List<GoodsBean> goodsBeanList = new ArrayList<>();
    //买家信息
    private BuyerBean buyerBean =null;
    //其他信息
    //价格
    private Double prices;
    //运费
    private Double freight;

    public ComFirmOrderBean(ShopBean shopBean) {
        this.shopBean = shopBean;
    }

    public ComFirmOrderBean(ShopBean shopBean, BuyerBean buyerBean) {
        this.shopBean = shopBean;
        this.buyerBean = buyerBean;
    }

    public List<GoodsBean> getGoodsBeanList() {
        return goodsBeanList;
    }

    public void addGoodsBeanInList(GoodsBean goodsBean) {
        this.goodsBeanList.add(goodsBean);
    }

    public ShopBean getShopBean() {
        return shopBean;
    }

    public void setShopBean(ShopBean shopBean) {
        this.shopBean = shopBean;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public BuyerBean getBuyerBean() {
        return buyerBean;
    }

    public void setBuyerBean(BuyerBean buyerBean) {
        this.buyerBean = buyerBean;
    }
}
