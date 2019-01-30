package com.yaer.remittance.bean;

public class SelectGoodsListBean {
    /**
     * gid : 82
     * gname : 金惠美茶具定制
     * gisauction : 0
     * gmoney : 598
     * gdesc : 经济技术开发区一粒沙定制海淀区一粒沙定制北京一粒沙
     * gcid : 47
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-2378f1f3-bd62-426a-9a8d-1b5f4681558b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-f6a33ec6-3710-4fec-bca1-2014ea04a6b4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-03347ef5-4ecc-4e08-a20c-30f24e3a15e0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-df69ede3-93f5-4a0b-8dec-a4234fe39e6f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-13cf5513-7d66-4587-a9b4-a34eedcb9263.png
     * gnumber : 10
     * gvideo :
     * gstartingprice :
     * gaddprice :
     * gstoptime :
     * gtime : 1544724344622
     * gcollateral :
     * sid : 50
     * gauctiontime :
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 10
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     */

    private String gid;   //商品id
    private String gname;    //商品名称
    private int gisauction;//是否是拍品
    private String gmoney;//商品价格（发布商品时必传）
    private String gdesc;//商品简介
    private int gcid;//商品分类id
    private String gimg;//	商品图片（视频或图片必传一个）
    private int gnumber;//商品数量
    private String gvideo;//商品视频（视频或图片必传一个）
    private String gstartingprice;//商品起拍价格（拍品必传）
    private String gaddprice;//商品加价幅度（拍品必传）
    private String gstoptime;//拍卖截至时间（拍品必传）
    private String gtime;//发布时间
    private String gcollateral;//拍卖保证金（拍品必传）
    private String sid;//商品id
    private String gauctiontime;//拍卖起始时间（拍品必传）
    private int gissoldout;//是否下架
    private String giscollectionid;
    private String gpostage;
    private int gisguarantee;//担保交易
    private int gquickrefund;//	快速退款
    private int gGGCT;//正品保证
    private int gauthentication;//平台认证
    private int esid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getGisauction() {
        return gisauction;
    }

    public void setGisauction(int gisauction) {
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

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getGcollateral() {
        return gcollateral;
    }

    public void setGcollateral(String gcollateral) {
        this.gcollateral = gcollateral;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getGauctiontime() {
        return gauctiontime;
    }

    public void setGauctiontime(String gauctiontime) {
        this.gauctiontime = gauctiontime;
    }

    public int getGissoldout() {
        return gissoldout;
    }

    public void setGissoldout(int gissoldout) {
        this.gissoldout = gissoldout;
    }

    public String getGiscollectionid() {
        return giscollectionid;
    }

    public void setGiscollectionid(String giscollectionid) {
        this.giscollectionid = giscollectionid;
    }

    public String getGpostage() {
        return gpostage;
    }

    public void setGpostage(String gpostage) {
        this.gpostage = gpostage;
    }

    public int getGisguarantee() {
        return gisguarantee;
    }

    public void setGisguarantee(int gisguarantee) {
        this.gisguarantee = gisguarantee;
    }

    public int getGquickrefund() {
        return gquickrefund;
    }

    public void setGquickrefund(int gquickrefund) {
        this.gquickrefund = gquickrefund;
    }

    public int getGGGCT() {
        return gGGCT;
    }

    public void setGGGCT(int gGGCT) {
        this.gGGCT = gGGCT;
    }

    public int getGauthentication() {
        return gauthentication;
    }

    public void setGauthentication(int gauthentication) {
        this.gauthentication = gauthentication;
    }

    public int getEsid() {
        return esid;
    }

    public void setEsid(int esid) {
        this.esid = esid;
    }
}
