package com.yaer.remittance.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SelectPersonalAuthenticationInfoBean {
    //个人认证id
    private String paid;
    //账号id
    private String uid;
    //认证名称
    private String paname;
    //认证身份证
    private String pacard;
    /*正面身份证图片*/
    private String pacardimg;
    /*反面面身份证图片*/
    private String pacardimgback;
    //认证类型
    private String pastatus;

    public static String getJsonString(SelectPersonalAuthenticationInfoBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("paid", userBean.getPaid());
        map.put("uid", userBean.getUid());
        map.put("paname", userBean.getPaname());
        map.put("pacard", userBean.getPacard());
        map.put("pacardimg", userBean.getPacardimg());
        map.put("pacardimgback", userBean.getPacardimgback());
        map.put("pastatus", userBean.getPastatus());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPaname() {
        return paname;
    }

    public void setPaname(String paname) {
        this.paname = paname;
    }

    public String getPacard() {
        return pacard;
    }

    public void setPacard(String pacard) {
        this.pacard = pacard;
    }

    public String getPacardimg() {
        return pacardimg;
    }

    public void setPacardimg(String pacardimg) {
        this.pacardimg = pacardimg;
    }

    public String getPacardimgback() {
        return pacardimgback;
    }

    public void setPacardimgback(String pacardimgback) {
        this.pacardimgback = pacardimgback;
    }

    public String getPastatus() {
        return pastatus;
    }

    public void setPastatus(String pastatus) {
        this.pastatus = pastatus;
    }
}
