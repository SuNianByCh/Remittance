package com.yaer.remittance.bean;

import java.util.List;

/**
 * 优店实体类
 */
public class GoodshopBean {
    /**
     * sid : 49
     * sname : 茶香酒道
     * sfans :
     * sgrade : 5.0
     * stime : 1544722453088
     * slabel : 珠串雅玩,文房雅玩
     * simg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181214013412397452dca5c-8d65-4710-9893-fb20ff4070d6.jpg
     * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018121401341272872f24f24-54f1-4ca7-a0c0-5b8cfcd53f76.jpg
     * stype : 17
     * goodsListModels : [{"gid":86,"gname":"测试商品","gisauction":0,"gmoney":"1.0","gdesc":"这个是简介 这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介","gcid":47,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181214222146497a609504a-fd86-44e1-b805-ce7cbc16c192.jpg","gnumber":111,"gvideo":"","gstartingprice":"","gaddprice":"","gstoptime":"","gtime":"1544797307618","gcollateral":"","sid":"49","gauctiontime":"","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0}]
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
    private String stype;
    private boolean followStatus;
    private String uicon;
    private String uname;
    private List<GoodsListModelsBean> goodsListModels;

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

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
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

    public List<GoodsListModelsBean> getGoodsListModels() {
        return goodsListModels;
    }

    public void setGoodsListModels(List<GoodsListModelsBean> goodsListModels) {
        this.goodsListModels = goodsListModels;
    }

    public static class GoodsListModelsBean {
        /**
         * gid : 86
         * gname : 测试商品
         * gisauction : 0
         * gmoney : 1.0
         * gdesc : 这个是简介 这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介这个是简介
         * gcid : 47
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181214222146497a609504a-fd86-44e1-b805-ce7cbc16c192.jpg
         * gnumber : 111
         * gvideo :
         * gstartingprice :
         * gaddprice :
         * gstoptime :
         * gtime : 1544797307618
         * gcollateral :
         * sid : 49
         * gauctiontime :
         * gissoldout : 0
         * giscollectionid :
         * gpostage : 1
         * gisguarantee : 0
         * gquickrefund : 0
         * gGGCT : 0
         * gauthentication : 0
         * esid : 0
         */

        private String gid;
        private String gname;
        private String gisauction;
        private String gmoney;
        private String gdesc;
        private String gcid;
        private String gimg;
        private String gnumber;
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
        private String gisguarantee;
        private String gquickrefund;
        private String gGGCT;
        private String gauthentication;
        private String esid;
        private String ghot;

        public String getgGGCT() {
            return gGGCT;
        }

        public void setgGGCT(String gGGCT) {
            this.gGGCT = gGGCT;
        }

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

        public String getGGGCT() {
            return gGGCT;
        }

        public void setGGGCT(String gGGCT) {
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
    }

    /*  *//**
     * sid : 18
     * sname : 1
     * sfans : 1213
     * sgrade :
     * stime : 2
     * slabel : 饿啊阿达
     * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/banner/banner3.png
     * stype : 3
     * goodsListModels : [{"gid":"54","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847399_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847395_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847398_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847401_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847394_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847393_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847388_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847396_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847390_2f62e1e519a2d8043caf0bf6288910c9.jpg","gname":"雷诺塔 i7 8700/GTX1060吃鸡游戏主机组装电脑组装机DIY台式电脑组装机主机","gmoney":"6300","gnumber":"0","uname":"","gtime":"1543487894722"},{"gid":"52","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241033-4d778210-d1f5-4a53-b541-8681c89c59cf.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241033-676728f3-9fbf-4dc6-b951-b4e5a5cbf892.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241033-1986a3b8-497e-4d88-89d2-4a0b8892dec4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241033-5944bd99-dec7-4192-8173-0ceb533f471a.png","gname":"鸡血玉","gmoney":"10000","gnumber":"0","uname":"Frank","gtime":"1543026806532"},{"gid":"51","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-c18963fb-4451-463c-9759-10560b960185.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-da330ee7-f5b3-4331-b703-b6ce8004afa8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-e4cf149a-d97e-4df3-afb7-8f40ad1cc5cf.png","gname":"热手石","gmoney":"1000","gnumber":"0","uname":"Frank","gtime":"1543026690310"},{"gid":"44","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222331-5d779b54-deb7-4041-aecf-7dcc95e745b1.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222331-e5109bb9-3f9f-4816-8937-8e916dedfca7.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222331-b5045ec4-2fb5-451e-8f08-69ae95f6cd14.png","gname":"秋水芙蓉","gmoney":"159","gnumber":"0","uname":"Frank","gtime":"1542900703526"}]
     * followStatus : false
     * uicon : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKXuqtzrWCKCDEFrqn60icFPCfxTOzJeslLianibvh8Cjt1ZYneicG40s1YvGEkn83FRPMTGP7jtQics2g/132
     * uname : Frank
     *//*

    private int sid;
    private String sname;
    private String sfans;
    private String sgrade;
    private String stime;
    private String slabel;
    private String simg;
    private int stype;
    private boolean followStatus;
    private String uicon;
    private String uname;
    private List<GoodsListModelsBean> goodsListModels;

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

    public List<GoodsListModelsBean> getGoodsListModels() {
        return goodsListModels;
    }

    public void setGoodsListModels(List<GoodsListModelsBean> goodsListModels) {
        this.goodsListModels = goodsListModels;
    }

    public static class GoodsListModelsBean {
        *//**
     * gid : 54
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847399_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847395_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847398_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847401_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847394_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847393_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847388_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847396_2f62e1e519a2d8043caf0bf6288910c9.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/test/1543487847390_2f62e1e519a2d8043caf0bf6288910c9.jpg
     * gname : 雷诺塔 i7 8700/GTX1060吃鸡游戏主机组装电脑组装机DIY台式电脑组装机主机
     * gmoney : 6300
     * gnumber : 0
     * uname :
     * gtime : 1543487894722
     *//*

        private String gid;
        private String gimg;
        private String gname;
        private String gmoney;
        private String gnumber;
        private String uname;
        private String gtime;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGmoney() {
            return gmoney;
        }

        public void setGmoney(String gmoney) {
            this.gmoney = gmoney;
        }

        public String getGnumber() {
            return gnumber;
        }

        public void setGnumber(String gnumber) {
            this.gnumber = gnumber;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
        }
    }*/
}
