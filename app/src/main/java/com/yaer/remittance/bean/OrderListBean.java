package com.yaer.remittance.bean;

import java.util.List;

public class OrderListBean {
    /**
     * 上面是订单信息
     * oid : 209
     * ostatus : 0
     * onumber : PPH1545043479525
     * ototalvalue : 250.0
     * uid : 92
     * aid : 174
     * otime : 1545043479525
     * 这个是店铺和商品信息shoplist : [{"osid":"OSID15450434796300","oid":209,"osleaveword":"不知道","sid":92,"sname":"守艺人","simg":"https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png","goodslist":[{"gsid":207,"gid":103,"osid":"OSID15450434796300","ognumber":1,"gname":"股锡碗","gdesc":"盛器 约为1500cc","gmoney":"250","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-87bf4516-269c-456a-a5f3-bfebec311dba.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-6eeca5fb-692a-4fcf-a0e6-35da51b848d9.png"}]}]
     * addressInfoModel : {"aid":177,"acity":"北京北京市东城区","asubdistrict":"123","adesc":"海淀黄庄一号楼","atime":"1545210493605","aisdefault":0,"uid":92,"aphone":"13717870853","aname":"侯杰"}
     */

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
        /**
         * aid : 177
         * acity : 北京北京市东城区
         * asubdistrict : 123
         * adesc : 海淀黄庄一号楼
         * atime : 1545210493605
         * aisdefault : 0
         * uid : 92
         * aphone : 13717870853
         * aname : 侯杰
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

    public static class ShoplistBean {
        /**
         * osid : OSID15450434796300
         * oid : 209
         * osleaveword : 不知道
         * sid : 92
         * sname : 守艺人
         *   "suname":"粒粒皆辛苦",
         * simg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png
         * goodslist : [{"gsid":207,"gid":103,"osid":"OSID15450434796300","ognumber":1,"gname":"股锡碗","gdesc":"盛器 约为1500cc","gmoney":"250","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-87bf4516-269c-456a-a5f3-bfebec311dba.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-6eeca5fb-692a-4fcf-a0e6-35da51b848d9.png"}]
         */

        private String osid;
        private int oid;
        private String osleaveword;
        private int sid;
        private String sname;
        private String suname;

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
            /**
             * gsid : 207
             * gid : 103
             * osid : OSID15450434796300
             * ognumber : 1
             * gname : 股锡碗
             * gdesc : 盛器 约为1500cc
             * gmoney : 250
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-87bf4516-269c-456a-a5f3-bfebec311dba.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-6eeca5fb-692a-4fcf-a0e6-35da51b848d9.png
             */

            private int gsid;
            private int gid;
            private String osid;
            private int ognumber;
            private String gname;
            private String gdesc;
            private String gmoney;
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

            public String getGmoney() {
                return gmoney;
            }

            public void setGmoney(String gmoney) {
                this.gmoney = gmoney;
            }

            public String getGimg() {
                return gimg;
            }

            public void setGimg(String gimg) {
                this.gimg = gimg;
            }
        }
    }


    /* *//**
     * oid : 19
     * ostatus : 0
     * onumber : PPH1544067274198
     * ototalvalue : 3780
     * uid : 18
     * aid : 58
     * otime : 1544067274198
     * shoplist : [{"osid":"OSID15440672743160","oid":19,"osleaveword":"你好店主，给我这个货加急一下","sid":18,"sname":"1","goodslist":[{"gsid":14,"gid":"34","osid":"OSID15440672743160","ognumber":"10","gname":"渡江达摩","gdesc":"品名:渡江达摩","gmoney":"3780","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-bcfea1d4-d503-453f-a0be-b56aa988766c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-c0722536-d3e0-496d-adef-24ec7fa21596.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-a2b1717f-fe67-4213-8791-aff346bceb7e.png"}]}]
     * addressInfoModel : {"aid":58,"acity":"北京北京市东城区","asubdistrict":"123","adesc":"老胡同","atime":"1542588610865","aisdefault":1,"uid":18,"aphone":"13717870853","aname":"侯杰"}
     *//*

    private int oid;
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
         * aid : 58
         * acity : 北京北京市东城区
         * asubdistrict : 123
         * adesc : 老胡同
         * atime : 1542588610865
         * aisdefault : 1
         * uid : 18
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

    public static class ShoplistBean {
        *//**
         * osid : OSID15440672743160
         * oid : 19
         * osleaveword : 你好店主，给我这个货加急一下
         * sid : 18
         * sname : 1
         * goodslist : [{"gsid":14,"gid":"34","osid":"OSID15440672743160","ognumber":"10","gname":"渡江达摩","gdesc":"品名:渡江达摩","gmoney":"3780","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-bcfea1d4-d503-453f-a0be-b56aa988766c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-c0722536-d3e0-496d-adef-24ec7fa21596.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-a2b1717f-fe67-4213-8791-aff346bceb7e.png"}]
         *//*

        private String osid;
        private int oid;
        private String osleaveword;
        private int sid;
        private String sname;
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

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            *//**
             * gsid : 14
             * gid : 34
             * osid : OSID15440672743160
             * ognumber : 10
             * gname : 渡江达摩
             * gdesc : 品名:渡江达摩
             * gmoney : 3780
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-bcfea1d4-d503-453f-a0be-b56aa988766c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-c0722536-d3e0-496d-adef-24ec7fa21596.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-a2b1717f-fe67-4213-8791-aff346bceb7e.png
             *//*

            private int gsid;
            private String gid;
            private String osid;
            private String ognumber;
            private String gname;
            private String gdesc;
            private String gmoney;
            private String gimg;

            public int getGsid() {
                return gsid;
            }

            public void setGsid(int gsid) {
                this.gsid = gsid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getOsid() {
                return osid;
            }

            public void setOsid(String osid) {
                this.osid = osid;
            }

            public String getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(String ognumber) {
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

            public String getGmoney() {
                return gmoney;
            }

            public void setGmoney(String gmoney) {
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
