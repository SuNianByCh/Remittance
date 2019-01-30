package com.yaer.remittance.bean;

public class getBondInfoBean {

    /**
     * bid : 1
     * bstatus : 0
     * uid : 107
     * btime : 1547550864257
     * bmoney : 1000
     */

    private int bid;
    private int bstatus;
    private int uid;
    private String btime;
    private String bmoney;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBstatus() {
        return bstatus;
    }

    public void setBstatus(int bstatus) {
        this.bstatus = bstatus;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getBmoney() {
        return bmoney;
    }

    public void setBmoney(String bmoney) {
        this.bmoney = bmoney;
    }
}
