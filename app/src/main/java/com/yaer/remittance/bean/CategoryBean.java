package com.yaer.remittance.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：wangzihang
 * date： 2017/8/4 11:13
 * desctiption：
 * e-mail：wangzihang@xiaohongchun.com
 */

public class CategoryBean implements Serializable  {
    /**
     * code : 200
     * msg : 获得商铺分类列表成功！
     * result : [{"scid":17,"scname":"茶香酒道","sctime":"1544717624515","smoney":"100","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717309312-8440.png","goodsClassIFicationInfoModels":[{"gcid":47,"gcname":"白茶","gctime":"1544718922223","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718880545-7419.png","scid":17},{"gcid":48,"gcname":"白酒","gctime":"1544718954622","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718943773-4605.png","scid":17},{"gcid":49,"gcname":"茶道","gctime":"1544718965999","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718963842-6663.png","scid":17},{"gcid":50,"gcname":"红酒","gctime":"1544718974695","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718971703-1192.png","scid":17},{"gcid":51,"gcname":"普洱茶","gctime":"1544718983359","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718980993-1308.png","scid":17},{"gcid":52,"gcname":"香道","gctime":"1544719015854","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544719004128-8144.png","scid":17}]},{"scid":18,"scname":"工艺作品","sctime":"1544717656465","smoney":"200","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717650822-3138.png","goodsClassIFicationInfoModels":[]},{"scid":19,"scname":"花鸟文娱","sctime":"1544717680161","smoney":"50","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717679154-1331.png","goodsClassIFicationInfoModels":[]},{"scid":20,"scname":"书画篆刻","sctime":"1544717703729","smoney":"150","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717702652-8593.png","goodsClassIFicationInfoModels":[]},{"scid":21,"scname":"文玩杂项","sctime":"1544717760569","smoney":"150","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717762587-5666.png","goodsClassIFicationInfoModels":[]},{"scid":22,"scname":"邮币纸品","sctime":"1544717783675","smoney":"150","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717784818-3360.png","goodsClassIFicationInfoModels":[]},{"scid":23,"scname":"紫砂陶瓷","sctime":"1544717807929","smoney":"200","scimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717805877-9384.png","goodsClassIFicationInfoModels":[]}]
     */

    private String code;
    private String msg;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * scid : 17//店铺分类
         * scname : 茶香酒道
         * sctime : 1544717624515
         * smoney : 100
         * scimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544717309312-8440.png
         * goodsClassIFicationInfoModels : [{"gcid":47,"gcname":"白茶","gctime":"1544718922223","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718880545-7419.png","scid":17},{"gcid":48,"gcname":"白酒","gctime":"1544718954622","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718943773-4605.png","scid":17},{"gcid":49,"gcname":"茶道","gctime":"1544718965999","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718963842-6663.png","scid":17},{"gcid":50,"gcname":"红酒","gctime":"1544718974695","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718971703-1192.png","scid":17},{"gcid":51,"gcname":"普洱茶","gctime":"1544718983359","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718980993-1308.png","scid":17},{"gcid":52,"gcname":"香道","gctime":"1544719015854","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544719004128-8144.png","scid":17}]
         */

        private String scid;
        private String scname;
        private String sctime;
        private String smoney;
        private String scimg;
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

        public String getScimg() {
            return scimg;
        }

        public void setScimg(String scimg) {
            this.scimg = scimg;
        }

        public List<GoodsClassIFicationInfoModelsBean> getGoodsClassIFicationInfoModels() {
            return goodsClassIFicationInfoModels;
        }

        public void setGoodsClassIFicationInfoModels(List<GoodsClassIFicationInfoModelsBean> goodsClassIFicationInfoModels) {
            this.goodsClassIFicationInfoModels = goodsClassIFicationInfoModels;
        }

        public static class GoodsClassIFicationInfoModelsBean {
            /**
             * gcid : 47//
             * gcname : 白茶
             * gctime : 1544718922223
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-14/PPH1544718880545-7419.png
             * scid : 17//一级分类id
             */

            private String gcid;
            private String gcname;
            private String gctime;
            private String gimg;
            private String scid;

            public String getGcid() {
                return gcid;
            }

            public void setGcid(String gcid) {
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

            public String getScid() {
                return scid;
            }

            public void setScid(String scid) {
                this.scid = scid;
            }
        }
    }


