package com.yaer.remittance.bean;

/**
 * 关注店铺
 */
public class GetFollowShopBean {
    //店铺id
    public String sid;
    //店铺名称
    public String sname;
    //店铺关注数
    public String sfans;
    //店铺评分
    public String sgrade;
    //店铺标签
    private String slabel;
    //店铺图片
    public String simg;
    //时间
    public String stime;
    //店铺背景图片
    public String sbgimg;
    //店铺分类
    private String stype;
    //是否关注
    public Boolean followStatus;
    //用户头像
    public String uicon;
    //用户名称
    public String uname;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSfans() {
        return sfans;
    }

    public void setSfans(String sfans) {
        this.sfans = sfans;
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade;
    }

    public String getSlabel() {
        return slabel;
    }

    public void setSlabel(String slabel) {
        this.slabel = slabel;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSbgimg() {
        return sbgimg;
    }

    public void setSbgimg(String sbgimg) {
        this.sbgimg = sbgimg;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public Boolean getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Boolean followStatus) {
        this.followStatus = followStatus;
    }

    public String getUicon() {
        return uicon;
    }

    public void setUicon(String uicon) {
        this.uicon = uicon;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


}
