package com.yaer.remittance.bean;

import java.util.List;

public class ShopInfoByidBean {
    /**
     * scmoney : 200
     * followstatus : false
     * shoplabel : ["茶文化","时尚首饰","民俗工艺"]
     * shopfans :
     * shopname : 守艺人
     * shopimg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-0e873754-870e-4879-8223-9e689221d1c9.png
     * scname : 工艺作品
     * sbgimg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812161959-74554f19-d35b-4495-be51-4b196925ffa6.png
     * shopid : 92
     * shopgoods : [{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-dddb6bc0-6eae-4fab-8457-9a1fdc15e1a2.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-f65b0345-c813-44bf-baaf-3c4c42509cd8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-6e31b856-aed5-445c-aa3b-1faf01fa8e78.png","goodmoney":"","goodid":99},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-a7b4b47f-b0af-415d-9268-ec8f417f4eb9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170641-c753c162-4fdd-44c6-adfb-d37af69d7958.png","goodmoney":"200","goodid":100},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170645-ee6ae253-edd0-4568-a0ba-4810b009c169.png","goodmoney":"","goodid":101},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170654-0073612d-7037-4b4d-b745-399d01e3e71e.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170654-7fa3c581-efe6-46b1-9ee4-4982916d9e8e.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170654-a277b333-da06-4c86-86ed-f59425dd2597.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170654-788b3831-fb37-4dd0-94d6-fca3be4cce30.png","goodmoney":"","goodid":102},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-87bf4516-269c-456a-a5f3-bfebec311dba.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812170930-6eeca5fb-692a-4fcf-a0e6-35da51b848d9.png","goodmoney":"250","goodid":103},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-b87eaa36-cbc0-44f0-b381-7ae3063ff13c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-3acd6c8e-b475-4d68-b00d-58333ff22ed9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-e836eb05-0fef-441d-af50-f43d6c8f31ab.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-dbae57b8-fa04-470f-894d-92224a393d1a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-97c5ec5b-97ce-46eb-9a32-2289ec749155.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-aee32cdc-c7d1-4c36-b42f-5c6cada051c3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-7073d6eb-11ea-4c08-8e5f-c04c6ecb7d83.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-bc4a52cd-c302-4f53-955f-94b1783314db.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812191529-5d1c35a1-eb11-4a89-bd22-477223bc1312.png","goodmoney":"1200","goodid":105},{"goodimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-2aa57992-da87-421a-82e5-ec2161ddf7a3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-4377e8da-3256-4a78-83d5-c887eb8d086b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-2becefd2-1cdc-4c06-8644-dd5b7c42293b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-63594e1e-bf40-44fc-a328-3cfa53aa0fe6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-f171ec51-1074-46e7-b230-d8da4b8db653.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-d5de868c-d3f9-4964-866d-4744cf07fcf0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-4d1b066e-9dc6-4ee0-93a5-571f0c1fbdf6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-241f3b2c-c801-477b-a954-c39fdcf71f65.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812200053-956d061a-d1d1-4522-80db-720070347771.png","goodmoney":"","goodid":108}]
     * scid : 18
     */

    private String scmoney;
    private boolean followstatus;
    private String shopfans;
    private String shopname;
    private String shopimg;
    private String scname;
    private String sdesc;
    private String sbgimg;
    private int shopid;
    private String scid;
    private List<String> shoplabel;
    private List<ShopgoodsBean> shopgoods;

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public String getScmoney() {
        return scmoney;
    }

    public void setScmoney(String scmoney) {
        this.scmoney = scmoney;
    }

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

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getSbgimg() {
        return sbgimg;
    }

