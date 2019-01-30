package com.yaer.remittance.bean;

import java.util.List;

public class GetMainBean {
    private List<ShopInfoModelBean> shopInfoModel;//店铺信息
    private List<RecommendgoodsBean> recommendgoods;//拍品推荐
    private List<ChoicegoodsBean> choicegoods;//为你优选

    public List<ShopInfoModelBean> getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(List<ShopInfoModelBean> shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public List<RecommendgoodsBean> getRecommendgoods() {
        return recommendgoods;
    }

    public void setRecommendgoods(List<RecommendgoodsBean> recommendgoods) {
        this.recommendgoods = recommendgoods;
    }

    public List<ChoicegoodsBean> getChoicegoods() {
        return choicegoods;
    }

    public void setChoicegoods(List<ChoicegoodsBean> choicegoods) {
        this.choicegoods = choicegoods;
    }

    public static class ShopInfoModelBean {
        /**
         * sid : 107
         * sname : 雍和堂
         * sfans :
         * sgrade : 5.0
         * stime : 1547206560166
         * slabel : 金石篆刻,文房雅玩,民俗工艺
         * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111935-c00dd4e0-8e14-4e40-bc50-8c0e52a74656.png
         * sbgimg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111935-fbe1a880-d752-4831-8bb7-43ddbd9ce94d.png
         * stype : 33
         * goodsListModels : [{"gid":295,"gname":"拍品哈哈哈哈哈","gisauction":1,"gmoney":"1.11","gdesc":"不知发哦不知道不知道","gcid":163,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-2d409052-4655-48c0-b0c1-558a97511922.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-c4688955-2cdc-48d2-bba1-841668212682.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-8457d6ac-026f-48a8-a2e3-08e5c741c7e3.png","gnumber":1,"gvideo":"","gstartingprice":"0","gaddprice":"100","gstoptime":"1548040080000","gtime":"1547874424148","gcollateral":"1","sid":"107","gauctiontime":"1547780520000","gissoldout":0,"giscollectionid":"","gpostage":"10","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":30},{"gid":297,"gname":"蒲公英","gisauction":1,"gmoney":"1.00","gdesc":"蒲公英","gcid":163,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20190119232328315b3857806-3099-432f-a30c-d068327ce9b4.jpg","gnumber":1,"gvideo":"","gstartingprice":"60","gaddprice":"50","gstoptime":"1548170520000","gtime":"1547911412071","gcollateral":"","sid":"107","gauctiontime":"1547911320000","gissoldout":0,"giscollectionid":"","gpostage":"20","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"158","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":60},{"gid":298,"gname":"拍品吃完还","gisauction":1,"gmoney":"1.00","gdesc":"那些好的好的好","gcid":162,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200106-f04a24a1-c127-40ae-b1e5-0decfef3fc2f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200106-93bdafe3-3bb4-4d47-9874-b0a0e685d595.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200106-f79bf9f3-51a6-40de-9509-6831425fd01d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200106-ad8349a6-6300-41a2-91e5-541c1be712fb.png","gnumber":1,"gvideo":"","gstartingprice":"100","gaddprice":"10","gstoptime":"1548169500000","gtime":"1547917565353","gcollateral":"1","sid":"107","gauctiontime":"1547910300000","gissoldout":0,"giscollectionid":"","gpostage":"20","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"10.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":0},{"gid":299,"gname":"呃呃呃非常到底","gisauction":1,"gmoney":"1.00","gdesc":"发的等等我","gcid":162,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200159-b7f3b597-ef6e-45af-8942-bdb452a1485f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200159-01fa4b79-6c86-4de8-ab58-0c17dd42968a.png","gnumber":1,"gvideo":"","gstartingprice":"123456789","gaddprice":"12345678910","gstoptime":"1548259140000","gtime":"1547920765328","gcollateral":"1","sid":"107","gauctiontime":"1547913540000","gissoldout":0,"giscollectionid":"","gpostage":"123456","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":30},{"gid":300,"gname":"拍品测试数据测试吞吞吐吐","gisauction":1,"gmoney":"100","gdesc":"好的好的好地位姐姐快点快点觉得觉得好好的婚纱十八大你发金额就就到家多喝点还减肥几点几","gcid":161,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200330-0fc4ca01-3812-4614-b22d-79f58dfa7a56.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200330-a999bd9d-a345-4128-8bfa-cfd999054a15.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200330-fa989cb0-143e-41af-92ac-3e7ce35af3b4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200330-ceef18fe-c61c-4afd-a734-cf0b5d11d4bf.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901200330-8e3036d6-f481-4bc1-aa7f-987e355a2c10.png","gnumber":0,"gvideo":"","gstartingprice":"0","gaddprice":"50","gstoptime":"1548163800000","gtime":"1547926241635","gcollateral":"1","sid":"107","gauctiontime":"1547904600000","gissoldout":0,"giscollectionid":"","gpostage":"20","gisguarantee":1,"gquickrefund":1,"gGGCT":0,"gauthentication":1,"esid":0,"glatestbid":"200","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":1140},{"gid":301,"gname":"分已付款i私爱就是觉得忽然好接电话","gisauction":1,"gmoney":"100","gdesc":"并不想好的好的好还说啥","gcid":161,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201141-6ff02b47-1437-489a-8fb8-2289357aa24e.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201141-eb445241-ebdc-481a-bc1e-dbb5881b8301.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201141-b70b6add-d222-4638-a3b3-8b4a1ba4f899.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201141-31c46371-f20d-4b30-a027-a60ab444c4a4.png","gnumber":1,"gvideo":"","gstartingprice":"10","gaddprice":"10","gstoptime":"1548308460000","gtime":"1547955686257","gcollateral":"1","sid":"107","gauctiontime":"1547962860000","gissoldout":0,"giscollectionid":"","gpostage":"10","gisguarantee":1,"gquickrefund":1,"gGGCT":0,"gauthentication":1,"esid":0,"glatestbid":"100","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":30},{"gid":302,"gname":"你知道回复v哦去哦哦去","gisauction":1,"gmoney":"100","gdesc":"很喜欢的好多钱哦速度胡","gcid":159,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201142-b6ef8237-68c3-46d5-94bb-41880b1bc6ed.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201142-483c52cd-3f7a-4074-afcf-345709d72177.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201142-1e7efa91-e93f-4567-b378-78eedf8baaf7.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201142-ee390379-0408-4ae6-bafd-9a04676dabbe.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201142-c0e25a9f-d390-420e-a3a1-baec711ac8f5.png","gnumber":1,"gvideo":"","gstartingprice":"10","gaddprice":"10","gstoptime":"1548391320000","gtime":"1547955743532","gcollateral":"2000","sid":"107","gauctiontime":"1547962920000","gissoldout":0,"giscollectionid":"","gpostage":"10","gisguarantee":1,"gquickrefund":1,"gGGCT":0,"gauthentication":1,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":30},{"gid":303,"gname":"就到家都觉得几真不错吧啦脾气","gisauction":1,"gmoney":"100","gdesc":"好的好的蝴蝶姐姐","gcid":165,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201144-cb3647aa-8d2f-42b3-8e2e-dd9ecb910fcc.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201144-683309ee-270d-46b7-8888-c27c30370747.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201144-74e9e9ef-9a89-439a-b4fa-cf7c2338d97f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201144-04485e53-cec0-4984-84e0-d859ad91cd14.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201144-e97fa486-e710-4403-a231-178e2f6d309b.png","gnumber":1,"gvideo":"","gstartingprice":"100","gaddprice":"100","gstoptime":"1548308640000","gtime":"1547955874922","gcollateral":"2000","sid":"107","gauctiontime":"1547963040000","gissoldout":0,"giscollectionid":"","gpostage":"10","gisguarantee":1,"gquickrefund":1,"gGGCT":0,"gauthentication":1,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":0},{"gid":304,"gname":"啦陆地饿哦青浦额u如","gisauction":1,"gmoney":"100","gdesc":"就是觉得回电话哦去哦额u五","gcid":162,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-1a79dd05-6da8-4b9a-a463-81d4af382585.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-e901e772-d5e5-489c-836b-af6f71a7eea6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-729fb710-800e-453f-a89f-ad7f217a92a7.png","gnumber":1,"gvideo":"","gstartingprice":"10","gaddprice":"100","gstoptime":"1548308760000","gtime":"1547955981152","gcollateral":"2000","sid":"107","gauctiontime":"1547963160000","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":1,"gquickrefund":1,"gGGCT":0,"gauthentication":1,"esid":0,"glatestbid":"0.00","followStatus":false,"gstate":0,"biddingnumber":0,"ghot":120}]
         * followStatus : false
         * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111410-73abbcab-414b-4de5-88ec-0e3653bc750d.png
         * uname : 粒粒皆辛苦
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

        public List<GoodsListModelsBean> getGoodsListModels() {
            return goodsListModels;
        }

        public void setGoodsListModels(List<GoodsListModelsBean> goodsListModels) {
            this.goodsListModels = goodsListModels;
        }

        public static class GoodsListModelsBean {
            /**
             * gid : 295
             * gname : 拍品哈哈哈哈哈
             * gisauction : 1
             * gmoney : 1.11
             * gdesc : 不知发哦不知道不知道
             * gcid : 163
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-2d409052-4655-48c0-b0c1-558a97511922.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-c4688955-2cdc-48d2-bba1-841668212682.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901191307-8457d6ac-026f-48a8-a2e3-08e5c741c7e3.png
             * gnumber : 1
             * gvideo :
             * gstartingprice : 0
             * gaddprice : 100
             * gstoptime : 1548040080000
             * gtime : 1547874424148
             * gcollateral : 1
             * sid : 107
             * gauctiontime : 1547780520000
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
             * biddingnumber : 0
             * ghot : 30
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
            private int biddingnumber;
            private int ghot;

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

            public int getBiddingnumber() {
                return biddingnumber;
            }

            public void setBiddingnumber(int biddingnumber) {
                this.biddingnumber = biddingnumber;
            }

            public int getGhot() {
                return ghot;
            }

            public void setGhot(int ghot) {
                this.ghot = ghot;
            }
        }
    }

    public static class RecommendgoodsBean {
        /**
         * gid : 304
         * gname : 啦陆地饿哦青浦额u如
         * gisauction : 1
         * gmoney : 100
         * gdesc : 就是觉得回电话哦去哦额u五
         * gcid : 162
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-1a79dd05-6da8-4b9a-a463-81d4af382585.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-e901e772-d5e5-489c-836b-af6f71a7eea6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-729fb710-800e-453f-a89f-ad7f217a92a7.png
         * gnumber : 1
         * gvideo :
         * gstartingprice : 10
         * gaddprice : 100
         * gstoptime : 1548308760000
         * gtime : 1547955981152
         * gcollateral : 2000
         * sid : 107
         * gauctiontime : 1547963160000
         * gissoldout : 0
         * giscollectionid :
         * gpostage : 1
         * gisguarantee : 1
         * gquickrefund : 1
         * gGGCT : 0
         * gauthentication : 1
         * esid : 0
         * glatestbid : 0.00
         * followStatus : false
         * gstate : 0
         * biddingnumber : 0
         * ghot : 120
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
        private int biddingnumber;
        private int ghot;

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

        public int getBiddingnumber() {
            return biddingnumber;
        }

        public void setBiddingnumber(int biddingnumber) {
            this.biddingnumber = biddingnumber;
        }

        public int getGhot() {
            return ghot;
        }

        public void setGhot(int ghot) {
            this.ghot = ghot;
        }
    }

    public static class ChoicegoodsBean {
        /**
         * gid : 304
         * gname : 啦陆地饿哦青浦额u如
         * gisauction : 1
         * gmoney : 100
         * gdesc : 就是觉得回电话哦去哦额u五
         * gcid : 162
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-1a79dd05-6da8-4b9a-a463-81d4af382585.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-e901e772-d5e5-489c-836b-af6f71a7eea6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-729fb710-800e-453f-a89f-ad7f217a92a7.png
         * gnumber : 1
         * gvideo :
         * gstartingprice : 10
         * gaddprice : 100
         * gstoptime : 1548308760000
         * gtime : 1547955981152
         * gcollateral : 2000
         * sid : 107
         * gauctiontime : 1547963160000
         * gissoldout : 0
         * giscollectionid :
         * gpostage : 1
         * gisguarantee : 1
         * gquickrefund : 1
         * gGGCT : 0
         * gauthentication : 1
         * esid : 0
         * glatestbid : 0.00
         * followStatus : false
         * gstate : 0
         * biddingnumber : 0
         * ghot : 120
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
        private int biddingnumber;
        private int ghot;

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

        public int getBiddingnumber() {
            return biddingnumber;
        }

        public void setBiddingnumber(int biddingnumber) {
            this.biddingnumber = biddingnumber;
        }

        public int getGhot() {
            return ghot;
        }

        public void setGhot(int ghot) {
            this.ghot = ghot;
        }
    }


   /* private List<ShopInfoModelBean> shopInfoModel;
    private List<PaigoodsBean> paigoods;
    private List<GoodsBean> goods;
    private List<OpaigoodsBean> opaigoods;

    public List<ShopInfoModelBean> getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(List<ShopInfoModelBean> shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public List<PaigoodsBean> getPaigoods() {
        return paigoods;
    }

    public void setPaigoods(List<PaigoodsBean> paigoods) {
        this.paigoods = paigoods;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<OpaigoodsBean> getOpaigoods() {
        return opaigoods;
    }

    public void setOpaigoods(List<OpaigoodsBean> opaigoods) {
        this.opaigoods = opaigoods;
    }

    public static class ShopInfoModelBean {
        *//**
         * sid : 86
         * sname : 茶香酒道
         * sfans :
         * sgrade : 5.0
         * stime : 1545324200195
         * slabel : 天然玉石,文房雅玩
         * simg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018122100431944022a4a189-0265-4731-a867-036e1f886db8.jpg
         * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181221004319841ee5baf87-0bb7-47ce-9d8e-e8de3b881a6d.jpg
         * stype : 17
         * goodsListModels : [{"gid":113,"gname":"1","gisauction":1,"gmoney":"","gdesc":"1","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812220024446981673f9aa-84fa-4f20-a1c3-31698ef2ded5.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181222002445014e89fa1c4-65ef-4fd1-96fc-d07dd8c561d4.jpg","gnumber":1,"gvideo":"","gstartingprice":"1","gaddprice":"1","gstoptime":"1545668673","gtime":"1545409477057","gcollateral":"","sid":"86","gauctiontime":"1545409473","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"0.00","followStatus":false},{"gid":114,"gname":"Jjjjj","gisauction":1,"gmoney":"","gdesc":"Sxxxxx","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181222004524981702e6386-5e48-434f-a5bf-394a4dfce46f.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181222004525341127527ed-50a5-4dfa-8909-bb015993ea70.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181222004525484778b8fca-9fe9-4018-8bff-26821dc9730d.jpg","gnumber":1,"gvideo":"","gstartingprice":"1","gaddprice":"1","gstoptime":"1545669914","gtime":"1545410717530","gcollateral":"","sid":"86","gauctiontime":"1545410714","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"2.00","followStatus":false},{"gid":115,"gname":"Aksjksdjfsdfsdf","gisauction":1,"gmoney":"","gdesc":"Sadfasdfasfsd","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812220046403597d55df4a-cb2f-4726-9b7d-dc505a71fcae.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018122200464092035f539a6-5640-4b90-8324-77345724e5dc.jpg","gnumber":1,"gvideo":"","gstartingprice":"12","gaddprice":"12","gstoptime":"1545842787","gtime":"1545410793719","gcollateral":"","sid":"86","gauctiontime":"1545583587","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"228.00","followStatus":false},{"gid":116,"gname":"Werh","gisauction":1,"gmoney":"","gdesc":"Hhgfcfghjkjhh","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181223004426416e5c4db92-83ef-4d35-a1c9-021beb90a321.jpg","gnumber":1,"gvideo":"","gstartingprice":"1","gaddprice":"1","gstoptime":"1545756255","gtime":"1545497066992","gcollateral":"","sid":"86","gauctiontime":"1545497055","gissoldout":0,"giscollectionid":"","gpostage":"12","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"11.00","followStatus":false},{"gid":117,"gname":"Ce shi. Pai pin","gisauction":1,"gmoney":"","gdesc":"Rrrrrr","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018122301174638181b0a6fd-d860-4f36-904e-05c3591b2cfa.jpg","gnumber":1,"gvideo":"","gstartingprice":"1","gaddprice":"1","gstoptime":"1545758254","gtime":"1545499066875","gcollateral":"","sid":"86","gauctiontime":"1545499054","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"0.00","followStatus":false},{"gid":118,"gname":"111","gisauction":1,"gmoney":"","gdesc":"111","gcid":54,"gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812230122510175aa1aa29-67e8-40c0-8db9-1a8c99f24eb6.jpg","gnumber":1,"gvideo":"","gstartingprice":"1","gaddprice":"1","gstoptime":"1545758563000","gtime":"1545499371465","gcollateral":"","sid":"86","gauctiontime":"1545499363000","gissoldout":0,"giscollectionid":"","gpostage":"1","gisguarantee":0,"gquickrefund":0,"gGGCT":0,"gauthentication":0,"esid":0,"glatestbid":"44.00","followStatus":false}]
         * followStatus : false
         * uicon :
         * uname :
         *//*

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
             * gid : 113
             * gname : 1
             * gisauction : 1
             * gmoney :
             * gdesc : 1
             * gcid : 54
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812220024446981673f9aa-84fa-4f20-a1c3-31698ef2ded5.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181222002445014e89fa1c4-65ef-4fd1-96fc-d07dd8c561d4.jpg
             * gnumber : 1
             * gvideo :
             * gstartingprice : 1
             * gaddprice : 1
             * gstoptime : 1545668673
             * gtime : 1545409477057
             * gcollateral :
             * sid : 86
             * gauctiontime : 1545409473
             * gissoldout : 0
             * giscollectionid :
             * gpostage : 1
             * gisguarantee : 0
             * gquickrefund : 0
             * gGGCT : 0
             * gauthentication : 0
             * esid : 0
             * glatestbid : 0.00
             * followStatus : false
             * ghot:30
             *//*

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
            private int ghot;

            public int getgGGCT() {
                return gGGCT;
            }

            public void setgGGCT(int gGGCT) {
                this.gGGCT = gGGCT;
            }

            public int getGhot() {
                return ghot;
            }

            public void setGhot(int ghot) {
                this.ghot = ghot;
            }

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
        }
    }

    public static class PaigoodsBean {
        *//**
         * gid : 118
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812230122510175aa1aa29-67e8-40c0-8db9-1a8c99f24eb6.jpg
         * gname : 111
         * gmoney :
         * gnumber : 1
         * uname :
         * uicon :
         * gtime : 1545499371465
         * glatestbid : 44.00
         * followStatus : false
         *//*

        private String gid;
        private String gimg;
        private String gname;
        private String gmoney;
        private int gnumber;
        private String uname;
        private String uicon;
        private String gtime;
        private String glatestbid;
        private boolean followStatus;

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

        public int getGnumber() {
            return gnumber;
        }

        public void setGnumber(int gnumber) {
            this.gnumber = gnumber;
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

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
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
    }

    public static class GoodsBean {
        *//**
         * gid : 120
         * gname : 好的好的好
         * gisauction : 1
         * gmoney :
         * gdesc : 还真是
         * gcid : 117
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812250922-2459be88-e9d4-40b6-b64c-28dc5e5ab478.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812250922-850b1c6c-11b0-4a20-866f-128c1461d2c6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812250922-97095752-77f9-4b3f-a257-5547d0ba8ac0.png
         * gnumber : 1
         * gvideo :
         * gstartingprice : 200
         * gaddprice : 10
         * gstoptime : 1545722460000
         * gtime : 1545700929580
         * gcollateral : 0
         * sid : 94
         * gauctiontime : 1545636060000
         * gissoldout : 1
         * giscollectionid :
         * gpostage : 10
         * gisguarantee : 0
         * gquickrefund : 0
         * gGGCT : 0
         * gauthentication : 0
         * esid : 0
         * glatestbid : 40.00
         * followStatus : false
         * gstate : 2
         *//*

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

        *//**
         * gid : 112
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812220002271510f2b4193-3790-4885-9479-c6a4df68e170.jpg
         * gname : 2222323232
         * gmoney : 1
         * gnumber : 1087
         * uname :
         * uicon :
         * gtime : 1545408140546
         * glatestbid : 0.00
         * followStatus : false
         *//*

       *//* private String gid;
        private String gimg;
        private String gname;
        private String gmoney;
        private int gnumber;
        private String uname;
        private String uicon;
        private String gtime;
        private String glatestbid;
        private boolean followStatus;

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

        public int getGnumber() {
            return gnumber;
        }

        public void setGnumber(int gnumber) {
            this.gnumber = gnumber;
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

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
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
        }*//*
    }

    public static class OpaigoodsBean {
        *//**
         * gid : 111
         * gname : 贵州茅台酒
         * gisauction : 1
         * gmoney :
         * gdesc : 贵州茅台酒
         * gcid : 54
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820986_229.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820985_69.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820983_865.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820987_677.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820980_701.jpg
         * gnumber : 1
         * gvideo :
         * gstartingprice : 0
         * gaddprice : 2000
         * gstoptime : 1545494400000
         * gtime : 1545312825843
         * gcollateral : 0
         * sid : 89
         * gauctiontime : 1545235200000
         * gissoldout : 0
         * giscollectionid :
         * gpostage : 200
         * gisguarantee : 0
         * gquickrefund : 0
         * gGGCT : 0
         * gauthentication : 0
         * esid : 0
         * glatestbid : 1
         * followStatus : false
         * ghot
         *//*

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
        private int ghot;

        public int getgGGCT() {
            return gGGCT;
        }

        public void setgGGCT(int gGGCT) {
            this.gGGCT = gGGCT;
        }

        public int getGhot() {
            return ghot;
        }

        public void setGhot(int ghot) {
            this.ghot = ghot;
        }

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
    }*/


  /*  private List<ShopInfoModelBean> shopInfoModel;
    private List<PaigoodsBean> paigoods;
    private List<GoodsBean> goods;
    private List<OpaigoodsBean> opaigoods;

    public List<ShopInfoModelBean> getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(List<ShopInfoModelBean> shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public List<PaigoodsBean> getPaigoods() {
        return paigoods;
    }

    public void setPaigoods(List<PaigoodsBean> paigoods) {
        this.paigoods = paigoods;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<OpaigoodsBean> getOpaigoods() {
        return opaigoods;
    }

    public void setOpaigoods(List<OpaigoodsBean> opaigoods) {
        this.opaigoods = opaigoods;
    }

    public static class ShopInfoModelBean {
        *//**
     * sid : 49
     * sname : 茶香酒道
     * sfans :
     * sgrade : 5.0
     * stime : 1544722453088
     * slabel : 珠串雅玩,文房雅玩
     * simg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20181214013412397452dca5c-8d65-4710-9893-fb20ff4070d6.jpg
     * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/2018121401341272872f24f24-54f1-4ca7-a0c0-5b8cfcd53f76.jpg
     * stype : 17
     * goodsListModels : []
     * followStatus : false
     * uicon :
     * uname :
     *//*

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

        public List<?> getGoodsListModels() {
            return goodsListModels;
        }

        public void setGoodsListModels(List<?> goodsListModels) {
            this.goodsListModels = goodsListModels;
        }
    }

    public static class PaigoodsBean {
        *//**
     * gid : 84
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-a842c95f-f7fc-44af-bee2-3fc715613ad0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-14e9b099-b532-4728-8a85-a654e18ce59c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-2cde6952-52f9-4246-9ac6-dca0ef1e3958.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-48668612-e28b-46be-871e-a2ac803f3c76.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-35f2b5c2-fbf0-4b3a-962f-685bed0bcdd1.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-938baecf-0b72-4c7e-ae96-397d93c97ea5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-454fcbd1-6ed9-494f-ae37-c32148086d8d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-cde5caaf-94d3-4854-b677-e33d38fbf4e8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-97b30098-1854-4c95-b136-367716c3b587.png
     * gname : 图片奇葩退饿哦我去诶
     * gmoney :
     * gnumber : 1
     * uname :
     * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812140216-f341bd59-ecea-465d-bcfb-2058efe5058c.png
     * gtime : 1544774271709
     *//*

        private String gid;
        private String gimg;
        private String gname;
        private String gmoney;
        private String gnumber;
        private String uname;
        private String uicon;
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

        public String getUicon() {
            return uicon;
        }

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
        }
    }

    public static class GoodsBean {
        *//**
     * gid : 82
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-2378f1f3-bd62-426a-9a8d-1b5f4681558b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-f6a33ec6-3710-4fec-bca1-2014ea04a6b4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-03347ef5-4ecc-4e08-a20c-30f24e3a15e0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-df69ede3-93f5-4a0b-8dec-a4234fe39e6f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-13cf5513-7d66-4587-a9b4-a34eedcb9263.png
     * gname : 金惠美茶具定制
     * gmoney : 598
     * gnumber : 10
     * uname :
     * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812140216-f341bd59-ecea-465d-bcfb-2058efe5058c.png
     * gtime : 1544724344622
     *//*

        private String gid;
        private String gimg;
        private String gname;
        private String gmoney;
        private String gnumber;
        private String uname;
        private String uicon;
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

        public String getUicon() {
            return uicon;
        }

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }

        public String getGtime() {
            return gtime;
        }

        public void setGtime(String gtime) {
            this.gtime = gtime;
        }
    }

    public static class OpaigoodsBean {
        *//**
     * gid : 84
     * gname : 图片奇葩退饿哦我去诶
     * gisauction : 1
     * gmoney :
     * gdesc : 绝对一起起哦哦为你的呢地哦我
     * gcid : 48
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-a842c95f-f7fc-44af-bee2-3fc715613ad0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-14e9b099-b532-4728-8a85-a654e18ce59c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-2cde6952-52f9-4246-9ac6-dca0ef1e3958.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-48668612-e28b-46be-871e-a2ac803f3c76.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-35f2b5c2-fbf0-4b3a-962f-685bed0bcdd1.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-938baecf-0b72-4c7e-ae96-397d93c97ea5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-454fcbd1-6ed9-494f-ae37-c32148086d8d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-cde5caaf-94d3-4854-b677-e33d38fbf4e8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-97b30098-1854-4c95-b136-367716c3b587.png
     * gnumber : 1
     * gvideo :
     * gstartingprice : 0
     * gaddprice : 100
     * gstoptime : 2018年12月16日-15时57分
     * gtime : 1544774271709
     * gcollateral :
     * sid : 50
     * gauctiontime : 2018年12月14日-15时57分
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 10
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     *//*

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
    }*/


    /*private List<ShopInfoModelBean> shopInfoModel;
    private List<PaigoodsBean> paigoods;
    private List<GoodsBean> goods;
    private List<OpaigoodsBean> opaigoods;

  *//*  public static String getJsonString(GetMainBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("shopInfoModel", userBean.getShopInfoModel());
        map.put("paigoods", userBean.getPaigoods());
        map.put("goods", userBean.getGoods());
        map.put("opaigoods", userBean.getOpaigoods());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }*//*

    public List<ShopInfoModelBean> getShopInfoModel() {
        return shopInfoModel;
    }

    public void setShopInfoModel(List<ShopInfoModelBean> shopInfoModel) {
        this.shopInfoModel = shopInfoModel;
    }

    public List<PaigoodsBean> getPaigoods() {
        return paigoods;
    }

    public void setPaigoods(List<PaigoodsBean> paigoods) {
        this.paigoods = paigoods;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<OpaigoodsBean> getOpaigoods() {
        return opaigoods;
    }

    public void setOpaigoods(List<OpaigoodsBean> opaigoods) {
        this.opaigoods = opaigoods;
    }

    public static class ShopInfoModelBean {
        *//**
     * sid : 4
     * sname : 1
     * sfans : 1213
     * sgrade :
     * stime : 2
     * slabel : 饿啊阿达
     * simg : 1
     * stype : 3
     *//*

        private int sid;
        private String sname;
        private String sfans;
        private String sgrade;
        private String uicon;
        private String stime;
        private String slabel;
        private String simg;
        private int stype;

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

        public void setUicon(String uicon) {
            this.uicon = uicon;
        }

        public String getUicon() {
            return uicon;
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
    }

    public static class PaigoodsBean {
        *//**
     * gid : 50
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-c449bc26-e252-4d3a-a081-0c38e564cb96.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-12317e56-573f-4bb2-9a3a-fffd9fe92fe9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-72973288-cd44-46c6-b410-4c30d50c17f3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-1625ed24-1d64-4680-80f2-a0485dcae390.png
     * gname : 手工雕刻主人杯
     * gmoney :
     * gnumber : 0
     * uname : Frank
     * gtime : 1542990215181
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
    }

    public static class GoodsBean {
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
    }

    public static class OpaigoodsBean {
        *//**
     * gid : 38
     * gname : 龙泉宝剑 镇宅辟邪汉剑长剑古剑
     * gisauction : 1
     * gmoney :
     * gdesc : 龙泉宝剑
     * gcid : 5
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222319-046f8958-732c-4746-a09b-a4ccfd3dbeff.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222319-5ad58065-7bb9-4461-be51-7c2d3e65ae3b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222319-32199a42-9294-4c39-8c77-480f0cd1de3b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222319-27ce5f25-222a-4509-b0f8-e6e7670648cb.png
     * gvideo :
     * gstartingprice : 0
     * gaddprice : 20
     * gstoptime : 2018年12月04日-23时18分
     * gtime : 1542899936550
     * gcollateral :
     * sid : 18
     * gauctiontime : 2018年11月22日-23时18分
     * gissoldout : 0
     * giscollectionid : 0
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     *//*

        private String gid;
        private String gname;
        private String gisauction;
        private String gmoney;
        private String gdesc;
        private String gcid;
        private String gimg;
        private String gvideo;
        private String gstartingprice;
        private String gaddprice;
        private String gstoptime;
        private String gtime;
        private String gcollateral;
        private String sid;
        private String gauctiontime;
        private String gissoldout;
        private String giscollectionid;
        private String gisguarantee;
        private String gquickrefund;
        private String gGGCT;
        private String gauthentication;

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
    }*/
}
