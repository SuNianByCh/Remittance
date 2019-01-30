package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;


import com.ocnyang.cartlayout.bean.GroupItemBean;

import java.io.Serializable;

public class ShopBean extends GroupItemBean implements Serializable{
    private String shop_sid;//店铺id
    private String shop_name;//店铺名称
    private String shop_time;//店铺时间
    private String shop_simg;//店铺头像

    public String getShop_sid() {
        return shop_sid;
    }

    public void setShop_sid(String shop_sid) {
        this.shop_sid = shop_sid;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public String getShop_simg() {
        return shop_simg;
    }

    public void setShop_simg(String shop_simg) {
        this.shop_simg = shop_simg;
    }
}
