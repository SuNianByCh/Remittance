package com.yaer.remittance.bean;

/**
 * 精选拍品
 */
public class SelectGoodsBean {
    //商品id
    public String gid;
    //商品名称
    public String gname;
    //商品图片
    public String gimg;
    //是否是拍品
    public String gisauction;
    //价格
    public String gmoney;
    //描述
    public String gdesc;
    //分类id
    public String gcid;
    //数量
    private String gnumber;
    //视频
    private String gvideo;
    //起拍价
    private String gstartingprice;
    //加价幅度
    private String gaddprice;
    //截止时间
    private String gstoptime;
    //时间
    public String gtime;
    //收藏数量
    public String gcollateral;
    //店铺id
    public String sid;
    //拍卖时间
    public String gauctiontime;
    //
    public String gissoldout;
    //
    public String giscollectionid;
    //
    public String gpostage;
    //
    public String gisguarantee;
    //
    public String gquickrefund;
    //
    public String gGGCT;
    //
    public String gauthentication;
    //
    public String esid;
    //
    public String glatestbid;
    //是否关注
    public Boolean followStatus;
    //
    public String gstate;
    public String ghot;

    public String getGhot() {
        return ghot;
    }

    public void setGhot(String ghot) {
        this.ghot = ghot;
    }

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

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
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

    public String getGnumber() {
        return gnumber;
    }

    public void setGnumber(String gnumber) {
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

    public String getGissoldout() {
        return gissoldout;
    }

    public void setGissoldout(String gissoldout) {
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

    public String getGisguarantee() {
        return gisguarantee;
    }

    public void setGisguarantee(String gisguarantee) {
        this.gisguarantee = gisguarantee;
    }

    public String getGquickrefund() {
        return gquickrefund;
    }

    public void setGquickrefund(String gquickrefund) {
        this.gquickrefund = gquickrefund;
    }

    public String getgGGCT() {
        return gGGCT;
    }

    public void setgGGCT(String gGGCT) {
        this.gGGCT = gGGCT;
    }

    public String getGauthentication() {
        return gauthentication;
    }

    public void setGauthentication(String gauthentication) {
        this.gauthentication = gauthentication;
    }

    public String getEsid() {
        return esid;
    }

    public void setEsid(String esid) {
        this.esid = esid;
    }

    public String getGlatestbid() {
        return glatestbid;
    }

    public void setGlatestbid(String glatestbid) {
        this.glatestbid = glatestbid;
    }

    public Boolean getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Boolean followStatus) {
        this.followStatus = followStatus;
    }

    public String getGstate() {
        return gstate;
    }

    public void setGstate(String gstate) {
        this.gstate = gstate;
    }
    @Override
    public String toString() {
        return "SelectGoodsBean{" +
                "gid='" + gid + '\'' +
                ", gname='" + gname + '\'' +
                ", gimg='" + gimg + '\'' +
                ", gisauction='" + gisauction + '\'' +
                ", gmoney='" + gmoney + '\'' +
                ", gdesc='" + gdesc + '\'' +
                ", gcid='" + gcid + '\'' +
                ", gnumber='" + gnumber + '\'' +
                ", gvideo='" + gvideo + '\'' +
                ", gstartingprice='" + gstartingprice + '\'' +
                ", gaddprice='" + gaddprice + '\'' +
                ", gstoptime='" + gstoptime + '\'' +
                ", gtime='" + gtime + '\'' +
                ", gcollateral='" + gcollateral + '\'' +
                ", sid='" + sid + '\'' +
                ", gauctiontime='" + gauctiontime + '\'' +
                ", gissoldout='" + gissoldout + '\'' +
                ", giscollectionid='" + giscollectionid + '\'' +
                ", gpostage='" + gpostage + '\'' +
                ", gisguarantee='" + gisguarantee + '\'' +
                ", gquickrefund='" + gquickrefund + '\'' +
                ", gGGCT='" + gGGCT + '\'' +
                ", gauthentication='" + gauthentication + '\'' +
                ", esid='" + esid + '\'' +
                ", glatestbid='" + glatestbid + '\'' +
                ", followStatus=" + followStatus +
                ", gstate='" + gstate + '\'' +
                '}';
    }
}
