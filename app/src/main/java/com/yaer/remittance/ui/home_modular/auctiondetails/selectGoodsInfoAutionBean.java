package com.yaer.remittance.ui.home_modular.auctiondetails;

import java.util.List;

public class selectGoodsInfoAutionBean {
    /**
     * gid : 266
     * gname : 哦
     * gisauction : 1
     * gmoney :
     * gdesc : 工地名字一样
     * gcid : 175
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901162309284091412ea6d-f52d-4355-b9c8-3b35628c7691.jpg
     * gnumber : 1
     * gvideo :
     * gstartingprice : 5
     * gaddprice : 1
     * gstoptime : 1547996940000
     * gtime : 1547651368979
     * gcollateral :
     * sid : 109
     * gauctiontime : 1547737740000
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 2
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     * glatestbid : 1
     * shopInfoModel : {"sid":109,"sname":"玉翠珠宝","sfans":"","sgrade":"5.0","stime":"1547348480139","slabel":"天然玉石","simg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20190113110119210dff535bf-7cad-47ab-8f0c-27a169563125.jpg","sbgimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901131101197901786fcec-9036-4aac-9dff-5a69cb1e5fc1.jpg","stype":35,"goodsListModels":[],"followStatus":false,"uicon":"","uname":""}
     * goodsCommentinfoModels : []
     * followStatus : false
     * gstate : 0
     * biddingnumber : 1
     * isautobidding:1
     */

    private String gid;
    private String gname;
    private int gisauction;
    private String gmoney;
    private String gdesc;
    private int gcid;
    private String gimg;
    private int gnumber;
    private String gvideo;
    private String gstartingprice;
    private String gaddprice;
    private String gstoptime;
    private String gtime;
    private String gcollateral;
    private String sid;
    private String gauctiontime;
    private int gissoldout;
    private String giscollectionid;
    private String gpostage;
    private int gisguarantee;
    private int gquickrefund;
    private int isautobidding;
    private int gGGCT;
    private int gauthentication;
    private int esid;
    private String glatestbid;//当前出价价格
    private ShopInfoModelBean shopInfoModel;
    private boolean followStatus;
    private int gstate;
    private int biddingnumber;
    private List<?> goodsCommentinfoModels;

    public int getIsautobidding() {
        return isautobidding;
    }

    public void setIsautobidding(int isautobidding) {
        this.isautobidding = isautobidding;
    }

    public int getgGGCT() {
        return gGGCT;
    }

