package com.yaer.remittance.bean;

public class AllMessageBean {

    /**
     * mid : 6
     * mcontent : 支付成功！花费100.0元
     * mtype : 1
     * mtime : 1548332749830
     * mtid :
     * uid : 107
     * mimg :
     */

    private int mid;
    private String mcontent;
    private int mtype;
    private String mtime;
    private String mtid;
    private String uid;
    private String mimg;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getMtid() {
        return mtid;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMimg() {
        return mimg;
    }

    public void setMimg(String mimg) {
        this.mimg = mimg;
    }
}
