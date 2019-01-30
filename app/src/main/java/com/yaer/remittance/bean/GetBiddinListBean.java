package com.yaer.remittance.bean;

public class GetBiddinListBean {

    /**
     * bid : 106
     * uid : 92
     * uname : 睡觉的壶
     * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201812192228-38e7aef0-bdbb-4776-b77a-e4e9e537a81f.png
     * gid : 120
     * bmoney : 10.00
     * btime : 1545703573713
     */

    private int bid;
    private int uid;
    private String uname;
    private String uicon;
    private int gid;
    private String bmoney;
    private long btime;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUicon() {
        return uicon;
    }

    public void setUicon(String uicon) {
        this.uicon = uicon;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getBmoney() {
        return bmoney;
    }

    public void setBmoney(String bmoney) {
        this.bmoney = bmoney;
    }

    public long getBtime() {
        return btime;
    }

    public void setBtime(long btime) {
        this.btime = btime;
    }
}