    public void setgGGCT(int gGGCT) {
        this.gGGCT = gGGCT;
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
    public long getGstoptimeLong(){
        try {

            return Long.parseLong(gstoptime);
        }catch (Throwable e){
            return  0;
        }
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
    public  long getGauctiontimeLong(){
        try {
           return Long.parseLong(gauctiontime);
        }catch (Throwable e){

            return 0;
        }
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

    public String getGlatestbid() {
        return glatestbid;
    }

    public void setGlatestbid(String glatestbid) {
        this.glatestbid = glatestbid;
    }

    public ShopInfoModelBean getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(ShopInfoModelBean shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public boolean isFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(boolean followStatus) {
        this.followStatus = followStatus;
    }

    public int getGstate() {
        return gstate;
    }

    public void setGstate(int gstate) {
        this.gstate = gstate;
    }

    public int getBiddingnumber() {
        return biddingnumber;
    }

    public void setBiddingnumber(int biddingnumber) {
        this.biddingnumber = biddingnumber;
    }

    public List<?> getGoodsCommentinfoModels() {
        return goodsCommentinfoModels;
    }

    public void setGoodsCommentinfoModels(List<?> goodsCommentinfoModels) {
        this.goodsCommentinfoModels = goodsCommentinfoModels;
    }

    public static class ShopInfoModelBean {
        /**
         * sid : 109
         * sname : 玉翠珠宝
         * sfans :
         * sgrade : 5.0
         * stime : 1547348480139
         * slabel : 天然玉石
         * simg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20190113110119210dff535bf-7cad-47ab-8f0c-27a169563125.jpg
         * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901131101197901786fcec-9036-4aac-9dff-5a69cb1e5fc1.jpg
         * stype : 35
         * goodsListModels : []
         * followStatus : false
         * uicon :
         * uname :
         */

        private int sid;
        private String sname;
        private String sfans;
        private String sgrade;
        private String stime;
        private String slabel;
        private String simg;
        private String sbgimg;
        private int stype;
        private boolean followStatus;
        private String uicon;
        private String uname;
        private List<?> goodsListModels;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSfans() {
            return sfans;
        }

        public void setSfans(String sfans) {
            this.sfans = sfans;
        }

        public String getSgrade() {
            return sgrade;
        }

        public void setSgrade(String sgrade) {
            this.sgrade = sgrade;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getSlabel() {
            return slabel;
        }

        public void setSlabel(String slabel) {
            this.slabel = slabel;
        }

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public String getSbgimg() {
            return sbgimg;
        }

        public void setSbgimg(String sbgimg) {
            this.sbgimg = sbgimg;
        }

        public int getStype() {
            return stype;
        }

        public void setStype(int stype) {
            this.stype = stype;
        }

        public boolean isFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(boolean followStatus) {
            this.followStatus = followStatus;
        }

        public String getUicon() {
            return uicon;
        }

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public List<?> getGoodsListModels() {
            return goodsListModels;
        }

        public void setGoodsListModels(List<?> goodsListModels) {
            this.goodsListModels = goodsListModels;
        }
    }


    /**
     * gid : 101
     * gname : 摆件画
     * gisauction : 1
     * gmoney :
     * gdesc : 摆件画 适合家里摆放
     * gcid : 75
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170645-ee6ae253-edd0-4568-a0ba-4810b009c169.png
     * gnumber : 1
     * gvideo :
     * gstartingprice : 0
     * gaddprice : 5
     * gstoptime : 1545036368345
     * gtime : 1545000344959
     * gcollateral : 0
     * sid : 92
     * gauctiontime : 1545044934
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 10
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     * glatestbid : 100
     * shopInfoModel : {"sid":92,"sname":"守艺人","sfans":"","sgrade":"5.0","stime":"1544961592701","slabel":"茶文化,时尚首饰,民俗工艺","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png","sbgimg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-74554f19-d35b-4495-be51-4b196925ffa6.png","stype":18,"goodsListModels":[],"followStatus":false,"uicon":"","uname":""}
     * goodsCommentinfoModels : []
     */

   /* private int gid;
    private String gname;
    private int gisauction;
    private String gmoney;
    private String gdesc;
    private int gcid;
    private String gimg;
    private int gnumber;
    private String gvideo;
    private double gstartingprice;
    private double gaddprice;
    private long gstoptime;
    private long gtime;
    private double gcollateral;
    private String sid;
    private long gauctiontime;
    private int gissoldout;
    private String giscollectionid;
    private String gpostage;
    private int gisguarantee;
    private int gquickrefund;
    private int gGGCT;
    private int gauthentication;
    private int esid;
    private double glatestbid;//商品价格
    private ShopInfoModelBean shopInfoModel;
    private List<?> goodsCommentinfoModels;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
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

    public double getGstartingprice() {
        return gstartingprice;
    }

    public void setGstartingprice(double gstartingprice) {
        this.gstartingprice = gstartingprice;
    }

    public double getGaddprice() {
        return gaddprice;
    }

    public void setGaddprice(double gaddprice) {
        this.gaddprice = gaddprice;
    }

    public long getGstoptime() {
        return gstoptime;
    }

    public void setGstoptime(long gstoptime) {
        this.gstoptime = gstoptime;
    }

    public long getGtime() {
        return gtime;
    }

    public void setGtime(long gtime) {
        this.gtime = gtime;
    }

    public double getGcollateral() {
        return gcollateral;
    }

    public void setGcollateral(double gcollateral) {
        this.gcollateral = gcollateral;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getGauctiontime() {
        return gauctiontime;
    }

    public void setGauctiontime(long gauctiontime) {
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

    public double getGlatestbid() {
        return glatestbid;
    }

    public void setGlatestbid(double glatestbid) {
        this.glatestbid = glatestbid;
    }

    public ShopInfoModelBean getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(ShopInfoModelBean shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public List<?> getGoodsCommentinfoModels() {
        return goodsCommentinfoModels;
    }

    public void setGoodsCommentinfoModels(List<?> goodsCommentinfoModels) {
        this.goodsCommentinfoModels = goodsCommentinfoModels;
    }

    public static class ShopInfoModelBean {
        *//**
     * sid : 92
     * sname : 守艺人
     * sfans :
     * sgrade : 5.0
     * stime : 1544961592701
     * slabel : 茶文化,时尚首饰,民俗工艺
     * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png
     * sbgimg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-74554f19-d35b-4495-be51-4b196925ffa6.png
     * stype : 18
     * goodsListModels : []
     * followStatus : false
     * uicon :
     * uname :
     *//*

        private int sid;
        private String sname;
        private String sfans;
        private String sgrade;
        private String stime;
        private String slabel;
        private String simg;
        private String sbgimg;
        private int stype;
        private boolean followStatus;
        private String uicon;
        private String uname;
        private List<?> goodsListModels;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSfans() {
            return sfans;
        }

        public void setSfans(String sfans) {
            this.sfans = sfans;
        }

        public String getSgrade() {
            return sgrade;
        }

        public void setSgrade(String sgrade) {
            this.sgrade = sgrade;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getSlabel() {
            return slabel;
        }

        public void setSlabel(String slabel) {
            this.slabel = slabel;
        }

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public String getSbgimg() {
            return sbgimg;
        }

        public void setSbgimg(String sbgimg) {
            this.sbgimg = sbgimg;
        }

        public int getStype() {
            return stype;
        }

        public void setStype(int stype) {
            this.stype = stype;
        }

        public boolean isFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(boolean followStatus) {
            this.followStatus = followStatus;
        }

        public String getUicon() {
            return uicon;
        }

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public List<?> getGoodsListModels() {
            return goodsListModels;
        }

        public void setGoodsListModels(List<?> goodsListModels) {
            this.goodsListModels = goodsListModels;
        }
    }*/
}
