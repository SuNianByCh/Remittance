package com.yaer.remittance.bean;

import java.util.List;

public class ShopClassIFicationInfoModels {

    /**
     * scid : 3
     * scname : 书法字画
     * sctime : 1538165534528
     * smoney :
     * goodsClassIFicationInfoModels : [{"gcid":4,"gcname":"陶器","gctime":"1537556081065","scid":3},{"gcid":5,"gcname":"手链","gctime":"1537556081095","scid":3},{"gcid":7,"gcname":"书画篆刻","gctime":"1543419235018","scid":3},{"gcid":8,"gcname":"工艺作品","gctime":"1543419250313","scid":3},{"gcid":9,"gcname":"文玩杂耍","gctime":"1543419266848","scid":3},{"gcid":10,"gcname":"茶酒香道","gctime":"1543419320099","scid":3}]
     */

    private int scid;
    private String scname;
    private String sctime;
    private String smoney;
    private List<GoodsClassIFicationInfoModelsBean> goodsClassIFicationInfoModels;

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getSctime() {
        return sctime;
    }

    public void setSctime(String sctime) {
        this.sctime = sctime;
    }

    public String getSmoney() {
        return smoney;
    }

    public void setSmoney(String smoney) {
        this.smoney = smoney;
    }

    public List<GoodsClassIFicationInfoModelsBean> getGoodsClassIFicationInfoModels() {
        return goodsClassIFicationInfoModels;
    }

    public void setGoodsClassIFicationInfoModels(List<GoodsClassIFicationInfoModelsBean> goodsClassIFicationInfoModels) {
        this.goodsClassIFicationInfoModels = goodsClassIFicationInfoModels;
    }

    public static class GoodsClassIFicationInfoModelsBean {
        /**
         * gcid : 4
         * gcname : 陶器
         * gctime : 1537556081065
         * scid : 3
         */

        private int gcid;
        private String gcname;
        private String gctime;
        private int scid;

        public int getGcid() {
            return gcid;
        }

        public void setGcid(int gcid) {
            this.gcid = gcid;
        }

        public String getGcname() {
            return gcname;
        }

        public void setGcname(String gcname) {
            this.gcname = gcname;
        }

        public String getGctime() {
            return gctime;
        }

        public void setGctime(String gctime) {
            this.gctime = gctime;
        }

        public int getScid() {
            return scid;
        }

        public void setScid(int scid) {
            this.scid = scid;
        }
    }
}