    /* *//**
     * code : 200
     * msg : 获得商铺分类列表成功！
     * result : [{"scid":5,"scname":"茶酒香道","sctime":"1544281049","smoney":"500","goodsClassIFicationInfoModels":[{"gcid":25,"gcname":"白茶","gctime":"1544453600609","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453597937-229.png","scid":5},{"gcid":26,"gcname":"白酒","gctime":"1544453742150","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453737820-8116.png","scid":5},{"gcid":27,"gcname":"茶道","gctime":"1544453750973","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453746099-9063.png","scid":5},{"gcid":28,"gcname":"红酒","gctime":"1544453762717","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453754056-9961.png","scid":5},{"gcid":29,"gcname":"普洱","gctime":"1544453772433","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453766521-5193.png","scid":5},{"gcid":30,"gcname":"其它","gctime":"1544453780341","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453775781-4069.png","scid":5},{"gcid":31,"gcname":"香道","gctime":"1544453792494","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453783952-7632.png","scid":5},{"gcid":32,"gcname":"岩茶","gctime":"1544453802391","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453796869-2373.png","scid":5},{"gcid":33,"gcname":"洋酒","gctime":"1544453810638","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453805149-6656.png","scid":5}]},{"scid":6,"scname":"工艺作品","sctime":"1544281091","smoney":"500","goodsClassIFicationInfoModels":[{"gcid":34,"gcname":"摆件","gctime":"1544453944032","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453930586-1242.png","scid":6},{"gcid":35,"gcname":"沉香","gctime":"1544453952658","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453947124-6540.png","scid":6},{"gcid":36,"gcname":"刺绣","gctime":"1544453960139","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453955627-810.png","scid":6},{"gcid":37,"gcname":"黄花梨","gctime":"1544453969194","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453963569-8539.png","scid":6},{"gcid":38,"gcname":"剪纸","gctime":"1544453979442","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453972747-1240.png","scid":6},{"gcid":39,"gcname":"金银器","gctime":"1544453989354","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453984223-3564.png","scid":6},{"gcid":40,"gcname":"琉璃","gctime":"1544453997723","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453992690-5985.png","scid":6},{"gcid":41,"gcname":"木雕","gctime":"1544454005708","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454001341-6787.png","scid":6},{"gcid":42,"gcname":"漆器","gctime":"1544454017659","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454008865-5774.png","scid":6},{"gcid":43,"gcname":"石雕","gctime":"1544454035587","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454030926-868.png","scid":6},{"gcid":44,"gcname":"铜铁锡器","gctime":"1544454049907","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454041677-4731.png","scid":6},{"gcid":45,"gcname":"珠串","gctime":"1544454061764","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454053213-1681.png","scid":6},{"gcid":46,"gcname":"紫夜檀","gctime":"1544454102068","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544454066624-8809.png","scid":6}]},{"scid":7,"scname":"花鸟文娱","sctime":"1544281114","smoney":"500","goodsClassIFicationInfoModels":[]},{"scid":8,"scname":"书画篆刻","sctime":"1544281114","smoney":"500","goodsClassIFicationInfoModels":[]},{"scid":9,"scname":"文玩杂项","sctime":"1544281151","smoney":"1000","goodsClassIFicationInfoModels":[]},{"scid":10,"scname":"邮币纸品","sctime":"1544281180","smoney":"200","goodsClassIFicationInfoModels":[]},{"scid":11,"scname":"紫砂陶瓷","sctime":"1544281212","smoney":"400","goodsClassIFicationInfoModels":[]}]
     *//*

    private String code;
    private String msg;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        *//**
         * scid : 5
         * scname : 茶酒香道
         * sctime : 1544281049
         * smoney : 500
         * goodsClassIFicationInfoModels : [{"gcid":25,"gcname":"白茶","gctime":"1544453600609","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453597937-229.png","scid":5},{"gcid":26,"gcname":"白酒","gctime":"1544453742150","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453737820-8116.png","scid":5},{"gcid":27,"gcname":"茶道","gctime":"1544453750973","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453746099-9063.png","scid":5},{"gcid":28,"gcname":"红酒","gctime":"1544453762717","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453754056-9961.png","scid":5},{"gcid":29,"gcname":"普洱","gctime":"1544453772433","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453766521-5193.png","scid":5},{"gcid":30,"gcname":"其它","gctime":"1544453780341","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453775781-4069.png","scid":5},{"gcid":31,"gcname":"香道","gctime":"1544453792494","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453783952-7632.png","scid":5},{"gcid":32,"gcname":"岩茶","gctime":"1544453802391","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453796869-2373.png","scid":5},{"gcid":33,"gcname":"洋酒","gctime":"1544453810638","gimg":"http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453805149-6656.png","scid":5}]
         *//*

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

        public static class GoodsClassIFicationInfoModelsBean implements Serializable{
            *//**
             * gcid : 25
             * gcname : 白茶
             * gctime : 1544453600609
             * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/2018-12-10/PPH1544453597937-229.png
             * scid : 5
             *//*

            private String gcid;
            private String gcname;
            private String gctime;
            private String gimg;
            private String scid;

            public String getGcid() {
                return gcid;
            }

            public void setGcid(String gcid) {
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

            public String getScid() {
                return scid;
            }

            public void setScid(String scid) {
                this.scid = scid;
            }
        }
    }
*/



