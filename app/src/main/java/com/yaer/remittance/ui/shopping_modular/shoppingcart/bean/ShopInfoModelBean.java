package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;


import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺信息表
 */
public class ShopInfoModelBean extends DataSupport {
    /**
     * sid : 94
     * sname : 乖宝宝
     * stime : 1545224543039
     * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192102-665df784-1ce0-4fdc-88cf-02b3c50df726.png
     * 13611126481，，g4030450
     */
    private String sid;//店铺id
    private String sname;//店铺名称
    private String stime;//店铺时间
    private String simg;//店铺头像


    private List<ShopCartBean> shopCartBeanList;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }


    public void addShopCarBean(ShopCartBean shopCartBean){
        if(shopCartBeanList == null){
            shopCartBeanList = new ArrayList<>();
        }
        if(shopCartBean != null && !shopCartBeanList.contains(shopCartBean) ){
           shopCartBeanList.add(shopCartBean);
        }
    }

    public List<ShopCartBean> getShopCartBeanList() {
        return shopCartBeanList;
    }

    public void setShopCartBeanList(List<ShopCartBean> shopCartBeanList) {
        this.shopCartBeanList = shopCartBeanList;
    }

    public void addShopCarBean(List<ShopCartBean> shopCartBeans) {
        if(shopCartBeans != null && !shopCartBeans.isEmpty()){
            for (ShopCartBean shop:shopCartBeans
                 ) {
                addShopCarBean(shop);
            }
        }
    }

    /*@Override
    public boolean isExpand() {
        return true;
    }

    @Override
    public void setExpand(boolean b) {

    }

    @Override
    public List getChildrenList() {
        return shopCartBeanList;
    }

    @Override
    public int getChildrenCount() {
        return shopCartBeanList == null ? 0 :shopCartBeanList.size();
    }*/
}