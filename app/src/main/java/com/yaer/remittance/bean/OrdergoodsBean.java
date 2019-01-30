package com.yaer.remittance.bean;

import java.util.List;

public class OrdergoodsBean {

    /**
     * uid : 8
     * ototalvalue : 1
     * aid : 68
     * shoplist : [{"osleaveword":"","sid":18,"goodslist":[{"gid":"34","ognumber":1}]}]
     */

    //你在这个类里把各个字段的注释打一下
    private int uid;//用户id
    private double ototalvalue;//总金额
    private String aid;//地址id
    private List<ShoplistBean> shoplist;//商品集合

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getOtotalvalue() {
        return ototalvalue;
    }

    public void setOtotalvalue(double ototalvalue) {
        this.ototalvalue = ototalvalue;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class ShoplistBean {
        /**
         * osleaveword :
         * sid : 18
         * goodslist : [{"gid":"34","ognumber":1}]
         */

        private String osleaveword;//对商家说的话
        private String sid;//店铺id
        private String opostage;//邮费
        private List<GoodslistBean> goodslist;

        public String getOpostage() {
            return opostage;
        }

        public void setOpostage(String opostage) {
            this.opostage = opostage;
        }

        public String getOsleaveword() {
            return osleaveword;
        }

        public void setOsleaveword(String osleaveword) {
            this.osleaveword = osleaveword;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * gid : 34
             * ognumber : 1
             */

            private String gid;//商品id?这个是商品id
            private int ognumber;//商品库存提交数量
            private double gmoney;//商品价格

            public double getGmoney() {
                return gmoney;
            }

            public void setGmoney(double gmoney) {
                this.gmoney = gmoney;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public int getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(int ognumber) {
                this.ognumber = ognumber;
            }
        }
    }



   /* private List<ShoplistBean> shoplist;

    public List<ShoplistBean> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShoplistBean> shoplist) {
        this.shoplist = shoplist;
    }

    public static class ShoplistBean {
        *//**
     * osleaveword :
     * sid : 18
     * goodslist : [{"gid":"34","ognumber":1}]
     *//*

        private String osleaveword;
        private String sid;
        private List<GoodslistBean> goodslist;

        public String getOsleaveword() {
            return osleaveword;
        }

        public void setOsleaveword(String osleaveword) {
            this.osleaveword = osleaveword;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            *//**
     * gid : 34
     * ognumber : 1
     *//*

            private String gid;
            private String ognumber;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getOgnumber() {
                return ognumber;
            }

            public void setOgnumber(String ognumber) {
                this.ognumber = ognumber;
            }
        }
    }*/

}
