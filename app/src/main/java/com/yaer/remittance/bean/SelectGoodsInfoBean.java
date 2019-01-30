package com.yaer.remittance.bean;

import java.io.Serializable;
import java.util.List;

public class SelectGoodsInfoBean implements Serializable{
    /**
     * gid : 112
     * gname : 2222323232
     * gisauction : 0
     * gmoney : 1
     * gdesc : Qweqwewqeqweqweqweqwe
     * gcid : 61
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812220002271510f2b4193-3790-4885-9479-c6a4df68e170.jpg
     * gnumber : 1111
     * gvideo :
     * gstartingprice :
     * gaddprice :
     * gstoptime :
     * gtime : 1545408140546
     * gcollateral :
     * sid : 86
     * gauctiontime :
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 1.1
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     * glatestbid : 0.00
     * shopInfoModel : {"sid":86,"sname":"茶香酒道","sfans":"","sgrade":"5.0","stime":"1545324200195","slabel":"天然玉石,文房雅玩","simg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018122100431944022a4a189-0265-4731-a867-036e1f886db8.jpg","sbgimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181221004319841ee5baf87-0bb7-47ce-9d8e-e8de3b881a6d.jpg","stype":17,"goodsListModels":[],"followStatus":false,"uicon":"","uname":""}
     * goodsCommentinfoModels : [{"ucid":3,"ucdesc":"不错不错完美","uid":92,"ucstars":"5","gid":112,"gtime":"1545469295004","uname":"睡觉的壶","uicon":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192228-38e7aef0-bdbb-4776-b77a-e4e9e537a81f.png"},{"ucid":2,"ucdesc":"月底完美盛典安全啊","uid":92,"ucstars":"5","gid":112,"gtime":"1545468791237","uname":"睡觉的壶","uicon":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192228-38e7aef0-bdbb-4776-b77a-e4e9e537a81f.png"}]
     * followStatus : false
     */

    private String gid;
    private String gname;
    private int gisauction;
    private double gmoney;
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
    private int gpostage;
    private int gisguarantee;
    private int gquickrefund;
    private int gGGCT;
    private int gauthentication;
    private int esid;
    private String glatestbid;
    private ShopInfoModelBean shopInfoModel;
    private boolean followStatus;
    private List<GoodsCommentinfoModelsBean> goodsCommentinfoModels;

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

    public int getGpostage() {
        return gpostage;
    }

    public void setGpostage(int gpostage) {
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

    public List<GoodsCommentinfoModelsBean> getGoodsCommentinfoModels() {
        return goodsCommentinfoModels;
    }

    public void setGoodsCommentinfoModels(List<GoodsCommentinfoModelsBean> goodsCommentinfoModels) {
        this.goodsCommentinfoModels = goodsCommentinfoModels;
    }

    public static class ShopInfoModelBean implements Serializable{
        /**
         * sid : 86
         * sname : 茶香酒道
         * sfans :
         * sgrade : 5.0
         * stime : 1545324200195
         * slabel : 天然玉石,文房雅玩
         * simg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018122100431944022a4a189-0265-4731-a867-036e1f886db8.jpg
         * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181221004319841ee5baf87-0bb7-47ce-9d8e-e8de3b881a6d.jpg
         * stype : 17
         * goodsListModels : []
         * followStatus : false
         * uicon :
         * uname :
         */

        private String sid;
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

    public static class GoodsCommentinfoModelsBean implements Serializable {
        /**
         * ucid : 3
         * ucdesc : 不错不错完美
         * uid : 92
         * ucstars : 5
         * gid : 112
         * gtime : 1545469295004
         * uname : 睡觉的壶
         * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192228-38e7aef0-bdbb-4776-b77a-e4e9e537a81f.png
         */

        private int ucid;
        private String ucdesc;
        private int uid;
        private String ucstars;
        private int gid;
        private String gtime;
        private String uname;
        private String uicon;

        public int getUcid() {
            return ucid;
        }

        public void setUcid(int ucid) {
            this.ucid = ucid;
        }

        public String getUcdesc() {
            return ucdesc;
        }

        public void setUcdesc(String ucdesc) {
            this.ucdesc = ucdesc;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUcstars() {
            return ucstars;
        }

        public void setUcstars(String ucstars) {
            this.ucstars = ucstars;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUicon() {
            return uicon;
        }

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }
    }
}
