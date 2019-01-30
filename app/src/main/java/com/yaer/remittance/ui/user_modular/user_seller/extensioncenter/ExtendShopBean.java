package com.yaer.remittance.ui.user_modular.user_seller.extensioncenter;

public class ExtendShopBean {

    /**
     * esid : 5
     * emid : 13
     * sid : 107
     * estime : 1548035021779
     * extendMoneyInfoModels : {"emid":13,"eid":5,"ename":"测试服务","emoney":"3","emday":"30"}
     */

    private int esid;
    private int emid;
    private int sid;
    private String estime;
    private ExtendMoneyInfoModelsBean extendMoneyInfoModels;

    public int getEsid() {
        return esid;
    }

    public void setEsid(int esid) {
        this.esid = esid;
    }

    public int getEmid() {
        return emid;
    }

    public void setEmid(int emid) {
        this.emid = emid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getEstime() {
        return estime;
    }

    public void setEstime(String estime) {
        this.estime = estime;
    }

    public ExtendMoneyInfoModelsBean getExtendMoneyInfoModels() {
        return extendMoneyInfoModels;
    }

    public void setExtendMoneyInfoModels(ExtendMoneyInfoModelsBean extendMoneyInfoModels) {
        this.extendMoneyInfoModels = extendMoneyInfoModels;
    }

    public static class ExtendMoneyInfoModelsBean {
        /**
         * emid : 13
         * eid : 5
         * ename : 测试服务
         * emoney : 3
         * emday : 30
         */

        private int emid;
        private int eid;
        private String ename;
        private String emoney;
        private String emday;

        public int getEmid() {
            return emid;
        }

        public void setEmid(int emid) {
            this.emid = emid;
        }

        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getEmoney() {
            return emoney;
        }

        public void setEmoney(String emoney) {
            this.emoney = emoney;
        }

        public String getEmday() {
            return emday;
        }

        public void setEmday(String emday) {
            this.emday = emday;
        }
    }
}