    public void setSbgimg(String sbgimg) {
        this.sbgimg = sbgimg;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
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
         * goodimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-dddb6bc0-6eae-4fab-8457-9a1fdc15e1a2.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-f65b0345-c813-44bf-baaf-3c4c42509cd8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812162041-6e31b856-aed5-445c-aa3b-1faf01fa8e78.png
         * goodmoney :
         * goodid : 99
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


    /**
     * ShopImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812140159-16a1325f-f32e-45a5-9d9b-aa35adf33be5.png
     * ShopName : 一粒沙
     * Scname : 茶香酒道
     * ShopLabel : ["茶文化","天然玉石","珠串雅玩"]
     * ShopGoods : [{"GoodId":82,"GoodMoney":"598","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-2378f1f3-bd62-426a-9a8d-1b5f4681558b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-f6a33ec6-3710-4fec-bca1-2014ea04a6b4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-03347ef5-4ecc-4e08-a20c-30f24e3a15e0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-df69ede3-93f5-4a0b-8dec-a4234fe39e6f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-13cf5513-7d66-4587-a9b4-a34eedcb9263.png"},{"GoodId":83,"GoodMoney":"","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-bee77156-a86f-4319-ad85-911dbb50dbd0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-543a8739-f8fe-4aef-be1e-d513709c31b1.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-399cf775-3bce-49e2-b590-3d2a60a4a8c5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-c7953b52-4e1b-42d6-8983-769c80e810f8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-fb9195b3-0906-4915-b13b-a66810af3122.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-a94e1044-5011-4a32-a337-92f4ee85163e.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-029b7888-f0b4-4334-9d93-afb74b2de0fb.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-22e20188-c57b-4f2a-bc6e-95e52c0e1821.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141518-646a4085-d637-4093-b276-b6ea91789434.png"},{"GoodId":84,"GoodMoney":"","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-a842c95f-f7fc-44af-bee2-3fc715613ad0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-14e9b099-b532-4728-8a85-a654e18ce59c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-2cde6952-52f9-4246-9ac6-dca0ef1e3958.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-48668612-e28b-46be-871e-a2ac803f3c76.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-35f2b5c2-fbf0-4b3a-962f-685bed0bcdd1.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-938baecf-0b72-4c7e-ae96-397d93c97ea5.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-454fcbd1-6ed9-494f-ae37-c32148086d8d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-cde5caaf-94d3-4854-b677-e33d38fbf4e8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812141557-97b30098-1854-4c95-b136-367716c3b587.png"}]
     * ShopFans :
     * Scmoney : 100
     * Scid : 17
     * SbgImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812140159-5115317a-5a8c-40b7-bd08-0a530ecb6fee.png
     * ShopId : 50
     *//*

    private String ShopImg;
    private String ShopName;
    private String Scname;
    private String ShopFans;
    private String Scmoney;
    private String Scid;
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

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getScname() {
        return Scname;
    }

    public void setScname(String Scname) {
        this.Scname = Scname;
    }

    public String getShopFans() {
        return ShopFans;
    }

    public void setShopFans(String ShopFans) {
        this.ShopFans = ShopFans;
    }

    public String getScmoney() {
        return Scmoney;
    }

    public void setScmoney(String Scmoney) {
        this.Scmoney = Scmoney;
    }

    public String getScid() {
        return Scid;
    }

    public void setScid(String Scid) {
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
     * GoodId : 82
     * GoodMoney : 598
     * GoodImg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-2378f1f3-bd62-426a-9a8d-1b5f4681558b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-f6a33ec6-3710-4fec-bca1-2014ea04a6b4.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-03347ef5-4ecc-4e08-a20c-30f24e3a15e0.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-df69ede3-93f5-4a0b-8dec-a4234fe39e6f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812140205-13cf5513-7d66-4587-a9b4-a34eedcb9263.png
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
    }
*/

    /**
     * ShopImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812090740-d0e80da2-54ee-4877-88de-9aaa0e6be713.png
     * ShopName : 一粒沙
     * ShopLabel : ["茶文化","珠串雅玩","时尚首饰","生活百货"]
     * ShopGoods : [{"GoodId":56,"GoodMoney":"498","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-5558a51d-1840-4e4e-9523-d0f1f69c8831.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-a4609bde-7547-4495-aab0-45ad41aec3ef.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-56be8121-1394-4d97-92f8-34d4f7caa41b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-05181eda-c89b-4fdf-a36b-636941eccb2d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-d9b8b697-a91a-42bf-9b20-bdcdd354a696.png"},{"GoodId":57,"GoodMoney":"820","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812091026-c3352ded-bc05-4319-ba7f-6217cb98c058.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812091026-dc78231f-6a3c-4ca3-bd43-e74e9c965e96.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812091026-2046be79-0988-4444-929d-fd690ac65f50.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812091026-6a70f499-2e05-4bd3-a025-ba6a581a95a0.png"}]
     * ShopFans :
     * Scid : 5
     * ShopId : 31
     *//*

    private String ShopImg;
    private String ShopName;
    private String ShopFans;
    private String Scid;
    private int ShopId;
    private List<String> ShopLabel;
    private List<ShopGoodsBean> ShopGoods;

    public String getShopImg() {
        return ShopImg;
    }

    public void setShopImg(String ShopImg) {
        this.ShopImg = ShopImg;
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

    public String getScid() {
        return Scid;
    }

    public void setScid(String Scid) {
        this.Scid = Scid;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
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
     * GoodId : 56
     * GoodMoney : 498
     * GoodImg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-5558a51d-1840-4e4e-9523-d0f1f69c8831.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-a4609bde-7547-4495-aab0-45ad41aec3ef.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-56be8121-1394-4d97-92f8-34d4f7caa41b.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-05181eda-c89b-4fdf-a36b-636941eccb2d.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201812090818-d9b8b697-a91a-42bf-9b20-bdcdd354a696.png
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

    /**
     * ShopImg : https://paipinhui.oss-cn-qingdao.aliyuncs.com/banner/banner3.png
     * ShopName : 1
     * ShopLabel : ["饿啊阿达"]
     * ShopGoods : [{"GoodId":"34","GoodMoney":"3780","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-bcfea1d4-d503-453f-a0be-b56aa988766c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-c0722536-d3e0-496d-adef-24ec7fa21596.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-a2b1717f-fe67-4213-8791-aff346bceb7e.png"},{"GoodId":"37","GoodMoney":"280","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222316-fcea2a20-5761-4e49-97a2-90f94a8b3373.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222316-116f1c13-df6a-49f1-9ad6-a13219d3d8fa.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222316-351271b7-2309-44b3-a345-c4412b0f7803.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222316-46cbb7b9-6e19-432e-a0f2-830159e18ddf.png"},{"GoodId":"40","GoodMoney":"450","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222322-c71d08ad-de06-4f3b-ace8-7277ee0bd656.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222322-d69f4079-59f2-4e0c-be0a-b729b4fd63c3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222322-c849a29c-9343-456d-86a4-c5abf954427a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222322-b5d04460-b664-4623-8c59-ba4b41bf498a.png"},{"GoodId":"41","GoodMoney":"50","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222325-48e12c16-97a2-40c3-bf59-e59ca595535c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222325-4beaed8a-7490-40f0-a128-b1d9fbee45ad.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222325-0146f67d-22b9-4f14-8696-2619f4ad1331.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222325-bf419ddd-7136-4711-b431-7180f3dc0d29.png"},{"GoodId":"42","GoodMoney":"298","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222328-aa2b0884-73cf-426b-b921-d98c91848e26.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222328-68325eb8-c32d-4e2c-aa0b-86c481ba699f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222328-9321298f-cc25-4979-8504-20bf3b7e32f2.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222328-e6b94443-6c24-4748-b2fb-c752385f9dce.png"},{"GoodId":"43","GoodMoney":"598","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222329-5fd67a82-d12d-4d2d-97e6-c9e77196207a.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222329-af00ed67-92cf-4be9-9406-2ff9455adc3f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222329-03cdc6d7-db7a-48f7-a63d-de089d08d3fd.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222329-19c54cad-a18a-44bb-b718-871e65dc3cad.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222329-cacce73f-b68b-4138-8458-28828106cd20.png"},{"GoodId":"46","GoodMoney":"","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222338-dd3e41fc-0273-42ef-9ca6-0b2d6563102f.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222338-3ead5fc5-4074-4645-af55-ecfd4cfee4ad.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222338-ef6bcdd3-2514-4683-8a1c-df57caefff60.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222338-2122704f-7531-42ae-92b0-da183d83a0e6.png"},{"GoodId":"50","GoodMoney":"","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-c449bc26-e252-4d3a-a081-0c38e564cb96.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-12317e56-573f-4bb2-9a3a-fffd9fe92fe9.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-72973288-cd44-46c6-b410-4c30d50c17f3.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811240023-1625ed24-1d64-4680-80f2-a0485dcae390.png"},{"GoodId":"51","GoodMoney":"1000","GoodImg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-c18963fb-4451-463c-9759-10560b960185.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-da330ee7-f5b3-4331-b703-b6ce8004afa8.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811241031-e4cf149a-d97e-4df3-afb7-8f40ad1cc5cf.png"}]
     * ShopFans : 1213
     * ShopId : 18
     *//*

    private String ShopImg;
    private String ShopName;
    private String ShopFans;
    private int ShopId;
    private List<String> ShopLabel;
    private List<ShopGoodsBean> ShopGoods;

    public String getShopImg() {
        return ShopImg;
    }

    public void setShopImg(String ShopImg) {
        this.ShopImg = ShopImg;
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

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
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
     * GoodId : 34
     * GoodMoney : 3780
     * GoodImg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-bcfea1d4-d503-453f-a0be-b56aa988766c.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-c0722536-d3e0-496d-adef-24ec7fa21596.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222307-a2b1717f-fe67-4213-8791-aff346bceb7e.png
     *//*

        private String GoodId;
        private String GoodMoney;
        private String GoodImg;

        public String getGoodId() {
            return GoodId;
        }

        public void setGoodId(String GoodId) {
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
