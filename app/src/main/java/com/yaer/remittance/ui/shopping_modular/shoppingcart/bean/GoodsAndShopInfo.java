package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsAndShopInfo implements Serializable {
    private List<ComFirmOrderBean> comFirmOrderBeanList;

    private int totalCount = 0;

    private double totalPrice = 0d;
    //总的订单你需要些什么数据呢？
    /**
     * 之前在购物车你计算过的可以放到数据类中，传过来，就不用再次计算了。如果没必要的话，恩可以直接就传过来，一个总值就可以，这样到了这边不用计算了，直接显示就可以，对
     * 就在这个类中定义就可以了，恩,我还需要提交每一个商品的订单，你看就需要这些数据0.0
     * @param comFirmOrderBeanList
     */

    public GoodsAndShopInfo(List<ComFirmOrderBean> comFirmOrderBeanList) {
        this.comFirmOrderBeanList = comFirmOrderBeanList;
    }

    public List<ComFirmOrderBean> getComFirmOrderBeanList() {
        return comFirmOrderBeanList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
