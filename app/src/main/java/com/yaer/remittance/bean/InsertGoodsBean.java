package com.yaer.remittance.bean;

public class InsertGoodsBean {
    //用户id
    private int uid;
    //用户身份标识
    private String utoken;
    //商品名字
    private String gname;
    //是否是拍品
    private String gisauction;
    //商品价格（发布商品时必传）
    private String gmoney;
    //商品简介
    private String gdesc;
    //商品分类id
    private String gcid;
    //商品图片（视频或图片必传一个）
    private String gimg;
    //商品视频（视频或图片必传一个）
    private String gvideo;
    //商品起拍价格（拍品必传）
    private String gstartingprice;
    //商品加价幅度（拍品必传）
    private String gaddprice;
    //拍卖截至时间（拍品必传）
    private String gstoptime;
    //拍卖保证金（拍品必传）
    private String gcollateral;
    //拍卖起始时间（拍品必传）
    private String gauctiontime;
    //是否下架
    private String gissoldout;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGisauction() {
        return gisauction;
    }

    public void setGisauction(String gisauction) {
        this.gisauction = gisauction;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public String getGcid() {
        return gcid;
    }

    public void setGcid(String gcid) {
        this.gcid = gcid;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public String getGvideo() {
        return gvideo;
    }

    public void setGvideo(String gvideo) {
        this.gvideo = gvideo;
    }

    public String getGstartingprice() {
        return gstartingprice;
    }

    public void setGstartingprice(String gstartingprice) {
        this.gstartingprice = gstartingprice;
    }

    public String getGaddprice() {
        return gaddprice;
    }

    public void setGaddprice(String gaddprice) {
        this.gaddprice = gaddprice;
    }

    public String getGstoptime() {
        return gstoptime;
    }

    public void setGstoptime(String gstoptime) {
        this.gstoptime = gstoptime;
    }

    public String getGcollateral() {
        return gcollateral;
    }

    public void setGcollateral(String gcollateral) {
        this.gcollateral = gcollateral;
    }

    public String getGauctiontime() {
        return gauctiontime;
    }

    public void setGauctiontime(String gauctiontime) {
        this.gauctiontime = gauctiontime;
    }

    public String getGissoldout() {
        return gissoldout;
    }

    public void setGissoldout(String gissoldout) {
        this.gissoldout = gissoldout;
    }

}
