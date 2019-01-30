package com.yaer.remittance.bean;

public class selectBannerBean {

    /**
     * bid : 1
     * bname : 这是一个测试跳转网页
     * bimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201811222253-d26c9f73-ba6e-4977-a7e7-2f27012b21d6.png
     * btype : 1
     * burl : https://www.baidu.com/
     */

    private String bid;
    private String bname;
    private String bimg;
    private String btype;
    private String burl;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl;
    }
}
