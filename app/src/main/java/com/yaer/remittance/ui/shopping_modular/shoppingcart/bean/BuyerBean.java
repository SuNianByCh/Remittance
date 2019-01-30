package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;

import java.io.Serializable;

public class BuyerBean implements Serializable{
    //留言
    private String leaving;
    //商品数量
    private int commodityNum = 0;
    //总计金额
    private double totalMoney = 0d;
    private double postage=0d;

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public String getLeaving() {
        if (leaving == null) {
            return "";
        }
        return leaving;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void addInCommodityNum(int commodityNum) {
        this.commodityNum += commodityNum;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void addInTotalMoney(double totalMoney) {
        this.totalMoney += totalMoney;
    }
}