    /* *//**
     * code : 200
     * msg : 获得商铺分类列表成功！
     * result : [{"scid":3,"scname":"书法字画","sctime":"1538165534528","goodsClassIFicationInfoModels":[{"gcid":4,"gcname":"陶器","gctime":"1537556081065","scid":3},{"gcid":5,"gcname":"手链","gctime":"1537556081095","scid":3},{"gcid":7,"gcname":"书画篆刻","gctime":"1543419235018","scid":3},{"gcid":8,"gcname":"工艺作品","gctime":"1543419250313","scid":3},{"gcid":9,"gcname":"文玩杂耍","gctime":"1543419266848","scid":3},{"gcid":10,"gcname":"茶酒香道","gctime":"1543419320099","scid":3}]},{"scid":4,"scname":"金银玉器","sctime":"1538166322444","goodsClassIFicationInfoModels":[{"gcid":11,"gcname":"花鸟文娱","gctime":"1543419330680","scid":4},{"gcid":12,"gcname":"紫砂陶瓷","gctime":"1543419341959","scid":4},{"gcid":13,"gcname":"玉翠珠宝","gctime":"1543419354333","scid":4},{"gcid":14,"gcname":"邮币纸品","gctime":"1543419363420","scid":4}]}]
     *//*

    private String code;
    private String msg;
    private List<ResultBean> result; // 这个是左边这个现在是全部

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        *//**
         * scid : 3
         * scname : 书法字画
         * sctime : 1538165534528
         * goodsClassIFicationInfoModels : [{"gcid":4,"gcname":"陶器","gctime":"1537556081065","scid":3},{"gcid":5,"gcname":"手链","gctime":"1537556081095","scid":3},{"gcid":7,"gcname":"书画篆刻","gctime":"1543419235018","scid":3},{"gcid":8,"gcname":"工艺作品","gctime":"1543419250313","scid":3},{"gcid":9,"gcname":"文玩杂耍","gctime":"1543419266848","scid":3},{"gcid":10,"gcname":"茶酒香道","gctime":"1543419320099","scid":3}]
         *//*
        //这里是左边
        private int scid;
        private String scname;//应该是这个吧
        private String sctime;
//        上面三个是左边的
        //这里是右边
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

        public List<GoodsClassIFicationInfoModelsBean> getGoodsClassIFicationInfoModels() {
            return goodsClassIFicationInfoModels;
        }

        public void setGoodsClassIFicationInfoModels(List<GoodsClassIFicationInfoModelsBean> goodsClassIFicationInfoModels) {
            this.goodsClassIFicationInfoModels = goodsClassIFicationInfoModels;
        }

        public static class GoodsClassIFicationInfoModelsBean implements Serializable{
            *//**
             * gcid : 4
             * gcname : 陶器
             * gctime : 1537556081065
             * scid : 3   还是没有gridview
             * 这里得有右边的title和文玩核对，这边现在没有图片，现在就给他填充一个gcname就行 gridview的数据也没有哦
             *//*
            //这里就是给gridview的   那右边的listview什么都没有？   这里应该是那个标题和文玩核桃
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
    }*/
}
