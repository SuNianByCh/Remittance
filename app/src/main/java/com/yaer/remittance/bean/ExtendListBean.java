package com.yaer.remittance.bean;

import java.io.Serializable;
import java.util.List;

public class ExtendListBean implements Serializable {

    /**
     * eid : 5
     * ename : 测试服务
     * edesc : 这个是测试服务
     * extendMoneyInfoModelList : [{"emid":13,"eid":5,"emoney":"3","emday":"30"},{"emid":14,"eid":5,"emoney":"7","emday":"65"},{"emid":15,"eid":5,"emoney":"1","emday":"10"}]
     */

    private int eid;
    private String ename;
    private String edesc;
    private List<ExtendMoneyInfoModelListBean> extendMoneyInfoModelList;

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

    public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

    public List<ExtendMoneyInfoModelListBean> getExtendMoneyInfoModelList() {
        return extendMoneyInfoModelList;
    }

    public void setExtendMoneyInfoModelList(List<ExtendMoneyInfoModelListBean> extendMoneyInfoModelList) {
        this.extendMoneyInfoModelList = extendMoneyInfoModelList;
    }

    public static class ExtendMoneyInfoModelListBean implements Serializable {
        /**
         * emid : 13
         * eid : 5
         * emoney : 3
         * emday : 30
         */

        private int emid;
        private int eid;
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
