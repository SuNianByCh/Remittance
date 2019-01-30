package com.yaer.remittance.bean;

import java.util.List;

public class ShopInfoListByKeyBean {
    /**
     * followstatus : false
     * shoplabel : ["手办","好玩的"]
     * shopfans :
     * shopname : 卡诺店1
     * shopimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/b0353739ff95e405453365e218d35422_1544945379939_867.png
     * sbgimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/b0353739ff95e405453365e218d35422_1544945385700_660.jpg
     * shopid : 89
     * shopgoods : [{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919646_39.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919644_528.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919642_687.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919642_258.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919646_870.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919641_64.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919635_18.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919639_468.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919643_601.jpg","goodmoney":"","goodid":98},{"goodimg":"img.alicdn.com/bao/uploaded/i3/O1CN01GFpJ941tgRYC3a7NS_!!0-paimai.jpg,img.alicdn.com/bao/uploaded/i2/O1CN01DHXWpF1tgRYESKjuX_!!0-paimai.jpg,img.alicdn.com/bao/uploaded/i2/O1CN01qBa7lU1tgRYCfiCVS_!!0-paimai.jpg,img.alicdn.com/bao/uploaded/i2/O1CN01t0ZEI71tgRYC3knBN_!!0-paimai.jpg,img.alicdn.com/bao/uploaded/i3/O1CN01FHhro21tgRYCnFhmu_!!0-paimai.jpg","goodmoney":"13500","goodid":109},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545306031469_909.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545306031473_960.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545306031477_299.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545306031475_761.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545306031476_663.jpg","goodmoney":"2000","goodid":110},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820986_229.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820985_69.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820983_865.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820987_677.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-20/8aca5688a0c5e15ff085f7807304421f_1545312820980_701.jpg","goodmoney":"","goodid":111}]
     * scid : 17
     */

    private boolean followstatus;
    private String shopfans;
    private String shopname;
    private String shopimg;
    private String sbgimg;
    private String shopid;
    private int scid;
    private List<String> shoplabel;
    private List<ShopgoodsBean> shopgoods;

    public boolean isFollowstatus() {
        return followstatus;
    }

    public void setFollowstatus(boolean followstatus) {
        this.followstatus = followstatus;
    }

    public String getShopfans() {
        return shopfans;
    }

    public void setShopfans(String shopfans) {
        this.shopfans = shopfans;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg) {
        this.shopimg = shopimg;
    }

    public String getSbgimg() {
        return sbgimg;
    }

