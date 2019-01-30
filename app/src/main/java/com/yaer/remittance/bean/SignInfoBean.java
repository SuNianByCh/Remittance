package com.yaer.remittance.bean;

public class SignInfoBean {
    /**
     * sid : 8
     * uid : 107
     * stime : 1548348541180
     * sday : 6
     * uintegral : 60
     */

    private int sid;
    private int uid;
    private String stime;
    private int sday;
    private int uintegral;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public int getSday() {
        return sday;
    }

    public void setSday(int sday) {
        this.sday = sday;
    }

    public int getUintegral() {
        return uintegral;
    }

    public void setUintegral(int uintegral) {
        this.uintegral = uintegral;
    }


   /* private int sid;
    private int uid;
    private long stime;
    private int sday;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getStime() {
        return stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public int getSday() {
        return sday;
    }

    public void setSday(int sday) {
        this.sday = sday;
    }*/
}
