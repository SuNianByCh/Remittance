package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;

import com.ocnyang.cartlayout.bean.ChildItemBean;

import java.io.Serializable;

public class GoodsBean extends ChildItemBean implements Serializable{
    private String gid;//商品id
    private String uid;//用户id
    private String goods_name;//商品名称
    private double goods_price;//商品金额
    private String gdesc;//商品描述
    private int gcid;//
    private String gimg;//商品图片
    private int gnumber;//商品库存
    private String sid;//店铺id
    private ShopInfoModelBean shopInfoModelBean;
    private int goods_amount = 1;//购买数量用的是这个
    private String gpostage;//邮费

    public String getGpostage() {
        return gpostage;
    }

    public void setGpostage(String gpostage) {
        this.gpostage = gpostage;
    }

    public int getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(int goods_amount) {
        this.goods_amount = goods_amount;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public int getGcid() {
        return gcid;
    }

    public void setGcid(int gcid) {
        this.gcid = gcid;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public int getGnumber() {
        return gnumber;
    }

    public void setGnumber(int gnumber) {
        this.gnumber = gnumber;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public ShopInfoModelBean getShopInfoModelBean() {
        return shopInfoModelBean;
    }

    public void setShopInfoModelBean(ShopInfoModelBean shopInfoModelBean) {
        this.shopInfoModelBean = shopInfoModelBean;
    }
}
