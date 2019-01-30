package com.yaer.remittance.bean;

public class AppMode {

    /**
     * aid : 1
     * androidv : 100
     * iosv : 100
     */

    private String aid;
    private int androidv;//更新版本code
    private String vdesc;//更新内容
    private int state;//	0不强制更新 1强制更新
    private String androidvname;//更新版本name
    private String iosv;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }

    public String getAndroidvname() {
        return androidvname;
    }

    public void setAndroidvname(String androidvname) {
        this.androidvname = androidvname;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getAndroidv() {
        return androidv;
    }

    public void setAndroidv(int androidv) {
        this.androidv = androidv;
    }

    public String getIosv() {
        return iosv;
    }

    public void setIosv(String iosv) {
        this.iosv = iosv;
    }
}
