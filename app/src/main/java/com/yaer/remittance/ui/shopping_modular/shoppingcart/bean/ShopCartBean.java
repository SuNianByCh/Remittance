package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;


import org.litepal.crud.DataSupport;

/**
 * 商品表
 * 你的库存数量是哪个字段？？？
 * 哪你的购买数量用哪个字段了？？？？？？
 * <p>
 * <p>
 * 姐，保存的数据库与你要使用的东西字段都有出入，老陈无力啊！！！！！！！！！！！！！！！，就一个这个goods_amount不一样，其他的都一样，你帮我写两条sql就可以，
 * 一条是查询商品数量的，一条就是增加商品库存数量的就可以，你刚才贴出来的那个实体类和咱们现在用的这个实体没有啥关系
 * 这个是保存数据用的，那个实体是把这边的数据拿一下，然后去做支付操作，两个数据库没有啥关系，你只要管这个ShopCartBean这个实体，那个实体不用考虑
 * 我忍了
 * 好了，这么快
 **/
public class ShopCartBean extends DataSupport {
    /**
     * gid : 107
     * gname : 白金耳饰
     * gmoney : 15000
     * gdesc : 白金耳饰 刘诗诗主打
     * gcid : 132
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812192122-bcad613c-16cf-45f1-b428-b40c425d6058.png
     * gnumber : 1  这个是购买的数量？对
     * gtime : 1545225777039
     * sid : 94
     * shopInfoModel : {"sid":94,"sname":"乖宝宝","sfans":"","sgrade":"5.0","stime":"1545224543039","slabel":"天然玉石,珠串雅玩,民俗工艺","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192102-665df784-1ce0-4fdc-88cf-02b3c50df726.png","sbgimg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192102-50fddd9e-52ba-401d-bfb7-573598f43642.png","stype":25,"goodsListModels":[],"followStatus":false,"uicon":"","uname":""}
     */
    private String gid;//商品id
    private String uid;//用户id
    private String gname;//商品名称
    private double gmoney;//商品金额
    private String gdesc;//商品描述
    private int gcid;//
    private String gimg;//商品图片
    private int gnumber;//商品库存
    private String sid;//店铺id
    private String gpostage;//商品邮费

    public String getGpostage() {
        return gpostage;
    }

    public void setGpostage(String gpostage) {
        this.gpostage = gpostage;
    }

    //add by daben   购买数量  not  back by service;
    //private int goods_amount = 1;//购买数量用的是这个
    private ShopInfoModelBean shopInfoModelBean;

    public ShopInfoModelBean getShopInfoModelBean() {
        /*if (shopInfoModelBean == null &&  getSid() != null) {
            shopInfoModelBean =  DataSupport.where("sid=?",getSid()).findFirst(ShopInfoModelBean.class);
        }*/
        return shopInfoModelBean;
    }

    /**
     * 从数据库中取得
     *
     * @return
     */

    public ShopInfoModelBean getShopInfoModelBeanBySql() {
        if (getSid() != null) {
            return DataSupport.where("sid=?", getSid()).findFirst(ShopInfoModelBean.class);
        }
        return null;

    }


 /*   public int getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(int goods_amount) {
        this.goods_amount = goods_amount;
    }*/

    public void setShopInfoModelBean(ShopInfoModelBean shopInfoModelBean) {
        this.shopInfoModelBean = shopInfoModelBean;
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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGmoney() {
        return gmoney;
    }

    public void setGmoney(double gmoney) {
        this.gmoney = gmoney;
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
}