    public void setSbgimg(String sbgimg) {
        this.sbgimg = sbgimg;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public List<String> getShoplabel() {
        return shoplabel;
    }

    public void setShoplabel(List<String> shoplabel) {
        this.shoplabel = shoplabel;
    }

    public List<ShopgoodsBean> getShopgoods() {
        return shopgoods;
    }

    public void setShopgoods(List<ShopgoodsBean> shopgoods) {
        this.shopgoods = shopgoods;
    }

    public static class ShopgoodsBean {
        /**
         * goodimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919646_39.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919644_528.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919642_687.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919642_258.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919646_870.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919641_64.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919635_18.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919639_468.jpg,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-16/e403679f769c0c935ed9866681727c55_1544960919643_601.jpg
         * goodmoney :
         * goodid : 98
         */

        private String goodimg;
        private String goodmoney;
        private int goodid;

        public String getGoodimg() {
            return goodimg;
        }

        public void setGoodimg(String goodimg) {
            this.goodimg = goodimg;
        }

        public String getGoodmoney() {
            return goodmoney;
        }

        public void setGoodmoney(String goodmoney) {
            this.goodmoney = goodmoney;
        }

        public int getGoodid() {
            return goodid;
        }

        public void setGoodid(int goodid) {
            this.goodid = goodid;
        }
    }

    /* *//**
     * ShopImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161739-14116d95-c182-4862-971b-d580961d4fc3.png
     * FollowStatus : false
     * ShopName : 一粒沙
     * ShopLabel : ["茶文化","珠串雅玩","时尚首饰"]
     * ShopGoods : [{"GoodId":93,"GoodMoney":"586","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-3e5a6a70-0182-4232-a0bf-3078e65bf7c4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-89476b3c-06f2-44b8-ae6e-a2e4e76d2222.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-ad54ea85-9fab-458b-b77c-cf1107ea6b97.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-07b8d1a3-27a9-4280-96c7-d9e342aad849.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-14d32323-8f91-4961-ae00-1bfb13407fa3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-d5cad75b-068c-400a-9137-06e4c1b306ac.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-175cbeba-ebdc-4180-865d-ac52bc47d2fa.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-093d2598-f904-4af7-ab38-a52a0815238a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-c7b319db-3ce2-4cf4-aef4-13fb2c8352f5.png"},{"GoodId":94,"GoodMoney":"200","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-beec0a9b-3758-4772-ac69-2b46ef83070d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-9eedac15-5a8d-4e0c-8ab6-06ac961af7e5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-55b55bfa-d680-4764-828b-1badca1d1062.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-f26bb2ce-f1cd-42e4-b9bb-f9457f840f36.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-0131b04a-d1ff-4a56-a79b-ced4c7b50b03.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-7ca05aeb-0f31-44d4-bba3-4307b3e96b18.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-bd802426-674e-4f21-85af-c5358b2b474f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-ea2e2ee4-ffa1-4bd7-bdfd-b735c78322bb.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161747-5bb25985-4f5f-40dd-b9db-d7e41aaf5441.png"}]
     * ShopFans :
     * Scid : 17
     * SbgImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161739-3ebdfe5c-7da6-4c3c-a18f-6e15d91a8abd.png
     * ShopId : 87
     *//*

    private String ShopImg;
    private boolean FollowStatus;
    private String ShopName;
    private String ShopFans;
    private int Scid;
    private String SbgImg;
    private String ShopId;
    private List<String> ShopLabel;
    private List<ShopGoodsBean> ShopGoods;

    public String getShopImg() {
        return ShopImg;
    }

    public void setShopImg(String ShopImg) {
        this.ShopImg = ShopImg;
    }

    public boolean isFollowStatus() {
        return FollowStatus;
    }

    public void setFollowStatus(boolean FollowStatus) {
        this.FollowStatus = FollowStatus;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getShopFans() {
        return ShopFans;
    }

    public void setShopFans(String ShopFans) {
        this.ShopFans = ShopFans;
    }

    public int getScid() {
        return Scid;
    }

    public void setScid(int Scid) {
        this.Scid = Scid;
    }

    public String getSbgImg() {
        return SbgImg;
    }

    public void setSbgImg(String SbgImg) {
        this.SbgImg = SbgImg;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public List<String> getShopLabel() {
        return ShopLabel;
    }

    public void setShopLabel(List<String> ShopLabel) {
        this.ShopLabel = ShopLabel;
    }

    public List<ShopGoodsBean> getShopGoods() {
        return ShopGoods;
    }

    public void setShopGoods(List<ShopGoodsBean> ShopGoods) {
        this.ShopGoods = ShopGoods;
    }

    public static class ShopGoodsBean {
        *//**
         * GoodId : 93
         * GoodMoney : 586
         * GoodImg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-3e5a6a70-0182-4232-a0bf-3078e65bf7c4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-89476b3c-06f2-44b8-ae6e-a2e4e76d2222.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-ad54ea85-9fab-458b-b77c-cf1107ea6b97.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-07b8d1a3-27a9-4280-96c7-d9e342aad849.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-14d32323-8f91-4961-ae00-1bfb13407fa3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-d5cad75b-068c-400a-9137-06e4c1b306ac.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-175cbeba-ebdc-4180-865d-ac52bc47d2fa.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-093d2598-f904-4af7-ab38-a52a0815238a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812161746-c7b319db-3ce2-4cf4-aef4-13fb2c8352f5.png
         *//*

        private int GoodId;
        private String GoodMoney;
        private String GoodImg;

        public int getGoodId() {
            return GoodId;
        }

        public void setGoodId(int GoodId) {
            this.GoodId = GoodId;
        }

        public String getGoodMoney() {
            return GoodMoney;
        }

        public void setGoodMoney(String GoodMoney) {
            this.GoodMoney = GoodMoney;
        }

        public String getGoodImg() {
            return GoodImg;
        }

        public void setGoodImg(String GoodImg) {
            this.GoodImg = GoodImg;
        }
    }*/
}
