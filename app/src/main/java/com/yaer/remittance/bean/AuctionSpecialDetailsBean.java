package com.yaer.remittance.bean;

public class AuctionSpecialDetailsBean {


    /**
     * sgid : 1
     * seid : 6
     * gid : 86
     * gtime : 1545967595091
     * sgstatus : 3
     * goodsInfoModel : {"gid":150,"gname":"不许哦随机打劫","gisauction":1,"gmoney":"","gdesc":"不想好的好的","gcid":77,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-9c613e15-727b-4260-82a2-2c4f0903ec9a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-50669291-1fd2-4848-b1bf-491675b16be5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-af1d2418-53a2-49b1-bcb5-e71166d71baa.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-ffbed19d-f722-4213-882c-82d25745e556.png","gnumber":1,"gvideo":"","gstartingprice":"0","gaddprice":"10","gstoptime":"1546488540000","gtime":"1546491732474","gcollateral":"0","sid":"92","gauctiontime":"1546402140000","gissoldout":0,"giscollectionid":"","gpostage":"10","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0}
     */

    private int sgid;
    private int seid;
    private int gid;
    private String gtime;
    private int sgstatus;
    private GoodsInfoModelBean goodsInfoModel;

    public int getSgid() {
        return sgid;
    }

    public void setSgid(int sgid) {
        this.sgid = sgid;
    }

    public int getSeid() {
        return seid;
    }

    public void setSeid(int seid) {
        this.seid = seid;
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

    public int getSgstatus() {
        return sgstatus;
    }

    public void setSgstatus(int sgstatus) {
        this.sgstatus = sgstatus;
    }

    public GoodsInfoModelBean getGoodsInfoModel() {
        return goodsInfoModel;
    }

    public void setGoodsInfoModel(GoodsInfoModelBean goodsInfoModel) {
        this.goodsInfoModel = goodsInfoModel;
    }

    public static class GoodsInfoModelBean {
        /**
         * gid : 150
         * gname : 不许哦随机打劫
         * gisauction : 1
         * gmoney :
         * gdesc : 不想好的好的
         * gcid : 77
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-9c613e15-727b-4260-82a2-2c4f0903ec9a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-50669291-1fd2-4848-b1bf-491675b16be5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-af1d2418-53a2-49b1-bcb5-e71166d71baa.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031209-ffbed19d-f722-4213-882c-82d25745e556.png
         * gnumber : 1
         * gvideo :
         * gstartingprice : 0
         * gaddprice : 10
         * gstoptime : 1546488540000
         * gtime : 1546491732474
         * gcollateral : 0
         * sid : 92
         * gauctiontime : 1546402140000
         * gissoldout : 0
         * giscollectionid :
         * gpostage : 10
         * gisguarantee : 0
         * gquickrefund : 0
         * gGGCT : 0
         * gauthentication : 0
         * esid : 0
         * glatestbid : 0.00
         * followStatus : false
         * gstate : 0
         */

        private int gid;
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
        private int gGGCT;
        private int gauthentication;
        private int esid;
        private String glatestbid;
        private boolean followStatus;
        private int gstate;

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

        public String getGlatestbid() {
            return glatestbid;
        }

        public void setGlatestbid(String glatestbid) {
            this.glatestbid = glatestbid;
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
    }
}
