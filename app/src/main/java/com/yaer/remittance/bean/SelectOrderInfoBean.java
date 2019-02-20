package com.yaer.remittance.bean;

import java.util.List;

public class SelectOrderInfoBean {
    /**
     * oid : 1417
     * ostatus : 3
     * onumber : PPH1547639795783
     * ototalvalue : 200.0
     * uid : 107
     * uname : 粒粒皆辛苦
     * aid : 204
     * otime : 1547639795783
     * opaytime : 1547649708191
     * otrackingnumber : 804061899274004693
     * otrackingname : 圆通速递
     * shoplist : [{"osid":"OSID15476397959720","oid":1417,"osleaveword":"","sid":107,"sname":"白金","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901260932-2b90f118-659d-4113-b544-de3d9925c0da.png","suname":"粒粒皆辛苦","cbuid":0,"opostage":"0.00","goodslist":[{"gsid":791,"gid":257,"osid":"OSID15476397959720","ognumber":1,"gname":"瓷猪","gdesc":"瓷猪","gmoney":"200","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-22ef3e87-ea77-405c-a3fd-3730dbed1bcc.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-a1d7589f-9c9c-4245-a715-cda818ba2abb.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-cf94ad98-d5d2-4557-839b-3da25b6a14bd.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-b37f84f7-e680-45e3-8cc5-7ee46a686553.png","isauction":0,"grefundstatus":0,"rid":0}]}]
     * addressInfoModel : {"aid":204,"acity":"北京北京市东城区","asubdistrict":"123","adesc":"就到家都觉得看看","atime":"1547634479378","aisdefault":1,"uid":107,"aphone":"13611126481","aname":"张飞"}
     * buyers : {"uid":107,"uname":"粒粒皆辛苦","uicon":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20190122042654829b78c3550-2bc3-49fc-bfb6-dae1584ae6f3.jpg","usex":"1","uphone":"13717870853","usignature":"好嗨有哟犹豫","ureturnscale":"0","uinfringementscale":"0","uspecialist":"0","uloginpassword":"58c931e08d2e7c068ca96b3793e2bddb","upaypassword":"EFG@AB","ubalance":"0","utime":"1547186321997","slabel":"","ulevel":"0","uintegral":"100","utoken":"a0e7bf5c05e09b0fb52c6abc1d46ee3e","personalauthentication":0,"expertcertification":0,"enterprisecertification":0,"uthirdpartyid":"E401D5A94B32A6F973E3FEF8E8996B35","uthirdpartytype":1,"ishaveUpaypassword":0,"ishaveLoginpassword":0,"urongcloudtoken":"r4rrfcKL7Y3o9T4p/+F5cDMtDXJS8O1An/smWOd1/cC094QATkoyecLbMQw0UBF0HRnE8JqrW1c82trIDngYJA==","uinvitationcode":"678a90"}
     */

    private int oid;
    private int ostatus;
    private String onumber;
    private double ototalvalue;
    private int uid;
    private String uname;
    private int aid;
    private String otime;
    private String opaytime;
    private String otrackingnumber;
    private String otrackingname;
    private AddressInfoModelBean addressInfoModel;
    private BuyersBean buyers;
    private List<ShoplistBean> shoplist;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOstatus() {
        return ostatus;
    }

