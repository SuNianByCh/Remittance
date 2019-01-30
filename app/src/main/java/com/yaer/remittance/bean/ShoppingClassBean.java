package com.yaer.remittance.bean;

import java.io.Serializable;
import java.util.List;

public class ShoppingClassBean implements Serializable{

    /**
     * scid : 5
     * scname : 茶酒香道
     * sctime : 1544281049
     * smoney : 500
     * goodsClassIFicationInfoModels : [{"gcid":25,"gcname":"白茶","gctime":"1544453600609","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453597937-229.png","scid":5},{"gcid":26,"gcname":"白酒","gctime":"1544453742150","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453737820-8116.png","scid":5},{"gcid":27,"gcname":"茶道","gctime":"1544453750973","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453746099-9063.png","scid":5},{"gcid":28,"gcname":"红酒","gctime":"1544453762717","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453754056-9961.png","scid":5},{"gcid":29,"gcname":"普洱","gctime":"1544453772433","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453766521-5193.png","scid":5},{"gcid":30,"gcname":"其它","gctime":"1544453780341","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453775781-4069.png","scid":5},{"gcid":31,"gcname":"香道","gctime":"1544453792494","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453783952-7632.png","scid":5},{"gcid":32,"gcname":"岩茶","gctime":"1544453802391","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453796869-2373.png","scid":5},{"gcid":33,"gcname":"洋酒","gctime":"1544453810638","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453805149-6656.png","scid":5}]
     */

    private String scid;
    private String scname;
    private String sctime;
    private String smoney;
    private List<GoodsClassIFicationInfoModelsBean> goodsClassIFicationInfoModels;

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
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

    public static class GoodsClassIFicationInfoModelsBean implements Serializable {
        /**
         * gcid : 25
         * gcname : 白茶
         * gctime : 1544453600609
         * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453597937-229.png
         * scid : 5
         */

        private int gcid;
        private String gcname;
        private String gctime;
        private String gimg;
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

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public int getScid() {
            return scid;
        }

        public void setScid(int scid) {
            this.scid = scid;
        }
    }
}
