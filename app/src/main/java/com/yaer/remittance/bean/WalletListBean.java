package com.yaer.remittance.bean;

public class WalletListBean {

    /**
     * wid : 325//，明细id
     * wmoney : 0.01金额
     * wtype : 1充值状态
     * wtime : 1546951279948明细时间
     * uid : 92、、用户id
     */

    private int wid;
    private String wmoney;
    private String wtype;
    private long wtime;
    private int uid;

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWmoney() {
        return wmoney;
    }

    public void setWmoney(String wmoney) {
        this.wmoney = wmoney;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public long getWtime() {
        return wtime;
    }

    public void setWtime(long wtime) {
        this.wtime = wtime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