    public void setOstatus(int ostatus) {
        this.ostatus = ostatus;
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public double getOtotalvalue() {
        return ototalvalue;
    }

    public void setOtotalvalue(double ototalvalue) {
        this.ototalvalue = ototalvalue;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getOpaytime() {
        return opaytime;
    }

    public void setOpaytime(String opaytime) {
        this.opaytime = opaytime;
    }

    public String getOtrackingnumber() {
        return otrackingnumber;
    }

    public void setOtrackingnumber(String otrackingnumber) {
        this.otrackingnumber = otrackingnumber;
    }

    public String getOtrackingname() {
        return otrackingname;
    }

    public void setOtrackingname(String otrackingname) {
        this.otrackingname = otrackingname;
    }

    public AddressInfoModelBean getAddressInfoModel() {
        return addressInfoModel;
    }

    public void setAddressInfoModel(AddressInfoModelBean addressInfoModel) {
        this.addressInfoModel = addressInfoModel;
    }

    public BuyersBean getBuyers() {
        return buyers;
    }

    public void setBuyers(BuyersBean buyers) {
        this.buyers = buyers;
    }

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class AddressInfoModelBean {
        /**
         * aid : 204
         * acity : 北京北京市东城区
         * asubdistrict : 123
         * adesc : 就到家都觉得看看
         * atime : 1547634479378
         * aisdefault : 1
         * uid : 107
         * aphone : 13611126481
         * aname : 张飞
         */

        private int aid;
        private String acity;
        private String asubdistrict;
        private String adesc;
        private String atime;
        private int aisdefault;
        private int uid;
        private String aphone;
        private String aname;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getAcity() {
            return acity;
        }

        public void setAcity(String acity) {
            this.acity = acity;
        }

        public String getAsubdistrict() {
            return asubdistrict;
        }

        public void setAsubdistrict(String asubdistrict) {
            this.asubdistrict = asubdistrict;
        }

        public String getAdesc() {
            return adesc;
        }

        public void setAdesc(String adesc) {
            this.adesc = adesc;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }

        public int getAisdefault() {
            return aisdefault;
        }

        public void setAisdefault(int aisdefault) {
            this.aisdefault = aisdefault;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAphone() {
            return aphone;
        }

        public void setAphone(String aphone) {
            this.aphone = aphone;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }

    public static class BuyersBean {
        /**
         * uid : 107
         * uname : 粒粒皆辛苦
         * uicon : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/20190122042654829b78c3550-2bc3-49fc-bfb6-dae1584ae6f3.jpg
         * usex : 1
         * uphone : 13717870853
         * usignature : 好嗨有哟犹豫
         * ureturnscale : 0
         * uinfringementscale : 0
         * uspecialist : 0
         * uloginpassword : 58c931e08d2e7c068ca96b3793e2bddb
         * upaypassword : EFG@AB
         * ubalance : 0
         * utime : 1547186321997
         * slabel :
         * ulevel : 0
         * uintegral : 100
         * utoken : a0e7bf5c05e09b0fb52c6abc1d46ee3e
         * personalauthentication : 0
         * expertcertification : 0
         * enterprisecertification : 0
         * uthirdpartyid : E401D5A94B32A6F973E3FEF8E8996B35
         * uthirdpartytype : 1
         * ishaveUpaypassword : 0
         * ishaveLoginpassword : 0
         * urongcloudtoken : r4rrfcKL7Y3o9T4p/+F5cDMtDXJS8O1An/smWOd1/cC094QATkoyecLbMQw0UBF0HRnE8JqrW1c82trIDngYJA==
         * uinvitationcode : 678a90
         */

        private int uid;
        private String uname;
        private String uicon;
        private String usex;
        private String uphone;
        private String usignature;
        private String ureturnscale;
        private String uinfringementscale;
        private String uspecialist;
        private String uloginpassword;
        private String upaypassword;
        private String ubalance;
        private String utime;
        private String slabel;
        private String ulevel;
        private String uintegral;
        private String utoken;
        private int personalauthentication;
        private int expertcertification;
        private int enterprisecertification;
        private String uthirdpartyid;
        private int uthirdpartytype;
        private int ishaveUpaypassword;
        private int ishaveLoginpassword;
        private String urongcloudtoken;
        private String uinvitationcode;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getUsex() {
            return usex;
        }

        public void setUsex(String usex) {
            this.usex = usex;
        }

        public String getUphone() {
            return uphone;
        }

        public void setUphone(String uphone) {
            this.uphone = uphone;
        }

        public String getUsignature() {
            return usignature;
        }

        public void setUsignature(String usignature) {
            this.usignature = usignature;
        }

        public String getUreturnscale() {
            return ureturnscale;
        }

        public void setUreturnscale(String ureturnscale) {
            this.ureturnscale = ureturnscale;
        }

        public String getUinfringementscale() {
            return uinfringementscale;
        }

        public void setUinfringementscale(String uinfringementscale) {
            this.uinfringementscale = uinfringementscale;
        }

        public String getUspecialist() {
            return uspecialist;
        }

        public void setUspecialist(String uspecialist) {
            this.uspecialist = uspecialist;
        }

        public String getUloginpassword() {
            return uloginpassword;
        }

        public void setUloginpassword(String uloginpassword) {
            this.uloginpassword = uloginpassword;
        }

        public String getUpaypassword() {
            return upaypassword;
        }

        public void setUpaypassword(String upaypassword) {
            this.upaypassword = upaypassword;
        }

        public String getUbalance() {
            return ubalance;
        }

        public void setUbalance(String ubalance) {
            this.ubalance = ubalance;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getSlabel() {
            return slabel;
        }

        public void setSlabel(String slabel) {
            this.slabel = slabel;
        }

        public String getUlevel() {
            return ulevel;
        }

        public void setUlevel(String ulevel) {
            this.ulevel = ulevel;
        }

        public String getUintegral() {
            return uintegral;
        }

        public void setUintegral(String uintegral) {
            this.uintegral = uintegral;
        }

        public String getUtoken() {
            return utoken;
        }

        public void setUtoken(String utoken) {
            this.utoken = utoken;
        }

        public int getPersonalauthentication() {
            return personalauthentication;
        }

        public void setPersonalauthentication(int personalauthentication) {
            this.personalauthentication = personalauthentication;
        }

        public int getExpertcertification() {
            return expertcertification;
        }

        public void setExpertcertification(int expertcertification) {
            this.expertcertification = expertcertification;
        }

        public int getEnterprisecertification() {
            return enterprisecertification;
        }

        public void setEnterprisecertification(int enterprisecertification) {
            this.enterprisecertification = enterprisecertification;
        }

        public String getUthirdpartyid() {
            return uthirdpartyid;
        }

        public void setUthirdpartyid(String uthirdpartyid) {
            this.uthirdpartyid = uthirdpartyid;
        }

        public int getUthirdpartytype() {
            return uthirdpartytype;
        }

        public void setUthirdpartytype(int uthirdpartytype) {
            this.uthirdpartytype = uthirdpartytype;
        }

        public int getIshaveUpaypassword() {
            return ishaveUpaypassword;
        }

        public void setIshaveUpaypassword(int ishaveUpaypassword) {
            this.ishaveUpaypassword = ishaveUpaypassword;
        }

        public int getIshaveLoginpassword() {
            return ishaveLoginpassword;
        }

        public void setIshaveLoginpassword(int ishaveLoginpassword) {
            this.ishaveLoginpassword = ishaveLoginpassword;
        }

        public String getUrongcloudtoken() {
            return urongcloudtoken;
        }

        public void setUrongcloudtoken(String urongcloudtoken) {
            this.urongcloudtoken = urongcloudtoken;
        }

        public String getUinvitationcode() {
            return uinvitationcode;
        }

        public void setUinvitationcode(String uinvitationcode) {
            this.uinvitationcode = uinvitationcode;
        }
    }

    public static class ShoplistBean {
        /**
         * osid : OSID15476397959720
         * oid : 1417
         * osleaveword :
         * sid : 107
         * sname : 白金
         * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901260932-2b90f118-659d-4113-b544-de3d9925c0da.png
         * suname : 粒粒皆辛苦
         * cbuid : 0
         * opostage : 0.00
         * goodslist : [{"gsid":791,"gid":257,"osid":"OSID15476397959720","ognumber":1,"gname":"瓷猪","gdesc":"瓷猪","gmoney":"200","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-22ef3e87-ea77-405c-a3fd-3730dbed1bcc.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-a1d7589f-9c9c-4245-a715-cda818ba2abb.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-cf94ad98-d5d2-4557-839b-3da25b6a14bd.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-b37f84f7-e680-45e3-8cc5-7ee46a686553.png","isauction":0,"grefundstatus":0,"rid":0}]
         */

        private String osid;
        private int oid;
        private String osleaveword;
        private int sid;
        private String sname;
        private String simg;
        private String suname;
        private int cbuid;
        private String opostage;
        private List<GoodslistBean> goodslist;

        public String getOsid() {
            return osid;
        }

        public void setOsid(String osid) {
            this.osid = osid;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getOsleaveword() {
            return osleaveword;
        }

        public void setOsleaveword(String osleaveword) {
            this.osleaveword = osleaveword;
        }

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

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public String getSuname() {
            return suname;
        }

        public void setSuname(String suname) {
            this.suname = suname;
        }

        public int getCbuid() {
            return cbuid;
        }

        public void setCbuid(int cbuid) {
            this.cbuid = cbuid;
        }

        public String getOpostage() {
            return opostage;
        }

        public void setOpostage(String opostage) {
            this.opostage = opostage;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * gsid : 791
             * gid : 257
             * osid : OSID15476397959720
             * ognumber : 1
             * gname : 瓷猪
             * gdesc : 瓷猪
             * gmoney : 200
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-22ef3e87-ea77-405c-a3fd-3730dbed1bcc.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-a1d7589f-9c9c-4245-a715-cda818ba2abb.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-cf94ad98-d5d2-4557-839b-3da25b6a14bd.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901152303-b37f84f7-e680-45e3-8cc5-7ee46a686553.png
             * isauction : 0
             * grefundstatus : 0
             * rid : 0
             */

            private int gsid;
            private int gid;
            private String osid;
            private int ognumber;
            private String gname;
            private String gdesc;
            private double gmoney;
            private String gimg;
            private int isauction;
            private int grefundstatus;
            private int rid;

            public int getGsid() {
                return gsid;
            }

            public void setGsid(int gsid) {
                this.gsid = gsid;
            }

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getOsid() {
                return osid;
            }

            public void setOsid(String osid) {
                this.osid = osid;
            }

            public int getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(int ognumber) {
                this.ognumber = ognumber;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGdesc() {
                return gdesc;
            }

            public void setGdesc(String gdesc) {
                this.gdesc = gdesc;
            }

            public double getGmoney() {
                return gmoney;
            }

            public void setGmoney(double gmoney) {
                this.gmoney = gmoney;
            }

            public String getGimg() {
                return gimg;
            }

            public void setGimg(String gimg) {
                this.gimg = gimg;
            }

            public int getIsauction() {
                return isauction;
            }

            public void setIsauction(int isauction) {
                this.isauction = isauction;
            }

            public int getGrefundstatus() {
                return grefundstatus;
            }

            public void setGrefundstatus(int grefundstatus) {
                this.grefundstatus = grefundstatus;
            }

            public int getRid() {
                return rid;
            }

            public void setRid(int rid) {
                this.rid = rid;
            }
        }
    }



    /* *//**
     * oid : 1276
     * ostatus : 1
     * onumber : PPH1547395492030
     * ototalvalue : 100.0
     * uid : 107
     * aid : 199
     * otime : 1547395492030
     * opaytime : 1547395502393
     * "otrackingnumber":"804061899274004693",
     * "otrackingname":"圆通速递",
     * shoplist : [{"osid":"OSID15473954921390","oid":1276,"osleaveword":"你好","sid":107,"sname":"雍和堂","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111935-c00dd4e0-8e14-4e40-bc50-8c0e52a74656.png","goodslist":[{"gsid":645,"gid":228,"osid":"OSID15473954921390","ognumber":1,"gname":"牛牛牛牛牛牛","gdesc":"牛牛牛牛牛牛牛牛牛牛牛牛牛牛牛","gmoney":"100","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-790f7494-8f32-46d5-baa6-5f718767343b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-eac0fe57-60db-40ea-9570-1aa60c1e3cd6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-2d83415a-0528-4eda-9bba-5c302cf81665.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-d028a33c-1ea0-436b-9fc8-e493283fd8ee.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-3421c415-a6af-4b4b-82f2-e3c616898e5a.png","grefundstatus":0}]}]
     * addressInfoModel : {"aid":199,"acity":"山东省济南市槐荫区","asubdistrict":"123","adesc":"才智大厦605","atime":"1547216066302","aisdefault":1,"uid":107,"aphone":"13717870853","aname":"侯杰"}
     *//*


    private int oid;
    private int ostatus;
    private String onumber;
    private double ototalvalue;
    private int uid;
    private int aid;
    private String otime;
    private String opaytime;
    private String otrackingnumber;
    private String otrackingname;

    public String getOtrackingnumber() {
        return otrackingnumber;
    }

    public void setOtrackingnumber(String otrackingnumber) {
        this.otrackingnumber = otrackingnumber;
    }

    public String getOtrackingname() {
        return otrackingname;
    }

    public void setOtrackingname(String otrackingname) {
        this.otrackingname = otrackingname;
    }

    private AddressInfoModelBean addressInfoModel;

    public BuyersBean getBuyers() {
        return buyers;
    }

    public void setBuyers(BuyersBean buyers) {
        this.buyers = buyers;
    }

    private BuyersBean buyers;
    private List<ShoplistBean> shoplist;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOstatus() {
        return ostatus;
    }

    public void setOstatus(int ostatus) {
        this.ostatus = ostatus;
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public double getOtotalvalue() {
        return ototalvalue;
    }

    public void setOtotalvalue(double ototalvalue) {
        this.ototalvalue = ototalvalue;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getOpaytime() {
        return opaytime;
    }

    public void setOpaytime(String opaytime) {
        this.opaytime = opaytime;
    }

    public AddressInfoModelBean getAddressInfoModel() {
        return addressInfoModel;
    }

    public void setAddressInfoModel(AddressInfoModelBean addressInfoModel) {
        this.addressInfoModel = addressInfoModel;
    }

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class AddressInfoModelBean {
        *//**
         * aid : 199
         * acity : 山东省济南市槐荫区
         * asubdistrict : 123
         * adesc : 才智大厦605
         * atime : 1547216066302
         * aisdefault : 1
         * uid : 107
         * aphone : 13717870853
         * aname : 侯杰
         *//*
        private int aid;
        private String acity;
        private String asubdistrict;
        private String adesc;
        private String atime;
        private int aisdefault;
        private int uid;
        private String aphone;
        private String aname;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getAcity() {
            return acity;
        }

        public void setAcity(String acity) {
            this.acity = acity;
        }

        public String getAsubdistrict() {
            return asubdistrict;
        }

        public void setAsubdistrict(String asubdistrict) {
            this.asubdistrict = asubdistrict;
        }

        public String getAdesc() {
            return adesc;
        }

        public void setAdesc(String adesc) {
            this.adesc = adesc;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }

        public int getAisdefault() {
            return aisdefault;
        }

        public void setAisdefault(int aisdefault) {
            this.aisdefault = aisdefault;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAphone() {
            return aphone;
        }

        public void setAphone(String aphone) {
            this.aphone = aphone;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }
    public static class BuyersBean {
        //用户id
        private int uid;
        //名称
        private String uname;
        //头像
        private String uicon;
        //性别
        private String usex;
        //手机号
        private String uphone;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getUsex() {
            return usex;
        }

        public void setUsex(String usex) {
            this.usex = usex;
        }

        public String getUphone() {
            return uphone;
        }

        public void setUphone(String uphone) {
            this.uphone = uphone;
        }
    }

    public static class ShoplistBean {
        *//**
         * osid : OSID15473954921390
         * oid : 1276
         * osleaveword : 你好
         * sid : 107
         * sname : 雍和堂
         * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111935-c00dd4e0-8e14-4e40-bc50-8c0e52a74656.png
         * goodslist : [{"gsid":645,"gid":228,"osid":"OSID15473954921390","ognumber":1,"gname":"牛牛牛牛牛牛","gdesc":"牛牛牛牛牛牛牛牛牛牛牛牛牛牛牛","gmoney":"100","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-790f7494-8f32-46d5-baa6-5f718767343b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-eac0fe57-60db-40ea-9570-1aa60c1e3cd6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-2d83415a-0528-4eda-9bba-5c302cf81665.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-d028a33c-1ea0-436b-9fc8-e493283fd8ee.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-3421c415-a6af-4b4b-82f2-e3c616898e5a.png","grefundstatus":0}]
         *//*


        private String osid;
        private int oid;
        private String osleaveword;
        private int sid;
        private String sname;
        private String suname;
        private int cbuid;
        private String opostage;

        public int getCbuid() {
            return cbuid;
        }

        public void setCbuid(int cbuid) {
            this.cbuid = cbuid;
        }

        public String getOpostage() {
            return opostage;
        }

        public void setOpostage(String opostage) {
            this.opostage = opostage;
        }

        public String getSuname() {
            return suname;
        }

        public void setSuname(String suname) {
            this.suname = suname;
        }

        private String simg;
        private List<GoodslistBean> goodslist;

        public String getOsid() {
            return osid;
        }

        public void setOsid(String osid) {
            this.osid = osid;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getOsleaveword() {
            return osleaveword;
        }

        public void setOsleaveword(String osleaveword) {
            this.osleaveword = osleaveword;
        }

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

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            *//**
             * gsid : 645
             * gid : 228
             * osid : OSID15473954921390
             * ognumber : 1
             * gname : 牛牛牛牛牛牛
             * gdesc : 牛牛牛牛牛牛牛牛牛牛牛牛牛牛牛
             * gmoney : 100
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-790f7494-8f32-46d5-baa6-5f718767343b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-eac0fe57-60db-40ea-9570-1aa60c1e3cd6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-2d83415a-0528-4eda-9bba-5c302cf81665.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-d028a33c-1ea0-436b-9fc8-e493283fd8ee.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901120216-3421c415-a6af-4b4b-82f2-e3c616898e5a.png
             * grefundstatus : 0
             * isauction 1
             * grefundstatus3
             * rid0
             *//*
            private int gsid;
            private int gid;
            private String osid;
            private int ognumber;
            private String gname;
            private String gdesc;
            private double gmoney;
            private String gimg;
            private int grefundstatus;
            private int rid;//退款id(跳转退款详情使用)
            private int isauction;

            public int getIsauction() {
                return isauction;
            }

            public void setIsauction(int isauction) {
                this.isauction = isauction;
            }

            public int getRid() {
                return rid;
            }

            public void setRid(int rid) {
                this.rid = rid;
            }

            public int getGsid() {
                return gsid;
            }

            public void setGsid(int gsid) {
                this.gsid = gsid;
            }

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getOsid() {
                return osid;
            }

            public void setOsid(String osid) {
                this.osid = osid;
            }

            public int getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(int ognumber) {
                this.ognumber = ognumber;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGdesc() {
                return gdesc;
            }

            public void setGdesc(String gdesc) {
                this.gdesc = gdesc;
            }

            public double getGmoney() {
                return gmoney;
            }

            public void setGmoney(double gmoney) {
                this.gmoney = gmoney;
            }

            public String getGimg() {
                return gimg;
            }

            public void setGimg(String gimg) {
                this.gimg = gimg;
            }

            public int getGrefundstatus() {
                return grefundstatus;
            }

            public void setGrefundstatus(int grefundstatus) {
                this.grefundstatus = grefundstatus;
            }
        }
    }*/


    /**
     * oid : 221
     * ostatus : 0
     * onumber : PPH1545203664250
     * ototalvalue : 200.0
     * uid : 92
     * aid : 176
     * otime : 1545203664250
     * shoplist : [{"osid":"OSID15452036643630","oid":221,"osleaveword":"侯杰","sid":92,"sname":"守艺人","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png","goodslist":[{"gsid":219,"gid":100,"osid":"OSID15452036643630","ognumber":1,"gname":"山水一幅画","gdesc":"山水一幅画 真迹","gmoney":"200","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-a7b4b47f-b0af-415d-9268-ec8f417f4eb9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-c753c162-4fdd-44c6-adfb-d37af69d7958.png"}]}]
     * addressInfoModel : {"aid":176,"acity":"北京北京市东城区","asubdistrict":"123","adesc":"就是觉得记得记得尽快","atime":"1545048107721","aisdefault":1,"uid":92,"aphone":"13313433434","aname":"你好"}
     */

  /*  private int oid;
    private int ostatus;
    private String onumber;
    private double ototalvalue;
    private int uid;
    private int aid;
    private String otime;
    private AddressInfoModelBean addressInfoModel;
    private List<ShoplistBean> shoplist;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOstatus() {
        return ostatus;
    }

    public void setOstatus(int ostatus) {
        this.ostatus = ostatus;
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public double getOtotalvalue() {
        return ototalvalue;
    }

    public void setOtotalvalue(double ototalvalue) {
        this.ototalvalue = ototalvalue;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public AddressInfoModelBean getAddressInfoModel() {
        return addressInfoModel;
    }

    public void setAddressInfoModel(AddressInfoModelBean addressInfoModel) {
        this.addressInfoModel = addressInfoModel;
    }

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class AddressInfoModelBean {
        *//**
     * aid : 176
     * acity : 北京北京市东城区
     * asubdistrict : 123
     * adesc : 就是觉得记得记得尽快
     * atime : 1545048107721
     * aisdefault : 1
     * uid : 92
     * aphone : 13313433434
     * aname : 你好
     *//*

        private int aid;
        private String acity;
        private String asubdistrict;
        private String adesc;
        private String atime;
        private int aisdefault;
        private int uid;
        private String aphone;
        private String aname;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getAcity() {
            return acity;
        }

        public void setAcity(String acity) {
            this.acity = acity;
        }

        public String getAsubdistrict() {
            return asubdistrict;
        }

        public void setAsubdistrict(String asubdistrict) {
            this.asubdistrict = asubdistrict;
        }

        public String getAdesc() {
            return adesc;
        }

        public void setAdesc(String adesc) {
            this.adesc = adesc;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }

        public int getAisdefault() {
            return aisdefault;
        }

        public void setAisdefault(int aisdefault) {
            this.aisdefault = aisdefault;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAphone() {
            return aphone;
        }

        public void setAphone(String aphone) {
            this.aphone = aphone;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }

    public static class ShoplistBean {
        *//**
     * osid : OSID15452036643630
     * oid : 221
     * osleaveword : 侯杰
     * sid : 92
     * sname : 守艺人
     * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png
     * goodslist : [{"gsid":219,"gid":100,"osid":"OSID15452036643630","ognumber":1,"gname":"山水一幅画","gdesc":"山水一幅画 真迹","gmoney":"200","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-a7b4b47f-b0af-415d-9268-ec8f417f4eb9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-c753c162-4fdd-44c6-adfb-d37af69d7958.png"}]
     *//*

        private String osid;
        private int oid;
        private String osleaveword;
        private int sid;
        private String sname;
        private String simg;
        private List<GoodslistBean> goodslist;

        public String getOsid() {
            return osid;
        }

        public void setOsid(String osid) {
            this.osid = osid;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getOsleaveword() {
            return osleaveword;
        }

        public void setOsleaveword(String osleaveword) {
            this.osleaveword = osleaveword;
        }

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

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            *//**
     * gsid : 219
     * gid : 100
     * osid : OSID15452036643630
     * ognumber : 1
     * gname : 山水一幅画
     * gdesc : 山水一幅画 真迹
     * gmoney : 200
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-a7b4b47f-b0af-415d-9268-ec8f417f4eb9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-c753c162-4fdd-44c6-adfb-d37af69d7958.png
     *//*

            private int gsid;
            private int gid;
            private String osid;
            private int ognumber;
            private String gname;
            private String gdesc;
            private double gmoney;
            private String gimg;

            public int getGsid() {
                return gsid;
            }

            public void setGsid(int gsid) {
                this.gsid = gsid;
            }

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getOsid() {
                return osid;
            }

            public void setOsid(String osid) {
                this.osid = osid;
            }

            public int getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(int ognumber) {
                this.ognumber = ognumber;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGdesc() {
                return gdesc;
            }

            public void setGdesc(String gdesc) {
                this.gdesc = gdesc;
            }

            public double getGmoney() {
                return gmoney;
            }

            public void setGmoney(double gmoney) {
                this.gmoney = gmoney;
            }

            public String getGimg() {
                return gimg;
            }

            public void setGimg(String gimg) {
                this.gimg = gimg;
            }
        }
    }*/
}
