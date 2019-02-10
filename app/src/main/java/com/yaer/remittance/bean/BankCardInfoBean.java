package com.yaer.remittance.bean;

/**
 * 2018-11-13
 * 开发者 houjie
 * 银行卡账号实体类
 */
public class BankCardInfoBean {
    //银行账号id
    private String bid;
    //持卡人名称
    private String bperplename;
    //银行卡开户银行名称
    private String bname;
    //银行卡开户银行卡号
    private String bcardnum;
    //时间
    private String btime;
    //当前用户id
    private String uid;
    /*银行卡图片*/
    private String bimg;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBperplename() {
        return bperplename;
    }

    public void setBperplename(String bperplename) {
        this.bperplename = bperplename;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBcardnum() {
        return bcardnum;
    }

    public void setBcardnum(String bcardnum) {
        this.bcardnum = bcardnum;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }
}
